# CodeEditor 架构文档

本文档描述 `CodeEditor` 模块的内部结构与关键执行路径，重点覆盖：**视图层级、绘制流程、手势缩放、光标绘制、拖动滚动、输入事件处理**。所有引用均给出 `文件路径:行号`，便于在源码中定位。

---

## 1. 总体架构概览

`CodeEditor` 是一个独立的 Android Library（`CodeEditor/build.gradle`，`compileSdkVersion 31`，`minSdk 21`），核心是一个继承 `ViewGroup` 的自绘文本编辑器，搭配 LSP4J（`org.eclipse.lsp4j:0.10.0`）以便接入语言服务。

整体采用经典的 **MVC 三层划分**：

```
┌─────────────────────────────────────────────────────────────┐
│  View 层 (绘制 + 触摸 + 输入)                                │
│  ─ EditorField / CodeTextField / CodeEditor                  │
│  ─ EditorDrawer / LineDrawer  (绘制委托)                     │
│  ─ TouchNavigationMethod / YoyoNavigationMethod (手势)        │
│  ─ EditorInputConnection / KeysInterpreter   (IME / 键盘)     │
│  ─ MaterialSlideBar / MagnifierMotionAnimator (UI 装饰)       │
└────────────────────────┬────────────────────────────────────┘
                         │ 调 controller
                         ▼
┌─────────────────────────────────────────────────────────────┐
│  Controller 层                                               │
│  ─ EditorController                                          │
│     · 光标移动 / 选区 / 自动缩进 / 剪贴板                    │
│     · 实现 Lexer.LexCallback                                 │
└────────────────────────┬────────────────────────────────────┘
                         │ 改 model
                         ▼
┌─────────────────────────────────────────────────────────────┐
│  Model 层                                                    │
│  ─ Document          (TextFieldMetrics + 自动换行)           │
│  ─ TextBuffer        (Gap Buffer + Span 表 + Undo)           │
│  ─ DocumentProvider  (对外封装文档 + Undo/Redo)              │
│  ─ Span / Lexer / LexTask  (语法高亮 token)                  │
└─────────────────────────────────────────────────────────────┘
```

设置（字体、缩进、行号、缩放速度等）集中存于 `EditorSetting`，可由 `CodeEditor.Default` 提供默认值（参见 `CodeEditor/src/main/java/tiiehenry/code/view/EditorSetting.java:12`）。`EditorSetting` 实现 `clone()`，便于保存/恢复 UI 状态。

---

## 2. 视图层级与继承关系

```
android.view.View
   └── ViewGroup
         └── EditorAbstract                (实现 Document.TextFieldMetrics、长按选字符表)
               └── EditorField              (核心：绘制 + 触摸 + 滚动 + IME)
                     └── CodeTextField     (Default 默认值 + UI 状态保存)
                           └── CodeEditor  (用户面对的入口：快捷键、放大镜、撤销重做、注释)
                                 └── LSPCodeEditor (接 LSP4J)
```

各层职责：

- **`EditorAbstract`** — 继承 `ViewGroup`，承载 `PICKER_SETS`（`SparseArray`，把单字符映射到带重音符号的候选串，例如 `'A' → "ÀÁÂÄÆÃÅĄĀ"`），来源照搬自 Android `QwertyKeyListener`，用于长按字符弹候选。
- **`EditorField`** — 1827 行的"主战场"：覆写 `onDraw / onTouchEvent / onKeyDown / onCreateInputConnection / computeScroll / onGenericMotionEvent` 等关键回调，持有 `OverScroller _scroller`、`MaterialSlideBar _scrollbar`、`TouchNavigationMethod _navMethod`、`EditorInputConnection _inputConnection`、`EditorDrawer drawer`、`EditorController fieldController`。
- **`CodeTextField`** — 抽象层，提供 `Default` 静态默认值（`tabLength=3`、`caretWidth=6`、`yoyoSize=16`、`slidingSpeedVertical=1` 等），并提供 `TextFieldUiState` 用于保存/恢复（光标位置、滚动位置、选区、设置克隆）。
- **`CodeEditor`** — 用户层 API：`init(Context)` 中 `initFont(fontDir)` 从 `/sdcard/Android/fonts/{default,bold,italic}.ttf` 加载字体；提供 `setText / gotoLine / selectLine / noteLine / noteBlock / format / undo / redo / setShowMagnifier`；并设置 `Ctrl+A/X/C/V/Z/D` 与 `Shift+Up/Down`（行内移动）等快捷键。

