# TaoKDao UI 结构与 CodeEditor 渲染机制深度分析

> 文档生成日期：2026-05-06
> 基于 TaoKDao 主仓库 master 分支代码分析

---

## 目录

1. [整体 UI 架构](#1-整体-ui-架构)
2. [MVP 模式与 MainActivity](#2-mvp-模式与-mainactivity)
3. [主界面布局结构](#3-主界面布局结构)
4. [内容管理系统](#4-内容管理系统)
5. [窗口与 Explorer 系统](#5-窗口与-explorer-系统)
6. [CodeEditor 模块概述](#6-codeeditor-模块概述)
7. [编辑器继承链与核心类](#7-编辑器继承链与核心类)
8. [文本数据模型](#8-文本数据模型)
9. [渲染绘制流程](#9-渲染绘制流程)
10. [语法高亮机制](#10-语法高亮机制)
11. [光标与选择系统](#11-光标与选择系统)
12. [滚动与触摸导航](#12-滚动与触摸导航)
13. [行号与代码块可视化](#13-行号与代码块可视化)
14. [LSP 集成](#14-lsp-集成)

---

## 1. 整体 UI 架构

TaoKDao 采用 **MVP（Model-View-Presenter）** 架构模式，主界面围绕一个中心化的 `MainActivity` 构建。整体 UI 设计理念类似于桌面 IDE（VSCode/IntelliJ），包含以下核心区域：

```
+----------------------------------------------------------+
|  [Nav] [QuickMenu RV]          [Menu RV]                |  Toolbar
|  [Indicator Start] [TabLayout]              [Indicator End]|
+----------------------------------------------------------+
|                                                          |
|                    ContentViewPager                      |  主编辑区
|              (CodeEditor / Guider / etc.)                |
|                                                          |
+----------------------------------------------------------+
|  [Side Buttons]                                          |  侧边栏
+----------------------------------------------------------+
|  [ProgressBar]                                           |  底部进度条
+----------------------------------------------------------+
|  [ToolPage Container (Popup)]                            |  底部工具页
+----------------------------------------------------------+
```

### 核心模块职责

| 模块 | 位置 | 职责 |
|------|------|------|
| `MainActivity` | `app/src/main/java/taokdao/main/MainActivity.kt` | 实现 20+ View 接口，协调所有 Presenter |
| `ContentManage` | `app/src/main/java/taokdao/main/business/content_manage/` | 管理打开的文件/内容（ViewPager） |
| `ExplorerWindow` | `app/src/main/java/taokdao/main/business/window/window_explorer/` | 文件浏览器弹窗 |
| `ToolPageWindow` | `app/src/main/java/taokdao/main/business/window/window_toolpage/` | 底部工具页（Logcat/Search/Event） |
| `CodeEditor` | `CodeEditor/src/main/java/tiiehenry/code/` | 代码编辑器核心 |
| `DynamicLoader` | `DynamicLoader/` | 动态插件加载 |

---

## 2. MVP 模式与 MainActivity

### 2.1 MainActivity 的 View 接口实现

`MainActivity` 实现了 **23 个 View 接口**，每个接口对应一个独立的业务 Presenter：

```kotlin
class MainActivity : BaseMainActivity(),
    FileOpenView, FileOperateView, CategoryMenuView,
    ProgressUseView, SessionControlView, PermissionRequestView, ProjectBuildView,
    PluginManageView, ProjectManageView, ScreenControlView, ThemeManageView, ExitControlView,
    ActionProcessView, MainSettingView, PluginInstallView, FileTemplateGenerateView,
    DrawableManageView, ProjectTemplateView, LanguageManageView, MMKVManageView, DexLoadView,
    BuildManageView, PluginLoadView, ContentManageView, ExplorerDisplayView,
    ToolPageDisplayView, ToolBarLayoutView, DialogManagerView, FileProviderView,
    IndicateManageView {
```

### 2.2 Presenter 清单

| Presenter | 职责 | 对应 View 接口 |
|-----------|------|---------------|
| `FileOpenPresenter` | 文件打开逻辑 | `FileOpenView` |
| `FileOperatePresenter` | 文件操作（复制/删除/重命名） | `FileOperateView` |
| `ContentManagePresenter` | 内容/标签页管理 | `ContentManageView` |
| `ExplorerWindowPresenter` | 文件浏览器窗口 | `ExplorerDisplayView` |
| `ToolPageWindowPresenter` | 底部工具页窗口 | `ToolPageDisplayView` |
| `ProjectManagePresenter` | 项目管理 | `ProjectManageView` |
| `ProjectBuildPresenter` | 项目构建 | `ProjectBuildView` |
| `PluginManagePresenter` | 插件管理 | `PluginManageView` |
| `PluginLoadPresenter` | 插件加载 | `PluginLoadView` |
| `BuildManagePresenter` | 构建流程管理 | `BuildManageView` |
| `ThemeManagePresenter` | 主题管理 | `ThemeManageView` |
| `LanguageManagePresenter` | 多语言管理 | `LanguageManageView` |
| `SessionControlPresenter` | 会话保存/恢复 | `SessionControlView` |
| `IndicateManagePresenter` | 状态栏指示器 | `IndicateManageView` |
| `ToolBarLayoutPresenter` | 工具栏布局 | `ToolBarLayoutView` |

每个 Presenter 在 `MainActivity` 中以 `lazy` 属性初始化：

```kotlin
override val contentManagePresenter = ContentManagePresenter(this)
override val explorerWindowPresenter = ExplorerWindowPresenter(this)
override val tabToolWindowPresenter = ToolPageWindowPresenter(this)
```

### 2.3 启动流程

`onPrepareStartupStage()` 按以下顺序初始化各模块：

1. 应用主题颜色
2. 初始化工具栏布局 (`toolBarLayoutPresenter.init()`)
3. 初始化内容管理器 (`contentManagePresenter.init()`)
4. 初始化 Guider 内容、Explorer 窗口、ToolPage 窗口
5. 初始化各类文件/项目/插件管理器
6. 加载插件引擎和普通插件
7. 恢复上一次会话 (`sessionControlPresenter.restoreSession()`)

---

## 3. 主界面布局结构

### 3.1 主布局文件

`app/src/main/res/layout/activity_main.xml` 使用 **ConstraintLayout**，垂直分层：

```xml
<ConstraintLayout>                          <!-- 根布局 -->
  <ConstraintLayout android:id="@+id/main_toolbar_cl">   <!-- 工具栏 -->
    <ImageView android:id="@+id/main_toolbar_navigator_iv"/>  <!-- 导航图标 -->
    <RecyclerView android:id="@+id/main_toolbar_quickmenu_rv"/> <!-- 快速菜单 -->
    <RecyclerView android:id="@+id/main_toolbar_menu_rv"/>      <!-- 主菜单 -->
    <TextView android:id="@+id/main_toolbar_indicator_start_tv"/> <!-- 左指示器 -->
    <TabLayout android:id="@+id/main_toolbar_tab_layout"/>        <!-- 标签页 -->
    <TextView android:id="@+id/main_toolbar_indicator_end_tv"/>   <!-- 右指示器 -->
  </ConstraintLayout>

  <ContentViewPager android:id="@+id/main_body_content_view_pager"/>  <!-- 内容区 -->
  <include layout="@layout/contents_guider"/>                          <!-- Guider -->
  <LinearLayout android:id="@+id/main_body_side_ll"/>                  <!-- 侧边栏 -->
  <ProgressBar android:id="@+id/main_bottom_progress_bar"/>            <!-- 进度条 -->
  <FrameLayout android:id="@+id/cl_toolpage_container"/>               <!-- 工具页容器 -->
</ConstraintLayout>
```

### 3.2 资源组织

`app/build.gradle` 使用多 `res-*` 目录按功能组织资源：

```gradle
res.srcDirs += 'src/main/res-content'      // 内容相关
res.srcDirs += 'src/main/res-explorer'     // 文件浏览器
res.srcDirs += 'src/main/res-tabtool'      // 工具页
res.srcDirs += 'src/main/res-codeeditor'   // 代码编辑器
res.srcDirs += 'src/main/res-fileicon'     // 文件图标
res.srcDirs += 'src/main/res-setting'      // 设置
res.srcDirs += 'src/main/res-plugin'       // 插件
```

---

## 4. 内容管理系统

### 4.1 核心组件

内容管理采用 **ViewPager + TabLayout** 模式：

- **`ContentManagePresenter`**：管理内容的添加/删除/显示，实现 `ViewPager.OnPageChangeListener`
- **`ContentViewPager`**：自定义 ViewPager，承载所有打开的内容页
- **`IContent`** 接口：所有可打开内容的抽象（文件、设置页、Guider 等）
- **`ContentFragment`**：内容的基础 Fragment 实现

### 4.2 CodeEditorFragment

代码编辑器的内容封装：

```kotlin
open class CodeEditorFragment(
    val main: IMainView,
    path: String,
    val binding: ContentsCodeeditorBinding = ...
) : ContentFragment(...) {
    val codeEditor = binding.codeIeditorEditor  // 核心编辑器 View

    init {
        // 初始化快捷菜单（搜索、只读切换）
        quickMenuList.add(QuickMenu(...))
    }

    override fun initView(view: View) {
        initCodeEditor(codeEditor)        // 配置语言、词法任务
        initCodeEditorSetting(codeEditor) // 配置主题、设置

        // 初始化各种浮动布局
        selectOperateLayout = SelectOperateLayout(...)     // 选择操作
        quickControlLayout = QuickControlLayout(...)       // 快捷控制
        quickEditLayout = QuickEditLayout(...)             // 快捷编辑
        quickInputLayout = QuickInputLayout(...)           // 快捷输入
        findReplaceLayout = FindReplaceLayout(...)         // 查找替换
    }
}
```

### 4.3 Guider 内容

当没有文件打开时显示引导界面：

```
+-- 文件 ------------------+
| 新建文件                 |
| 打开文件                 |
| 从模板创建               |
+-- 项目 ------------------+
| 新建项目                 |
| 打开项目                 |
+-- 帮助 ------------------+
| 应用设置                 |
| 应用介绍                 |
| 法律信息                 |
+--------------------------+
```

---

## 5. 窗口与 Explorer 系统

### 5.1 Explorer 窗口

`ExplorerWindowPresenter` 管理文件浏览器弹窗：

- 内部集成 `FileExplorerFragment`，基于 RecyclerView 展示文件列表
- 支持文件筛选（`EditText` 实时过滤）
- 文件点击 -> 通过 `fileOpenManager.requestOpen()` 打开
- 菜单功能：定位当前文件、刷新、新建文件/文件夹、从模板创建

### 5.2 ToolPage 窗口

`ToolPageWindowPresenter` 管理底部工具页，通过 `TabToolInternal` 维护：

```kotlin
class TabToolInternal(val main: IMainView) {
    lateinit var eventTabToolFragment: EventToolPageFragment      // 事件日志
    lateinit var errorTabFragment: TipsToolGroupFragment          // 错误提示
    lateinit var searchTabFragment: SearchToolPageFragment        // 全局搜索
    lateinit var logcatTabFragment: LogcatToolPageFragment        // Logcat
}
```

### 5.3 窗口状态管理

窗口系统使用 `WindowStateObserver` 回调模式管理显示/隐藏状态：

```kotlin
interface WindowStateObserver<T> {
    fun onWindowShow(window: T)
    fun onWindowHide(window: T)
}
```

---

## 6. CodeEditor 模块概述

CodeEditor 模块是 TaoKDao 的核心编辑器组件，提供：

- **自定义文本渲染**：直接通过 Canvas 绘制文本，非系统 TextView
- **语法高亮**：基于词法分析器（LexTask）生成颜色 Span
- **LSP 支持**：集成 LSP4J 提供代码补全、诊断等功能
- **ANTLR 支持**：CodeEditorAntlr 模块提供基于 ANTLR4 的语言解析
- **Gap Buffer 文本存储**：高效的插入/删除操作
- **自动换行**：支持长行自动换行显示
- **代码块可视化**：括号匹配、代码块竖线
- **放大镜、滚动条、自动补全面板**

---

## 7. 编辑器继承链与核心类

### 7.1 继承层次

```
android.view.ViewGroup
    └── EditorAbstract              // 基础 ViewGroup，字符选择器映射
        └── EditorField             // 核心编辑器：光标、滚动、输入、绘制
            └── CodeTextField       // 默认配置、UI 状态保存/恢复
                └── CodeEditor      // 快捷键、字体加载、LSP 入口
```

### 7.2 核心类职责

| 类 | 文件 | 职责 |
|----|------|------|
| `EditorField` | `view/EditorField.java` | 核心编辑器：光标管理、选择、滚动、输入事件、onDraw |
| `EditorController` | `view/EditorController.java` | 编辑控制器：字符插入/删除、撤销/重做、触发词法分析 |
| `EditorDrawer` | `praser/prog/EditorDrawer.java` | 绘制引擎：文本/选择/光标/行号的 Canvas 绘制 |
| `LineDrawer` | `praser/prog/LineDrawer.java` | 行相关绘制：行号、行背景、当前行高亮、代码块竖线 |
| `DocumentProvider` | `doc/DocumentProvider.java` | 文本访问接口：行/列转换、字符迭代 |
| `Document` | `doc/Document.java` | 文档模型：Gap Buffer + 自动换行 |
| `TextBuffer` | `doc/TextBuffer.java` | 底层字符存储：Gap Buffer 实现 |
| `LexTask` | `LexTask.java` | 词法分析抽象：生成 Span、SymbolPair、BlockLine |
| `Lexer` | `Lexer.java` | 词法分析管理器：后台线程调度 LexTask |
| `ColorScheme` | `view/ColorScheme.java` | 配色方案：语法元素到颜色的映射 |

### 7.3 EditorField 关键属性

```java
public abstract class EditorField extends EditorAbstract {
    public EditorSetting setting;           // 编辑器设置（字体、颜色、行为）
    public EditorDrawer drawer;             // 绘制引擎
    protected EditorController fieldController;  // 编辑控制器
    protected int caretPosition = 0;        // 光标字符位置
    protected int _selectionAnchor = -1;    // 选择起点（inclusive）
    protected int _selectionEdge = -1;      // 选择终点（exclusive）
    protected TouchNavigationMethod _navMethod;  // 触摸导航
    protected OverScroller scroller;        // 滚动器
    EditorInputConnection _inputConnection; // 输入法连接
    AutoCompletePanel _autoCompletePanel;   // 自动补全面板
}
```

---

## 8. 文本数据模型

### 8.1 Gap Buffer（间隙缓冲区）

`TextBuffer` 使用 Gap Buffer 数据结构存储文本，核心思想：

```
文本: "Hello World"
缓冲区: [H][e][l][l][o][ ][W][o][r][l][d][_][_][_][_]
                              ^
                           gapStart (光标位置)
                            gapEnd
```

- **插入**：在 gap 处直接写入，移动 gapEnd
- **删除**：移动 gapStart，不实际删除字符
- **优势**：在光标附近编辑操作 O(1)
- **缺点**：随机访问需要索引转换

### 8.2 Document（自动换行层）

`Document` 继承 `TextBuffer`，增加自动换行能力：

```java
public class Document extends TextBuffer {
    private boolean _isWordWrap = false;
    private ArrayList<Integer> _rowTable;  // 每行首字符的偏移量

    // 编辑后局部更新换行表
    void updateWordWrapAfterEdit(int startRow, int analyzeEnd, int delta)
}
```

### 8.3 DocumentProvider（访问接口）

`DocumentProvider` 作为迭代器封装文档访问：

```java
public class DocumentProvider {
    public int findRowNumber(int charOffset);     // 字符偏移 -> 行号
    public int getRowOffset(int rowNumber);       // 行号 -> 首字符偏移
    public int getRowSize(int rowNumber);         // 行长度
    public String getRow(int rowNumber);          // 获取行文本
    public char charAt(int charOffset);           // 获取字符
    public void setSpans(List<Span> spans);       // 设置语法高亮 Span
}
```

---

## 9. 渲染绘制流程

### 9.1 整体绘制调用链

```
EditorField.onDraw(Canvas)
    ├── canvas.clipRect()              // 裁剪到内容区域（含 padding）
    ├── canvas.translate()             // 应用 padding 偏移
    ├── drawer.realDraw(canvas, this)  // 核心绘制
    │   ├── lineDrawer.drawOptionHighlightRow()   // 当前行高亮背景
    │   ├── lineDrawer.drawBlockLine()            // 代码块竖线
    │   ├── resetLeftOffset()                     // 计算行号区域宽度
    │   ├── lineDrawer.drawLineNumberBackground() // 行号背景
    │   └── [逐行逐字符绘制文本]                     // 核心文本绘制循环
    ├── _navMethod.onTextDrawComplete() // 触摸导航绘制（如放大镜）
    └── _scrollbar.draw(canvas)         // 滚动条绘制
```

### 9.2 EditorDrawer.realDraw 详解

`realDraw()` 是渲染的核心方法（约 150 行），流程如下：

**Step 1：初始化绘制参数**

```java
void realDraw(Canvas canvas, EditorField editorField) {
    // 1. 绘制当前行高亮背景
    lineDrawer.drawOptionHighlightRow(canvas, editorField);

    // 2. 绘制代码块竖线
    lineDrawer.drawBlockLine(canvas, editorField);

    // 3. 计算左侧行号区域宽度
    resetLeftOffset(editorField);

    // 4. 绘制行号背景
    if (editorField.setting.isShowLineNumbers) {
        lineDrawer.drawLineNumberBackground(canvas, _leftOffset, editorField);
    }
```

**Step 2：确定可见行范围**

```java
    // 根据 Canvas 裁剪区域计算可见行
    int currRowNum = Math.max(getBeginPaintRow(canvas), 0);
    int endRowNum = getEndPaintRow(canvas);

    // 获取起始行的字符偏移
    int currIndex = editorField.getDocumentProvider().getRowOffset(currRowNum);
```

**Step 3：获取语法 Span 列表**

```java
    // 从 DocumentProvider 获取已分析的 Span 列表
    List<Span> spans = editorField.getDocumentProvider().getSpans();
    int spanSize = spans.size();

    // 二分查找当前字符对应的 Span
    int spanIndex = binarySearchSpan(spans, currIndex);
    Span currSpan = spans.get(spanIndex);
    int spanEndIndex = currSpan.startIndex + currSpan.len;

    // 设置初始画笔颜色和字体
    _brush.setColor(currSpan.colorable.getColor());
    _brush.setTypeface(getTypeface(currSpan.colorable, editorField));
```

**Step 4：逐行逐字符绘制循环**

```java
    while (currRowNum <= endRowNum) {
        int paintX = _leftOffset;  // 从行号右侧开始

        // 绘制行号
        if (currLineNum != lastLineNum) {
            lineDrawer.drawLineNumber(canvas, paintX, currLineNum, currPaintY, editorField);
        }

        // 遍历行内每个字符
        for (int i = 0, rowLen = ...; i < rowLen; ++i) {

            // Span 切换：当前字符超出当前 Span 范围时，切换到下一个 Span
            if (spanEndIndex <= currIndex && spanIndex < spanSize - 1) {
                currSpan = spans.get(++spanIndex);
                spanEndIndex = currSpan.startIndex + currSpan.len;
                _brush.setColor(currSpan.colorable.getColor());
                _brush.setTypeface(getTypeface(currSpan.colorable, editorField));
            }

            char c = editorField.getDocumentProvider().charAt(currIndex);

            // 根据状态选择绘制方式
            if (editorField.inSelectionRange(currIndex)) {
                paintX += drawSelectedText(canvas, c, paintX, currPaintY, editorField);
            } else if (isError || editorField.getLexTask().isError(currIndex)) {
                paintX += drawErrorText(canvas, c, paintX, currPaintY, editorField);
            } else {
                paintX += drawChar(canvas, _brush, c, paintX, currPaintY, editorField);
            }

            // 绘制光标（在光标位置前一个字符绘制完成后）
            if (currIndex - 1 == editorField.getCaretPosition()) {
                drawCaret(canvas, x, currPaintY, editorField);
            }

            ++currIndex;
        }

        currPaintY += rowHeight;  // 移动到下一行
        ++currRowNum;
    }

    // 绘制括号匹配高亮
    drawBlockPairs(canvas, editorField);
}
```

### 9.3 字符绘制细节

`drawChar()` 处理特殊字符：

```java
public int drawChar(Canvas canvas, Paint _brush, char c, int paintX, int paintY, EditorField editorField) {
    switch (c) {
        case 0xd83c: case 0xd83d:  // Emoji 高代理位
            _emoji = c;
            return 0;  // 不绘制，等低代理位
        case ' ':
            if (editorField.setting.isShowNonPrinting)
                drawNonPrinting(canvas, Language.GLYPH_SPACE, paintX, paintY);
            else
                canvas.drawText(" ", 0, 1, paintX, paintY, _brush);
            return _spaceWidth;
        case Language.TAB:
            if (editorField.setting.isShowNonPrinting)
                drawNonPrinting(canvas, Language.GLYPH_TAB, paintX, paintY);
            return getTabAdvance(editorField, paintX);
        case Language.NEWLINE: case Language.EOF:
            if (editorField.setting.isShowNonPrinting)
                drawNonPrinting(canvas, Language.GLYPH_NEWLINE, paintX, paintY);
            return getEOLAdvance(editorField);
        default:
            if (_emoji != 0) {
                // 绘制 Emoji（代理对）
                canvas.drawText(new char[]{_emoji, c}, 0, 2, paintX, paintY, _brush);
                _emoji = 0;
            } else {
                canvas.drawText(new char[]{c}, 0, 1, paintX, paintY, _brush);
            }
            return (int) _brush.measureText(new char[]{c}, 0, 1);
    }
}
```

### 9.4 可见区域优化

```java
// 只绘制视口内的内容
int drawXStart = editorField.getScrollX() + editorField.getPaddingLeft() - tabWidth;
int drawXEnd = editorField.getWidth() + editorField.getScrollX() - padding...

if (drawXStart < paintX && paintX < drawXEnd) {
    // 在视口内，实际绘制
} else {
    // 在视口外，只计算宽度不绘制
    paintX += editorField.getAdvance(c);
}
```

---

## 10. 语法高亮机制

### 10.1 Span 模型

`Span` 表示一段连续文本的样式：

```java
public class Span {
    public int line;            // 所在逻辑行
    public int lineOffset;      // 行内偏移
    public int startIndex;      // 文档全局起始位置
    public int len;             // 长度
    public Colorable colorable; // 颜色类型（KEYWORD, COMMENT, STRING 等）
    public FONTTYPE fontType;   // 字体类型（NORMAL, BOLD, ITALIC）
}
```

### 10.2 词法分析流程

```
编辑操作（插入/删除字符）
    │
    ▼
EditorController.onPrintableChar()
    │
    ├── 修改 Document（Gap Buffer）
    ├── 调用 determineSpans()
    │       └── Lexer.tokenize(DocumentProvider)
    │               └── 启动后台线程执行 LexTask.run()
    │                       └── 语言特定的词法分析
    │                               └── 生成 List<Span>
    │
    └── LexTask 完成后回调 lexDone(List<Span>)
            └── UI 线程：DocumentProvider.setSpans(results)
                    └── invalidate() 触发重绘
```

### 10.3 LexTask 抽象

```java
public abstract class LexTask<TOKEN, TYPE> implements Runnable {
    protected abstract ColorScheme.Colorable getTokenColorable(TYPE tokenType);

    // 分析过程中收集的数据结构
    protected ArrayList<Variable> variableStacks;       // 变量作用域
    protected ArrayList<BlockLine> lineCurlyStacks;     // 大括号块行
    protected ArrayList<SymbolPair> pairParenStacks;    // 括号对
    protected ArrayList<SymbolPair> pairCurlyStacks;    // 大括号对
    protected ArrayList<SymbolPair> pairQuoteStacks;    // 引号对
}
```

### 10.4 颜色到字体的映射

```java
public Typeface getTypeface(ColorScheme.Colorable c, EditorField editorField) {
    if (c == ColorScheme.Colorable.KEYWORD)
        return editorField.setting.boldTypeface;       // 关键字 = 粗体
    if (c == ColorScheme.Colorable.COMMENT_REGION)
        return editorField.setting.italicTypeface;     // 注释 = 斜体
    return editorField.setting.defTypeface;             // 默认 = 常规
}
```

---

## 11. 光标与选择系统

### 11.1 光标绘制

```java
public void drawCaret(Canvas canvas, int paintX, int paintY, EditorField editorField) {
    int caretX = paintX - editorField.setting.caretWidth / 2;
    int caretY = paintY;
    _brush.setColor(ColorScheme.Colorable.CARET_DISABLED.getColor());
    // 绘制实心矩形作为光标
    drawTextBackground(canvas, _brush, caretX, paintY, editorField.setting.caretWidth);
}
```

### 11.2 选择文本绘制

```java
public int drawSelectedText(Canvas canvas, char c, int paintX, int paintY, EditorField editorField) {
    int advance = getAdvance(editorField, c, paintX);

    // 1. 绘制选择背景
    _brush.setColor(ColorScheme.Colorable.SELECTION_BACKGROUND.getColor());
    drawTextBackground(canvas, _brush, paintX, paintY, advance);

    // 2. 绘制选择前景文字
    _brush.setColor(ColorScheme.Colorable.SELECTION_FOREGROUND.getColor());
    drawChar(canvas, _brush, c, paintX, paintY, editorField);

    return advance;
}
```

### 11.3 选择模式

```java
// 进入/退出选择模式
public void selectText(boolean mode) {
    if (fieldController.isSelectText()) {
        if (!mode) {
            invalidateSelectionRows();  // 重绘选择区域
            fieldController.setSelectText(false);
        }
    } else if (mode) {
        invalidateCaretRow();           // 重绘光标行
        fieldController.setSelectText(true);
    }
}
```

---

## 12. 滚动与触摸导航

### 12.1 滚动系统

```java
public abstract class EditorField extends EditorAbstract {
    public OverScroller scroller;          // Android OverScroller
    protected TouchNavigationMethod _navMethod;  // 触摸导航策略

    // Fling 滚动
    void flingScroll(int velocityX, int velocityY) {
        scroller.fling(getScrollX(), getScrollY(), velocityX, velocityY,
            minX, maxX, minY, maxY);
        postInvalidate();
    }

    // 动画滚动
    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            postInvalidate();
        }
    }
}
```

### 12.2 触摸导航

`TouchNavigationMethod` 处理触摸事件：

- **单击**：移动光标到点击位置
- **长按**：进入选择模式
- **拖动**：滚动或扩展选择
- **双击**：选中单词
- **Fling**：惯性滚动

### 12.3 光标自动滚动

当选择操作超出可视区域时，自动滚动：

```java
boolean autoScrollCaret(int scrollDir) {
    switch (scrollDir) {
        case SCROLL_UP:
            post(_scrollCaretUpTask);    // 每 250ms 上移一行
        case SCROLL_DOWN:
            post(_scrollCaretDownTask);  // 每 250ms 下移一行
        // ...
    }
}
```

---

## 13. 行号与代码块可视化

### 13.1 行号绘制

```java
// LineDrawer.drawLineNumber()
public void drawLineNumber(Canvas canvas, int leftOffset, int num, int paintY, EditorField editorField) {
    String s = String.valueOf(num);
    // 右对齐，居中于行号区域
    float x = (leftOffset - _brushLineNumber.measureText(s) - drawer.getSpaceWidth() / 2f) / 2;
    canvas.drawText(s, x, paintY, _brushLineNumber);
}
```

### 13.2 行号背景与当前行高亮

```java
// 行号区域背景
public void drawLineNumberBackground(Canvas canvas, int leftOffset, EditorField editorField) {
    int width = leftOffset - drawer.getSpaceWidth() / 4;
    canvas.drawRect(0, editorField.getScrollY(),
        width, editorField.getScrollY() + editorField.getHeight(),
        _brushLineBackground);
}

// 当前行高亮
public void drawOptionHighlightRow(Canvas canvas, EditorField editorField) {
    if (editorField.setting.isHighlightCurrentRow) {
        int y = drawer.getPaintBaseline(drawer.getCaretRow());
        drawLineBackground(canvas, 0, y, editorField.getScrollX() + width);
    }
}
```

### 13.3 代码块竖线

```java
// LineDrawer.drawBlockLine()
public void drawBlockLine(Canvas canvas, EditorField editorField) {
    if (editorField.setting.isShowBlockRegionLines && !editorField.isWordWrap()) {
        List<BlockLine> lines = editorField.getLexTask().getBlockRegionLinesList();
        for (BlockLine rect : lines) {
            int top = (rect.startLine) * drawer.getRowHeight();
            int bottom = (rect.endLine - 1) * drawer.getRowHeight();
            int left = Math.min(
                drawer.getCharExtent(rect.startIndex, editorField).first,
                drawer.getCharExtent(rect.endIndex, editorField).first
            );
            // 绘制竖线
            canvas.drawLine(left, top, left, bottom, _brushLine);
        }
    }
}
```

### 13.4 括号匹配高亮

```java
// EditorDrawer.drawBlockPairs()
public void drawBlockPairs(Canvas canvas, EditorField editorField) {
    if (currPair != null && !editorField.isSelectText() && !editorField.isWordWrap()) {
        // 高亮匹配的括号字符
        drawPairBackWithground(canvas, editorField.getDocumentProvider().charAt(currPair.left),
            open.first, openY - metrics.ascent, editorField);
        drawPairBackWithground(canvas, editorField.getDocumentProvider().charAt(currPair.right),
            close.first, closeY - metrics.ascent, editorField);
    }
}
```

---

## 14. LSP 集成

### 14.1 LSP4J 依赖

CodeEditor 模块依赖 LSP4J：

```gradle
implementation 'org.eclipse.lsp4j:org.eclipse.lsp4j:0.10.0'
```

### 14.2 集成入口

`CodeEditor` 类导入 LSP Launcher：

```java
import org.eclipse.lsp4j.launch.LSPLauncher;
```

### 14.3 自动补全面板

```java
// CodeEditorFragment 中配置自动补全
editor.autoCompletePanel.apply {
    setAdapter(AutoCompleteAdapter(main.context))
    setBackground(ColorDrawable(...))
    panel.animationStyle = R.style.CodeEditor_AutoComplete_PopupAnimation
    panel.setListSelector(...)
}
```

### 14.4 语言扩展体系

```java
// Language 抽象
public abstract class Language {
    public abstract LexTask newLexTask();     // 创建词法分析器
    public abstract boolean isWordWrap();     // 是否自动换行
}

// 语言注册
class CodeEditorLanguageManager {
    fun getLanguageForSuffix(suffix: String): Language? {
        // 根据文件后缀返回对应语言
    }
}
```

---

## 附录：关键文件索引

### UI 结构相关

| 文件 | 路径 |
|------|------|
| MainActivity | `app/src/main/java/taokdao/main/MainActivity.kt` |
| IMainView | `app/src/main/java/taokdao/main/IMainView.kt` |
| 主布局 | `app/src/main/res/layout/activity_main.xml` |
| ContentManagePresenter | `app/src/main/java/taokdao/main/business/content_manage/ContentManagePresenter.kt` |
| CodeEditorFragment | `app/src/main/java/taokdao/codeeditor/CodeEditorFragment.kt` |
| FileExplorerFragment | `app/src/main/java/taokdao/window/explorers/fileexplorer/FileExplorerFragment.kt` |
| GuiderContent | `app/src/main/java/taokdao/content/guider/GuiderContent.kt` |
| TabToolInternal | `app/src/main/java/taokdao/window/toolpages/TabToolInternal.kt` |

### CodeEditor 渲染相关

| 文件 | 路径 |
|------|------|
| CodeEditor | `CodeEditor/src/main/java/tiiehenry/code/view/CodeEditor.java` |
| CodeTextField | `CodeEditor/src/main/java/tiiehenry/code/view/CodeTextField.java` |
| EditorField | `CodeEditor/src/main/java/tiiehenry/code/view/EditorField.java` |
| EditorAbstract | `CodeEditor/src/main/java/tiiehenry/code/view/EditorAbstract.java` |
| EditorDrawer | `CodeEditor/src/main/java/tiiehenry/code/praser/prog/EditorDrawer.java` |
| LineDrawer | `CodeEditor/src/main/java/tiiehenry/code/praser/prog/LineDrawer.java` |
| EditorController | `CodeEditor/src/main/java/tiiehenry/code/view/EditorController.java` |
| DocumentProvider | `CodeEditor/src/main/java/tiiehenry/code/doc/DocumentProvider.java` |
| Document | `CodeEditor/src/main/java/tiiehenry/code/doc/Document.java` |
| TextBuffer | `CodeEditor/src/main/java/tiiehenry/code/doc/TextBuffer.java` |
| LexTask | `CodeEditor/src/main/java/tiiehenry/code/LexTask.java` |
| Lexer | `CodeEditor/src/main/java/tiiehenry/code/Lexer.java` |
| Span | `CodeEditor/src/main/java/tiiehenry/code/praser/Span.java` |
| ColorScheme | `CodeEditor/src/main/java/tiiehenry/code/view/ColorScheme.java` |
| Language | `CodeEditor/src/main/java/tiiehenry/code/language/Language.java` |