---

## 3. View 绘制流程

### 3.1 入口 — `EditorField.onDraw`

`EditorField.onDraw(Canvas)` 是绘制起点：先把画布裁剪到去 padding 的区域并平移，然后委托 `EditorDrawer.realDraw(...)`，最后让 `_navMethod.onTextDrawComplete()` 画 yoyo 控制柄、`_scrollbar.draw()` 画右侧滚动条。

```text
onDraw(canvas)
  ├─ canvas.clipRect(...)
  ├─ canvas.translate(paddingLeft, paddingTop)
  ├─ drawer.realDraw(canvas, this)             ← 主体文本 + 高亮
  ├─ _navMethod.onTextDrawComplete(canvas)     ← Yoyo 选区控制柄
  └─ _scrollbar.draw(canvas)                   ← 右侧滑块
```

### 3.2 主绘制 — `EditorDrawer.realDraw`

文件：`CodeEditor/src/main/java/tiiehenry/code/praser/prog/EditorDrawer.java`（676 行）。

执行顺序（从下到上叠加）：

1. **`lineDrawer.drawOptionHighlightRow`** — 高亮当前行整行背景（若 `setting.isHighlightCurrentRow`）。
2. **`lineDrawer.drawBlockLine`** — 在非自动换行时，按 `LexTask.getBlockRegionLinesList()` 给出的 `BlockLine` 列表绘制嵌套代码块的左侧竖线。当前光标所在最深块用 `SEPARATOR` 颜色加粗（3 px 矩形，见 `LineDrawer.java:99-107`）。
3. **`lineDrawer.drawLineNumberBackground`** — 行号槽位背景，并叠加当前行号背景高亮（见 `LineDrawer.java:131-158`）。
4. **可见行计算** — 由 `canvas.getClipBounds()` 的 `top/bottom` 除以 `getRowHeight()` 推出可见首末行；
5. **Span 二分查找** — 在 `_spans` 列表里二分找首个可见字符所属 token，避免遍历整个 buffer。
6. **逐行绘制**：
   - `drawLineNumber(canvas, leftOffset, num, paintY, editorField)`（见 `LineDrawer.java:123-129`），右对齐到 `leftOffset`；
   - 然后逐字符，根据当前位置是否处于：
     - 选区 → `drawSelectedText`（高亮背景 + 反色文本），
     - 错误区 → `drawErrorText`（错误下划线/底色 `_brushLineErr`），
     - 普通 → `drawChar`；
   - 如果 `currIndex - 1 == caretPosition` 即此处是光标插入点 → 调用 `drawCaret(canvas, x, currPaintY, editorField)`。
7. **Tab 与不可见字符**：
   - Tab 推进按列对齐：`tabLength * spaceWidth - (x - leftOffset) % spaceWidth`；
   - `setting.isShowNonPrinting` 开启时把空格、tab、换行画成可见字形，颜色取 `Colorable.NON_PRINTING_GLYPH`。
8. **Emoji 处理**：识别高位代理 `0xd83c`/`0xd83d`（emoji surrogate pair）并合并绘制。
9. **`_xExtent` 跟踪** — 全文最长行像素宽度，用于水平滚动上限。
10. **`drawBlockPairs`** — 收尾处绘制匹配括号对的高亮（运行时若 caret 紧邻 `( { [` 等）。

### 3.3 行号槽与样式 — `LineDrawer`

`LineDrawer` 内部维护五支独立 `Paint`：`_brushLine`（块竖线）、`_brushLineNumber`（行号）、`_brushLineBackground`（槽位底色 + 当前行整行底色）、`_brushLineBackgroundHighlight`（当前行号槽底色）、`_brushLineErr`（错误下划线/方块）。它实现 `ColorScheme.OnColorChangedListener`，主题切换时重新拉色。

关键方法：

- `drawLineNumber(canvas, leftOffset, num, paintY, ef)` — 行号文本右对齐：`x = (leftOffset - measureText(s) - spaceWidth/2f) / 2`，见 `LineDrawer.java:126`。
- `drawLineNumberBackground(canvas, leftOffset, ef)` — 整列槽位用 `_brushLineBackground` 涂色，再调用 `drawLineNumberBackgroundHighlight` 叠加当前行高亮。
- `drawBlockLine(canvas, ef)` — 按 `BlockLine.startLine/endLine` 计算 `top/bottom = startLine*rowHeight / (endLine-1)*rowHeight`；用 `drawer.getCharExtent(...)` 得列像素 `left`，`drawLine(left, top, left, bottom)`。当前光标所在的最深嵌套块用 `SEPARATOR` 颜色 3 px 矩形强调（`drawRect(left-2, top, left+2, bottom)`）。

### 3.4 几何计算

- `getRowHeight() = metrics.descent - metrics.ascent`。
- `getPaintBaseline(row) = (row+1) * rowHeight - metrics.descent`。
- `getCharExtent(charOffset, ef)` — 沿光标所在行从行首推进，累计 advance，返回 `Pair<left, right>` 像素坐标。
- `coordToCharIndexGetI(x, y, ef)` — 反向：先用 `y/rowHeight` 找行，再沿行累积 advance 命中 `x`，得到字符偏移；触摸命中即由此完成。
- 字符宽度均通过 `Paint.measureText` 取得，等宽字体下也支持代理对、tab 列对齐和不可见符替换字形。

---

## 4. 光标绘制

### 4.1 文本插入光标本体

`EditorDrawer.drawCaret(canvas, paintX, paintY, ef)` 是个**纯矩形填充**（无闪烁动画）：

```java
// EditorDrawer.java drawCaret 简化伪代码
canvas.drawRect(paintX - caretWidth/2,
                paintY + metrics.ascent,
                paintX + caretWidth/2,
                paintY + metrics.descent,
                _brushCaret);
```

颜色取 `ColorScheme.Colorable.CARET_DISABLED`（命名仅为历史遗留，实际即默认光标色），宽度 `setting.caretWidth`（默认 `6` 像素，见 `EditorSetting.java:46`）。绘制由 `realDraw` 在遍历到 `currIndex - 1 == caretPosition` 时触发，所以光标总是与文本绘制同帧渲染，不需要单独的闪烁 `Runnable`。

### 4.2 Yoyo 控制柄 (`YoyoNavigationMethod`)

为了在触屏上精准移动光标 / 调整选区，`YoyoNavigationMethod`（`CodeEditor/src/main/java/tiiehenry/code/view/YoyoNavigationMethod.java`，382 行）继承自 `TouchNavigationMethod`，并维护三个 `Yoyo` 实例：

| 实例           | 用途                                  |
| -------------- | ------------------------------------- |
| `_yoyoCaret`   | 无选区时挂在光标下方的小拖把          |
| `_yoyoStart`   | 选区状态下吸附选区起点                |
| `_yoyoEnd`     | 选区状态下吸附选区终点                |

每个 Yoyo 由两部分组成：
- **绳索**：`canvas.drawArc(...)` 从字符基线垂下；
- **手柄**：`canvas.drawOval(...)` 在绳索末端，半径 ≈ `setting.yoyoSize`（默认 `16`）。

`YoyoNavigationMethod.onTextDrawComplete(canvas)` 在 `EditorField.onDraw` 末尾被调用：
- 无选区 → 仅画 `_yoyoCaret`；
- 有选区 → 画 `_yoyoStart` 与 `_yoyoEnd`。

拖动手柄时：
- `Yoyo.moveHandle(x, y)` → `findNearestChar(x, y)` 计算新光标偏移；
- `_textField.moveCaret(newCaretIndex, true)` 移光标（最后一个参数标记"用户在打字/拖动中"，控制 selection 范围）；
- `attachYoyo(...)` 把锚点重新吸附到字符基线下方；
- 同时 `_textField.showMagnifier(...)` 触发系统放大镜跟随。

---

## 5. 手势缩放（双指 Pinch Zoom）

文件：`CodeEditor/src/main/java/tiiehenry/code/view/TouchNavigationMethod.java`（449 行），继承自 `GestureDetector.SimpleOnGestureListener`。

### 5.1 触发与算法

`onTouchEvent` 先把事件交给 `GestureDetector`，再额外路由 `onTouchZoom(event)`。当且仅当 `event.getPointerCount() == 2` 时执行缩放：

```java
// TouchNavigationMethod.onTouchZoom 核心逻辑
if (e.getPointerCount() == 2) {
    if (lastDist == 0) {
        lastDist = spacing(e);            // 双指落下瞬间的初始距
        lastSize = _textField.getTextSize();
    }
    float dist = spacing(e);              // 当前双指间距
    _textField.setTextSize((int)(lastSize * (dist / lastDist)));
}
```

其中：

```java
private float spacing(MotionEvent e) {
    float dx = e.getX(0) - e.getX(1);
    float dy = e.getY(0) - e.getY(1);
    return (float)Math.sqrt(dx*dx + dy*dy);   // 欧氏距离
}
```

放手时 `ACTION_POINTER_UP / ACTION_UP` 重置 `lastDist = 0`，下次下两指再重新基准化。

### 5.2 字号变化的连锁效应

`setTextSize(int)` 在 `EditorField` 中会：
1. 更新所有 `Paint` 的 `setTextSize(...)`；
2. 触发字符宽度、`rowHeight`、`spaceWidth` 重算；
3. 若开启自动换行，触发 `Document.analyzeWordWrap()` 重排 `_rowTable`；
4. `requestLayout()` 与 `invalidate()` 强制重绘。

由于 `_xExtent`（最长行像素宽）也会随 `Paint` 变化重算，水平滚动上限自动跟随。

---

## 6. 拖动与滚动

### 6.1 滚动核心 — Android `OverScroller`

`EditorField` 持有一个 `OverScroller _scroller`，以下三种场景会驱动它：

| 场景           | 入口                                                                            |
| -------------- | ------------------------------------------------------------------------------- |
| 普通拖动       | `TouchNavigationMethod.onScroll(e1,e2,dx,dy)` → `_textField.scrollTo(...)`       |
| 抛掷 Fling     | `TouchNavigationMethod.onFling(...)` → `_textField.flingScroll(-vx,-vy)`         |
| 自动回弹       | `TouchNavigationMethod.springBack()` → `_scroller.springBack(...)`               |
| 滑块拖动       | `MaterialSlideBar.handleEvent(...)` → `_textField.scrollTo(scrollX, ratio*max)`  |
| 光标边缘自动滚 | `EditorField` 中四个 `Runnable` 周期 250 ms 自调 `scrollBy(...)` + `re-post`     |

### 6.2 单指拖动（`onScroll`）

`TouchNavigationMethod.onScroll`：

1. 第一次回调时记录主滑动方向：`if (|dx| > |dy|) flingDir = HORIZONTAL else VERTICAL`；
2. 之后只允许单一方向滚动，避免 X/Y 抖动；
3. 调 `_textField.scrollTo(scrollX + dx, scrollY + dy)`。

水平和竖直允许的额外越界量由静态字段控制：
- `touchSlopHorizontal = 100`
- `touchSlopVertical   = 200`

越界后 `springBack()` 回弹。

### 6.3 抛掷与计算 — `flingScroll` / `computeScroll`

```java
// EditorField.flingScroll
_scroller.fling(scrollX, scrollY,
                -velocityX, -velocityY,
                0, maxScrollX,
                0, maxScrollY,
                touchSlopHorizontal, touchSlopVertical);  // 越界缓冲
```

`computeScroll()` 每帧在 `Choreographer` 触发：
- `if (_scroller.computeScrollOffset()) scrollTo(currX, currY); postInvalidate();`
- 若已停止且当前位置越界，调用 `_scroller.springBack(...)` 拉回边界。

抛掷速度由 `setting.slidingSpeedVertical` / `slidingSpeedHorizontal` 倍率（默认均为 `1`）调节。

### 6.4 鼠标滚轮

`EditorField.onGenericMotionEvent` 检测 `SOURCE_CLASS_POINTER` + `ACTION_SCROLL`，读取 `AXIS_VSCROLL`，乘以 `rowHeight` 后调 `scrollBy(0, -axis * rowHeight * 3)`，实现桌面/Chromebook 上的滚轮支持。

### 6.5 光标边缘自动滚动

当用户拖 yoyo 或选区落到视口边缘附近时，`EditorField` 中的四个 `Runnable`（上/下/左/右）会以 `SCROLL_PERIOD = 250 ms` 周期自调 `scrollBy(...)` 并重新 `postDelayed`。`TouchNavigationMethod.dragCaret(x, y)` 在判定边缘时 `_textField.autoScrollCaret(direction)` 启动对应任务，离开边缘则 `stopAutoScrollCaret()`。

### 6.6 右侧滑块 — `MaterialSlideBar`

文件：`CodeEditor/src/main/java/tiiehenry/code/view/MaterialSlideBar.java`。

- 槽宽 `width = 25`，被按住后变粗到 `touchedWidth = 30`。
- 滑块高度：`barHeight = max(contentHeight² / maxHeight, 3*width)`，保证短文档时也有可拖区域。
- 命中判定：触摸点 X 在 `[viewWidth - touchedWidth, viewWidth]` 且 Y 在滑块当前 y 范围内 → 进入拖动模式。
- 拖动时按比例：`_textField.scrollTo(scrollX, ratio * maxScrollY)`，其中 `ratio = (touchY - barTop)/barRange`。
- `handleEvent` 在 `EditorField.onTouchEvent` 内**优先**于 `_navMethod.onTouchEvent` 调用，以避免与文本拖动手势冲突。

---

## 7. 输入事件处理

### 7.1 触摸事件分发

```text
EditorField.onTouchEvent(MotionEvent e)
  ├─ if (_scrollbar.handleEvent(e)) return true;    // 滑块优先
  └─ return _navMethod.onTouchEvent(e);             // 否则交给 navMethod
```

`TouchNavigationMethod` 内部使用 `GestureDetector(getContext(), this)` 把 `MotionEvent` 拆成回调（`onDown / onScroll / onFling / onLongPress / onSingleTapUp / onDoubleTap`），并在 `onTouchEvent` 中额外执行 `onTouchZoom`（双指缩放）和 `dragCaret`（光标拖动）。

#### 双击智能选区（`onDoubleTap`）

优先级链：
1. **`LexTask.expandSelection(charOffset)`** —— 若当前 token 类型已知（关键字、字面量、字符串等），按词法选词；
2. **回退到标识符** —— 用 `Character.isJavaIdentifierPart` 向前后扩张；
3. **再回退到整行** —— 选中光标所在行。

#### 长按字符候选

`EditorAbstract.PICKER_SETS` 在长按时弹出含重音符号的候选菜单（如 `'A' → "ÀÁÂÄÆÃÅĄĀ"`），完全照搬 Android `QwertyKeyListener` 的映射表。

### 7.2 物理键盘 / 蓝牙键盘 — `onKeyDown`

`EditorField.onKeyDown(keyCode, event)`：

- 若 `KeysInterpreter.isNavigationKey(keyCode)`（DPAD_UP/DOWN/LEFT/RIGHT）→ `handleNavigationKey(...)`，含 Shift 选区扩展；
- 否则 `char c = KeysInterpreter.keyEventToPrintableChar(event)`，再 `fieldController.onPrintableChar(c)`。

`KeysInterpreter` 的映射表（关键 case）：

| KeyCode             | 输出                                                    |
| ------------------- | ------------------------------------------------------- |
| `KEYCODE_ENTER`     | `Language.NEWLINE`                                      |
| `KEYCODE_DEL`       | `Language.BACKSPACE`                                    |
| `KEYCODE_TAB`       | `Language.TAB`                                          |
| `KEYCODE_SPACE`     | `' '`                                                   |
| 其它                | `event.getUnicodeChar(event.getMetaState())` 若可打印   |

`CodeEditor` 在其上层注册了快捷键监听：`Ctrl+A` 全选、`Ctrl+X/C/V` 剪切复制粘贴、`Ctrl+Z/D` 撤销/重做、`Shift+Up/Down` 行内移动。

### 7.3 IME / 软键盘 — `EditorInputConnection`

`EditorField.onCreateInputConnection(outAttrs)`：

```java
outAttrs.inputType  = InputType.TYPE_CLASS_TEXT
                    | InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
                    | InputType.TYPE_TEXT_FLAG_MULTI_LINE;
outAttrs.imeOptions = EditorInfo.IME_FLAG_NO_EXTRACT_UI
                    | EditorInfo.IME_FLAG_NO_FULLSCREEN;
return _inputConnection;   // 即 EditorInputConnection
```

`EditorInputConnection extends BaseInputConnection`（412 行），关键回调：

| 回调                                       | 行为                                                                                  |
| ------------------------------------------ | ------------------------------------------------------------------------------------- |
| `setComposingText(text, newCursorPosition)`| 维护 `_composingCharCount`，调 `replaceComposingText(...)` 把 IME 候选写入文档        |
| `commitText(text, ...)`                    | 类似但结束 batch edit；`commitTextDisableOnce` 用于自动补全二次提交保护               |
| `deleteSurroundingText(left, right)`       | → `fieldController.deleteAroundComposingText(left, right)`                            |
| `sendKeyEvent(KeyEvent)`                   | 处理 `SHIFT_LEFT`（弹起切换选区模式）、`DPAD_*`、`MOVE_HOME / MOVE_END`               |
| `getCursorCapsMode(reqModes)`              | 智能首字母大写：扫描前一个非空白字符是否句末标点                                      |
| `performContextMenuAction(id)`             | 处理 Android 系统菜单 ID：`copy / paste / cut / selectAll / startSelectingText` 等    |

### 7.4 EditorController — 输入终点

`EditorController.onPrintableChar(char c)`：

- `c == BACKSPACE` —— 检测光标前是否为高位代理（emoji），是则一次删 2 字符；否则单字符；
- `c == NEWLINE` —— 调 `createAutoIndent()`：先复制当前行前导空白，再追加 `setting.autoIndentWidth * lexTask.createAutoIndent()` 个空格（若 `lexTask.createAutoIndent()` 返回 1，意味着语言期望进入更深一级缩进，例如 Java 的 `{` 后）；
- 其他可打印字符 —— 普通插入 + `lexer` 触发增量重词法。

光标移动一族 `moveCaret*(isTyping)`：`isTyping=true` 时若处于选区状态，会以"扩选"语义维护 `selectionAnchor`；否则直接平移。`updateSelectionRange(...)` 统一裁剪到 `[0, length]`。

剪贴板：`cut / copy / paste` 直接走 `ClipboardManager`，与系统 IME 行为一致。

---

## 8. 文档模型

### 8.1 `TextBuffer` —— Gap Buffer

文件：`CodeEditor/src/main/java/tiiehenry/code/doc/TextBuffer.java`。

- 内部 `char[] _contents`，包含一段连续 "gap"，以 `_gapStartIndex` / `_gapEndIndex`（含尾后位置）记录边界；
- `MIN_GAP_SIZE = 50`，`_lineCount` 维护行数缓存；
- 文末追加 `EOF` 字符简化越界判断；
- 实现 `CharSequence`，外部可直接当字符串传入 `Paint.measureText`、`Pattern.matcher` 等；
- `_undoStack` 提供撤销栈；
- `_spans` 是同步维护的 token 列表（语法高亮）；
- 编辑发生在 `_gapStartIndex` 附近时 O(1)；远距离编辑通过 `shiftGapStart(newPos)` 把 gap 搬过去。

### 8.2 `Document` —— 自动换行

`Document extends TextBuffer`，叠加：

- `_rowTable: ArrayList<Integer>` —— 每个可视行起始字符偏移；
- `analyzeWordWrap()` —— 基于 `Paint.measureText` 累积宽度，遇到空白时回退到上一处可断点（典型贪心 word-wrap）；
- `findRowNumber(charOffset)` —— 在 `_rowTable` 二分查行号；
- 重写 `insert / delete / shiftGapStart`，内部调用 `updateWordWrapAfterEdit`，仅对受影响区段重排，避免每次输入全量重算。

### 8.3 `DocumentProvider` —— 上层封装

`DocumentProvider` 提供给 `EditorSetting.doc`（`EditorSetting.java:59`）使用，集中管理：撤销/重做栈、文档替换、读写 IO、与 `lexer` 的协作。`EditorSetting.clone()` 中执行 `s.doc = new DocumentProvider(this.doc);`（`EditorSetting.java:87`），确保设置克隆时文档独立。

### 8.4 自动补全 — `AutoCompletePanel`

基于 Android `ListPopupWindow`。在 `onItemClick` 中按文本是否含 `(` 区分**函数 vs 标识符**：函数补全后把光标左移到 `(` `)` 之间，标识符则停在末尾。`isCompatibilityMode` 模式下兼容某些 IME 的奇怪行为（提交时撤销其 composing region）。

### 8.5 颜色主题 — `ColorScheme`

`Colorable` 枚举每个常量带浅/深两套整型颜色（如 `KEYWORD / SEPARATOR / BASIC_TYPE / TEXT / ERROR / WARNING / COMMENT_* / NON_PRINTING_GLYPH / BACKGROUND / SELECTION_FOREGROUND / SELECTION_BACKGROUND / CARET_BACKGROUND / CARET_DISABLED / SLIDEBAR_HANDLED / SLIDEBAR_BACKGROUND / LINE_OFFSET / LINE_OFFSET_HIGHLIGHT / LINENUMBER`）。所有 `Paint` 使用方实现 `OnColorChangedListener`，构造时调 `addOnColorChangedListener(this)`，主题切换时统一被回调（参见 `LineDrawer.java:36-46`）。

---

## 9. 关键路径速查表

| 关注点         | 入口                                                                                       |
| -------------- | ------------------------------------------------------------------------------------------ |
| 视图层级       | `view/CodeEditor.java`、`view/CodeTextField.java`、`view/EditorField.java`、`view/EditorAbstract.java` |
| 绘制总入口     | `EditorField.onDraw` → `EditorDrawer.realDraw` (`praser/prog/EditorDrawer.java`)            |
| 行号 / 槽位    | `praser/prog/LineDrawer.java`                                                              |
| 光标矩形       | `EditorDrawer.drawCaret`                                                                   |
| 光标控制柄     | `view/YoyoNavigationMethod.java`（`Yoyo` 内部类 + `onTextDrawComplete`）                   |
| 双指缩放       | `view/TouchNavigationMethod.java` 的 `onTouchZoom` / `spacing`                              |
| 单指拖动       | `view/TouchNavigationMethod.java` 的 `onScroll`                                             |
| 抛掷 / 回弹    | `EditorField.flingScroll` / `computeScroll` + `TouchNavigationMethod.springBack`           |
| 滑块           | `view/MaterialSlideBar.java` 的 `handleEvent` / `draw`                                     |
| 边缘自动滚动   | `EditorField` 的 `_scrollCaret*Task` 四个 `Runnable` + `TouchNavigationMethod.dragCaret`    |
| 物理键盘       | `EditorField.onKeyDown` + `view/KeysInterpreter.java`                                      |
| 软键盘 / IME   | `EditorField.onCreateInputConnection` + `view/EditorInputConnection.java`                  |
| Controller     | `view/EditorController.java`（`onPrintableChar` / `createAutoIndent` / `replaceText` 等）   |
| 文档 / Gap Buf | `doc/TextBuffer.java` / `doc/Document.java` / `doc/DocumentProvider.java`                   |
| 设置项         | `view/EditorSetting.java`（`CodeEditor.Default` 提供默认值）                               |
| 主题色         | `view/ColorScheme.java`（`Colorable` 枚举 + `OnColorChangedListener`）                     |
| 放大镜（系统） | `view/MagnifierMotionAnimator.java`（API 28+）                                              |
| 放大镜（自绘） | `view/MagnifierView.java`（未在主流程中使用，备用）                                         |
| 自动补全       | `view/AutoCompletePanel.java`                                                              |

---

## 10. 行为速记 / 设计要点

- **绘制纯靠 `Canvas` + `Paint`**：不使用 `StaticLayout`、不使用 `Spannable`；自实现 token 列表（`Span`）+ 二分定位 + 行表（`_rowTable`）+ Gap Buffer，整体绘制复杂度与可见行数线性相关。
- **光标无闪烁动画**：与文本同帧画一个矩形，靠 `invalidate()` 触发重绘；优点是简单稳健，缺点是没有"打字中暂停闪烁"等系统行为。
- **缩放不重排文字而重算字号**：`onTouchZoom` 直接改 `setTextSize`，所有尺寸缓存（行高、字符宽、`_xExtent`）跟随 `Paint` 重新测量；word-wrap 时自动触发 `analyzeWordWrap`。
- **滚动模型干净**：`OverScroller` 单点驱动 `fling/spring/drag/scrollbar/auto-scroll` 五种来源，`computeScroll` 是唯一会触发滚动位置更新的回调。
- **IME 与 Span 解耦**：IME 直接进入 `EditorController`，词法分析 `Lexer` 在 `LexCallback` 异步推进，绘制端只读 `Span` 列表，避免 IME 输入卡顿。
- **MVC 边界清晰**：`EditorField` 不直接改文档，全部经 `EditorController`；`EditorDrawer` 不持有可变状态，仅读 `EditorField + Document + Span` 完成绘制。

---

> 本文档为静态结构梳理，覆盖 `CodeEditor` 模块当前源码（compileSdkVersion 31）。如未来引入 `StaticLayout` 或 `RenderNode` 渲染、或将 IME 切换到 `InputMethodSession`，相关章节需要同步更新。
