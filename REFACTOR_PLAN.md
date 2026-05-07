# TaoKDao 重构方案

**编制日期**: 2026/05/06
**范围**: TaoKDao 主项目 + TaoKDao-API 复合构建
**目标**: 修复构建问题、消除 God Activity、简化 MVP 样板、统一状态管理

---

## 一、项目现状概览

### 1.1 模块结构

| 项目 | 模块 | 类型 | 文件数 | 状态 |
|---|---|---|---|---|
| TaoKDao | `:app` | Application | 352 | 活跃 |
| TaoKDao | `:common` | Library | 38 | 活跃 |
| TaoKDao | `:CodeEditor` | Library | 58 | 活跃 |
| TaoKDao | `:CodeEditorAntlr` | Library | 187 | 活跃 |
| TaoKDao | `:DynamicLoader` | Library | 24 | 活跃 |
| TaoKDao-API | `:api_public` | Library | 235 | 活跃 |
| TaoKDao-API | `:api_dialog` | Library | 59 | 活跃 |
| TaoKDao-API | `:dialog_mddialogs` | Library | 34 | 活跃 |
| TaoKDao-API | `:dialog_md3` | Library | 33 | 活跃 |

### 1.2 关键统计

- **总代码行数**: `app/src/main/java/` 约 29,156 行
- **MVP 三元组**: 30 Contract + 30 Presenter + 31 Model + 33 View + 7 ViewWrapper = 131 个 MVP 相关文件
- **MainActivity 规模**: 282 行，实现 30 个 View 接口，直接实例化 30 个 Presenter
- **Presenter 规模分布**:
  - < 20 行: 8 个（ActionProcess 10 行、ProjectBuild 10 行、DialogManager 11 行等）
  - 20-50 行: 6 个
  - 50-100 行: 6 个
  - 100-200 行: 5 个
  - 200+ 行: 1 个（ContentManagePresenter 293 行）

---

## 二、问题诊断

### 2.1 架构层（最高优先级）

#### God Activity 反模式

**位置**: `app/src/main/java/taokdao/main/MainActivity.kt:80-87`

```kotlin
class MainActivity : BaseMainActivity(), FileOpenView, FileOperateView, CategoryMenuView,
    ProgressUseView, SessionControlView, PermissionRequestView, ProjectBuildView,
    PluginManageView, ProjectManageView, ScreenControlView, ThemeManageView, ExitControlView,
    ActionProcessView, MainSettingView, PluginInstallView, FileTemplateGenerateView,
    DrawableManageView, ProjectTemplateView, LanguageManageView, MMKVManageView, DexLoadView,
    BuildManageView, PluginLoadView, ContentManageView, ExplorerDisplayView,
    ToolPageDisplayView, ToolBarLayoutView, DialogManagerView, FileProviderView,
    IndicateManageView
```

**影响**:
- 任何功能改动都需要修改 MainActivity
- 单元测试几乎不可能（需要 mock 30 个接口）
- 启动时一次性创建 30 个 Presenter，冷启动慢
- 内存中常驻 30 个 Presenter 实例

#### 贫血 Model 层

**空 Model 示例**:

```kotlin
// ContentManageModel.kt —— 完全为空
class ContentManageModel : ContentManageContract.M

// PermissionRequestModel.kt —— 完全为空
class PermissionRequestModel : PermissionRequestContract.M

// DialogManagerModel.kt —— 完全为空
class DialogManagerModel : DialogManagerContract.M
```

**统计**: 31 个 Model 中约 40% 无任何实现，剩余大部分仅做简单的 MMKV 读写代理。

**结论**: MVP 退化为 VP 模式，业务逻辑全部堆积在 Presenter。

#### 紧耦合的跨 Presenter 访问

Presenters 不直接通信，而是通过 View（即 MainActivity）间接访问其他 Presenter:

```kotlin
// SessionControlPresenter.kt
model.preserveProject(view.mmkvManager, view.projectManager.project?.projectDir?.absolutePath ?: "")
for (item in view.contentManager.list) { ... }

// BuildManagePresenter.kt
val path = view.contentManager.current?.path ?: return
if (!view.projectManager.isOpenedProject) { return }

// ContentManagePresenter.kt
view.main.toolBarLayoutPresenter.refreshQuickMenu(currentContent?.quickMenuList ?: listOf())
```

**风险**:
- 隐性的循环依赖（A Presenter -> View -> B Presenter -> View -> A Presenter）
- 重构时无法安全删除 Presenter（不知道谁通过 view.xxxPresenter 引用它）
- MainActivity 被迫暴露所有内部对象

#### 状态碎片化

| 状态类型 | 存放位置 |
|---|---|
| 插件列表 | `PluginManageModel.pluginList` / `pluginManifestList` |
| 打开文件 | `ContentManagePresenter.currentContent` + `ContentManageViewWrapper.contentAdapter.dataList` |
| 当前工程 | `ProjectManageModel` 内部引用 |
| 会话配置 | `SessionControlVariable` 全局可变对象 |
| 主题模式 | `ThemeManagePresenter.uiMode` |
| 构建偏好 | `BuildManageModel` MMKV |

**没有单一数据源**，同一状态可能在 Presenter、ViewWrapper、Model、全局对象中各存一份。

### 2.2 构建与依赖层

| 问题 | 位置 | 严重性 | 说明 |
|---|---|---|---|
| ProGuard 保留所有公开成员 | `app/proguard-rules.pro:52` | **高** | `-keep class * { public *; }` 使混淆基本失效 |
| 缺失 consumer ProGuard 文件 | `api_public/build.gradle:16` | **高** | 引用 `consumer-rules-kotlin.pro` 但文件不存在 |
| LSP4J 版本冲突 | `CodeEditor/build.gradle` vs `CodeEditorAntlr/build.gradle` | 中 | 1.0.0 vs 0.10.0 同时出现在 classpath |
| Anko 已废弃 | `app/build.gradle` | 中 | JetBrains 官方停止维护 |
| 动态版本号 | `app/build.gradle`（`api_public:+` 等） | 中 | 非可复现构建 |
| NDK 版本不一致 | `gradle.properties` vs `build.gradle` | 低 | 27.1 vs 28.2 |
| Lint 在 Release 禁用 | `app/build.gradle` | 低 | `checkReleaseBuilds false` |
| 空模块 | `TaoKDao-API/api_skin/` | 低 | 有 build.gradle 但无源码 |

### 2.3 代码质量问题

| 问题 | 规模 | 位置 |
|---|---|---|
| `e.printStackTrace()` | 68 处 | 几乎所有 Presenter 和工具类 |
| 重复 MVP 样板 | 29/30 Presenters 相同结构 | 每个 business/ 子包 |
| 注释掉的代码块 | 大量 | `ContentManagePresenter.kt`（FileObserver）、`MainActivity.kt`、`settings.gradle` |
| 超大文件 | 4,424 行 | `BytesEncodingDetect.java` |
| 超大文件 | 3,220 行 | `TabLayout.java`（vendored） |

---

## 三、重构路线图

### Phase 1: 构建安全修复（立即执行，零行为变更）

**目标**: 消除构建隐患，为后续重构创造安全基线。

| # | 任务 | 文件 | 具体操作 |
|---|---|---|---|
| 1.1 | 收紧 ProGuard 规则 | `app/proguard-rules.pro` | 删除/替换 `-keep class * { public *; }`，按包配置 `-keep class tiiehenry.taokdao.** { *; }` 和 `-keep class taokdao.main.** { *; }` |
| 1.2 | 补全缺失 ProGuard | `api_public/build.gradle` | 创建空文件 `consumer-rules-kotlin.pro` 或从 `consumerProguardFiles` 移除该引用 |
| 1.3 | 统一 LSP4J | `CodeEditorAntlr/build.gradle` | 将 `org.eclipse.lsp4j:org.eclipse.lsp4j:0.10.0` 升级到 `1.0.0` |
| 1.4 | 移除 Anko | `app/build.gradle` | 删除 `org.jetbrains.anko:*:0.10.8` 依赖，替换代码中的 `anko-commons` 调用为标准 Kotlin/Android API |
| 1.5 | 锁定动态版本 | `app/build.gradle` | 所有 `+` 改为固定版本（如 `api_public:1.3.1`） |
| 1.6 | 对齐 NDK | `gradle.properties` | 将 `android.ndkVersion=27.1.12297006` 改为 `28.2.13676358` |
| 1.7 | 清理遗留文件 | 根目录 | 删除未使用的 `dependencies.gradle`、`properties/ext-build_plugins.gradle`、`properties/ext-sdk_versions.gradle` |
| 1.8 | 删除空模块 | `TaoKDao-API/settings.gradle` | 移除 `:api_skin` 及其目录 |

**验收标准**:
- `./gradlew assembleRelease` 成功
- `./gradlew assembleDebug` 成功
- ProGuard mapping.txt 体积明显缩小
- 无 `consumer-rules-kotlin.pro` 相关构建警告

---

### Phase 2: MVP 瘦身与 God Activity 解耦（核心重构）

**目标**: 将 MainActivity 从 30 个 View 接口中解放出来，消除无意义的三层结构。

#### 2.1 微型 Presenter 合并

**标准**: Presenter < 25 行且无复杂逻辑 -> 直接删除，逻辑上提到 Manager 或内联。

| Presenter | 行数 | 合并目标 |
|---|---|---|
| `ActionProcessPresenter` | 10 | 合并到 `ContentManagePresenter` 或作为扩展函数 |
| `CategoryMenuPresenter` | 9 | 合并到 `ToolBarLayoutPresenter` |
| `DialogManagerPresenter` | 11 | 直接删除，对话框由调用方自行管理 |
| `ExitControlPresenter` | 18 | 合并到 `MainActivity` 或作为 `Application` 扩展 |
| `MMKVManagePresenter` | 16 | 提取为 `MMKVProvider` 单例/接口，删除 Presenter |
| `MainSettingPresenter` | 16 | 合并到 `SettingActivity` 或 `MainActivity` |
| `IndicateManagePresenter` | 19 | 保留但直接由 `IndicatorManager` 实现，无需 Presenter |
| `ProjectBuildPresenter` | 10 | 合并到 `BuildManagePresenter` |
| `ProjectTemplatePresenter` | ~20 | 合并到 `FileTemplateGeneratePresenter` |
| `FileOperatePresenter` | 15 | 逻辑移至 `FileOperatorPool` 或 `IFileOperateManager` 实现 |

**预期减少**: 约 10 个 Presenter + 10 个 Contract + 10 个 Model + 10 个 View = **40 个文件**

#### 2.2 空 Model 清理

**标准**: Model 无任何字段和方法实现 -> 删除，Contract 中的 `M` 接口改为可选。

**涉及文件**:
- `ContentManageModel.kt`
- `PermissionRequestModel.kt`
- `DialogManagerModel.kt`
- `ActionProcessModel.kt`
- `CategoryMenuModel.kt`
- `ExitControlModel.kt`
- `MMKVManageModel.kt`
- `MainSettingModel.kt`
- `IndicateManageModel.kt`
- `ProjectBuildModel.kt`

**操作步骤**:
1. 删除 Model 类文件
2. 从 Contract 中移除 `interface M`（如果无实际实现者）
3. Presenter 中删除 `private val model = XxxModel()` 字段
4. 若 Presenter 原通过 model 调用，直接内联逻辑或提取为 Repository

#### 2.3 提取 AppContainer（依赖注入容器）

**新文件**: `app/src/main/java/taokdao/main/di/AppContainer.kt`

```kotlin
class AppContainer(private val mainActivity: MainActivity) {
    val fileOpenManager: IFileOpenManager by lazy { FileOpenManagerImpl(mainActivity) }
    val pluginManager: IPluginManager by lazy { PluginManagerImpl(mainActivity) }
    val contentManager: IContentManager by lazy { ContentManagerImpl(mainActivity) }
    val projectManager: IProjectManager by lazy { ProjectManagerImpl(mainActivity) }
    val themeManager: IThemeManager by lazy { ThemeManagerImpl(mainActivity) }
    // ... 其他 Manager
}
```

**MainActivity 改造**:

```kotlin
class MainActivity : BaseMainActivity(), IMainView {
    lateinit var container: AppContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        container = AppContainer(this)
        // 不再直接实例化 30 个 Presenter
    }

    // IMainView 只暴露 IMainContext 方法，不暴露具体 Presenter
}
```

**收益**:
- MainActivity 不再知道每个功能的具体 Presenter 存在
- Manager 实现类可在 AppContainer 中替换为 mock，方便测试
- 初始化顺序由 AppContainer 的 `lazy` 依赖关系自然决定

#### 2.4 Presenter 不再持有 View，改为持有 IMainContext

**现状**: 每个 Presenter 构造函数接收 `XxxContract.V`（即 MainActivity）。

**改造**:

```kotlin
// 改造前
class PluginManagePresenter(private val view: PluginManageContract.V) : PluginManageContract.P

// 改造后
class PluginManagePresenter(
    private val context: IMainContext,
    private val view: PluginManageView
) : PluginManageContract.P
```

其中 `IMainContext` 提供 `getPluginManager()`、`getContentManager()` 等服务访问，不包含具体 Presenter 引用。

**关键改动**:
- 跨 Presenter 调用（如 `view.main.toolBarLayoutPresenter.refreshQuickMenu(...)`）改为 `context.getToolBarManager().refreshQuickMenu(...)`
- `IMainView` 不再暴露 `val xxxPresenter: XxxPresenter` 属性
- 每个功能只依赖 API 接口（`IContentManager`、`IPluginManager`），不依赖其他 Presenter

#### 2.5 初始化编排声明化

**现状**: `MainActivity.onPrepareStartupStage()` 手动按序调用 20+ 个初始化方法。

**改造**: 使用 Stage 模式

```kotlin
enum class StartupStage(val order: Int) {
    THEME(1),
    UI_FRAMEWORK(2),
    WINDOW(3),
    PLUGIN_ENGINE(4),
    PLUGIN_COMMON(5),
    SESSION_RESTORE(6)
}

interface StartupTask {
    val stage: StartupStage
    fun execute()
}
```

各 Manager/Service 在初始化时注册自己的 `StartupTask`，`MainActivity` 只需按 `stage.order` 排序后统一执行。

**收益**:
- 新增功能无需修改 MainActivity 的初始化代码
- 初始化顺序显式可见
- 支持同阶段并行初始化

---

### Phase 3: 状态管理统一（数据层重构）

#### 3.1 集中式 Settings Repository

**新文件**: `app/src/main/java/taokdao/data/settings/SettingsRepository.kt`

```kotlin
class SettingsRepository(private val mmkv: MMKV) {
    // 会话
    var saveSession: Boolean by mmkv.boolean("session.save", true)
    var saveOpenedFiles: Boolean by mmkv.boolean("session.save_opened", true)

    // 构建
    var defaultBuilderId: String? by mmkv.nullableString("build.default_id")

    // 插件
    var pluginEnabledMap: Map<String, Boolean> by mmkv.jsonMap("plugin.enabled")
}
```

**替换目标**:
- `SessionControlModel` / `SessionControlVariable`
- `BuildManageModel`
- `PluginManageModel` 中的 MMKV 逻辑
- `MMKVManagePresenter` 中的配置读写

#### 3.2 Content 状态流

**现状**: `currentContent`、`currIndex`、`contentAdapter.dataList` 三处维护同一状态。

**改造**: 引入单一状态持有者

```kotlin
class ContentStateHolder {
    private val _state = MutableStateFlow(ContentState())
    val state: StateFlow<ContentState> = _state.asStateFlow()

    data class ContentState(
        val contents: List<IContent> = emptyList(),
        val currentIndex: Int = -1
    ) {
        val current: IContent? get() = contents.getOrNull(currentIndex)
    }
}
```

Presenter 和 View 都观察 `StateFlow`，不再各自维护副本。

#### 3.3 Plugin 状态集中

```kotlin
class PluginStateHolder {
    private val _plugins = MutableStateFlow<List<Plugin>>(emptyList())
    val plugins: StateFlow<List<Plugin>> = _plugins.asStateFlow()

    fun loadFrom(manifests: List<PluginManifest>, enabledMap: Map<String, Boolean>) { ... }
}
```

---

### Phase 4: 模块拆分（长期演进）

**前提**: Phase 2-3 完成后，app 模块内的包之间耦合度降低，方可安全拆分。

| 新模块 | 抽取内容 | 源包 | 依赖 |
|---|---|---|---|
| `:feature:plugin` | 插件引擎、安装、加载、管理 | `taokdao.plugin.*` + plugin presenters | `:DynamicLoader`, API |
| `:feature:project` | 工程管理、工程模板、工程构建 | `taokdao.builder.*` + project presenters | `:feature:file`, API |
| `:feature:file` | 文件浏览器、文件打开器、文件模板 | `taokdao.content.filetemplate.*`, file explorers | API |
| `:feature:editor` | 代码编辑器集成、设置、语言 | `taokdao.codeeditor.*` | `:CodeEditor`, `:CodeEditorAntlr`, API |
| `:feature:window` | 工具页、资源浏览器 | `taokdao.window.*` | API |

**拆分顺序建议**:
1. `:feature:editor` — 与 `CodeEditor`/`CodeEditorAntlr` 天然关联，边界清晰
2. `:feature:plugin` — 已有 `:DynamicLoader` 基础，只需迁移 presenter 层
3. `:feature:window` — 纯 UI 层，依赖最少
4. `:feature:file` / `:feature:project` — 最后，因相互依赖较多

---

## 四、文件清单（按 Phase 分类）

### Phase 1 涉及文件

```
app/proguard-rules.pro
TaoKDao-API/api_public/build.gradle
TaoKDao-API/api_public/consumer-rules-kotlin.pro（新建）
CodeEditorAntlr/build.gradle
app/build.gradle
gradle.properties
dependencies.gradle（删除）
properties/ext-build_plugins.gradle（删除）
properties/ext-sdk_versions.gradle（删除）
properties/ext-repositories.gradle（删除）
TaoKDao-API/settings.gradle
TaoKDao-API/api_skin/（删除）
```

### Phase 2 涉及文件（核心）

**新建**:
```
app/src/main/java/taokdao/main/di/AppContainer.kt
app/src/main/java/taokdao/main/di/StartupStage.kt
app/src/main/java/taokdao/main/di/StartupTask.kt
```

**删除**（空 Model + 微型 Presenter）:
```
app/src/main/java/taokdao/main/business/action_process/ActionProcessModel.kt
app/src/main/java/taokdao/main/business/action_process/ActionProcessPresenter.kt
app/src/main/java/taokdao/main/business/action_process/ActionProcessContract.kt
app/src/main/java/taokdao/main/business/category_menu/CategoryMenuModel.kt
app/src/main/java/taokdao/main/business/category_menu/CategoryMenuPresenter.kt
app/src/main/java/taokdao/main/business/category_menu/CategoryMenuContract.kt
app/src/main/java/taokdao/main/business/dialog_manager/DialogManagerModel.kt
app/src/main/java/taokdao/main/business/dialog_manager/DialogManagerPresenter.kt
app/src/main/java/taokdao/main/business/dialog_manager/DialogManagerContract.kt
app/src/main/java/taokdao/main/business/exit_control/ExitControlModel.kt
app/src/main/java/taokdao/main/business/exit_control/ExitControlPresenter.kt
app/src/main/java/taokdao/main/business/exit_control/ExitControlContract.kt
app/src/main/java/taokdao/main/business/mmkv_manage/MMKVManageModel.kt
app/src/main/java/taokdao/main/business/mmkv_manage/MMKVManagePresenter.kt
app/src/main/java/taokdao/main/business/mmkv_manage/MMKVManageContract.kt
app/src/main/java/taokdao/main/business/main_setting/MainSettingModel.kt
app/src/main/java/taokdao/main/business/main_setting/MainSettingPresenter.kt
app/src/main/java/taokdao/main/business/main_setting/MainSettingContract.kt
app/src/main/java/taokdao/main/business/indicate_manage/IndicateManageModel.kt
app/src/main/java/taokdao/main/business/indicate_manage/IndicateManagePresenter.kt
app/src/main/java/taokdao/main/business/indicate_manage/IndicateManageContract.kt
app/src/main/java/taokdao/main/business/project_build/ProjectBuildModel.kt
app/src/main/java/taokdao/main/business/project_build/ProjectBuildPresenter.kt
app/src/main/java/taokdao/main/business/project_build/ProjectBuildContract.kt
app/src/main/java/taokdao/main/business/file_operate/FileOperateModel.kt
app/src/main/java/taokdao/main/business/file_operate/FileOperatePresenter.kt
app/src/main/java/taokdao/main/business/file_operate/FileOperateContract.kt
// ...（约 30-40 个文件）
```

**大幅修改**:
```
app/src/main/java/taokdao/main/MainActivity.kt
app/src/main/java/taokdao/main/IMainView.kt
app/src/main/java/taokdao/main/BaseMainActivity.kt
// 所有保留的 Presenter 文件（修改构造函数，不再接收 MainActivity）
```

### Phase 3 涉及文件

**新建**:
```
app/src/main/java/taokdao/data/settings/SettingsRepository.kt
app/src/main/java/taokdao/data/content/ContentStateHolder.kt
app/src/main/java/taokdao/data/plugin/PluginStateHolder.kt
```

**修改**:
```
app/src/main/java/taokdao/main/business/session_control/SessionControlPresenter.kt
app/src/main/java/taokdao/main/business/session_control/SessionControlVariable.kt
app/src/main/java/taokdao/main/business/content_manage/ContentManagePresenter.kt
app/src/main/java/taokdao/main/business/content_manage/ContentManageViewWrapper.kt
app/src/main/java/taokdao/main/business/build_manage/BuildManagePresenter.kt
app/src/main/java/taokdao/main/business/build_manage/BuildManageModel.kt
app/src/main/java/taokdao/main/business/plugin_manage/PluginManagePresenter.kt
app/src/main/java/taokdao/main/business/plugin_manage/PluginManageModel.kt
```

---

## 五、验收标准

### Phase 1
- [ ] `./gradlew clean assembleRelease` 成功，无警告
- [ ] `./gradlew clean assembleDebug` 成功
- [ ] ProGuard mapping.txt 小于改造前的 50%
- [ ] 无动态版本号 `+` 残留

### Phase 2
- [ ] MainActivity 实现的接口数量从 30 降至 <= 5（`IMainView`、`CoroutineScope` 等核心接口）
- [ ] MainActivity 中直接实例化的 Presenter 数量从 30 降至 <= 10
- [ ] 删除的文件数 >= 30 个
- [ ] 保留的 Presenter 不再通过 `view.xxxPresenter` / `view.main.xxxPresenter` 访问其他功能
- [ ] `./gradlew assembleDebug` 成功，功能无回归

### Phase 3
- [ ] 所有 MMKV 读写收敛到 `SettingsRepository`
- [ ] `SessionControlVariable` 全局对象删除
- [ ] Content 状态只有一个写入源（`ContentStateHolder`）
- [ ] Plugin 状态只有一个写入源（`PluginStateHolder`）

### Phase 4
- [ ] `:app` 模块文件数从 352 降至 <= 200
- [ ] 新增 feature 模块能独立编译
- [ ] 模块间无循环依赖

---

## 六、风险评估

| 风险 | 可能性 | 缓解措施 |
|---|---|---|
| ProGuard 收紧后运行时崩溃 | 中 | 先在 Debug 充分测试，再分步收紧规则 |
| Presenter 合并后业务逻辑遗漏 | 中 | 每次合并一个 Presenter，立即运行 |
| AppContainer 提取后初始化顺序变化 | 低 | 保留原有初始化顺序注释，逐阶段验证 |
| 状态流改造后 UI 不同步 | 中 | 用 StateFlow 的 `distinctUntilChanged` + `Lifecycle.repeatOnLifecycle` 保证 |
| 模块拆分后 API 循环依赖 | 低 | Phase 2 先解耦，确认无 `view.xxxPresenter` 调用后再拆分 |

---

## 七、附录：关键代码位置速查

| 关注点 | 路径 |
|---|---|
| MainActivity | `app/src/main/java/taokdao/main/MainActivity.kt` |
| BaseMainActivity | `app/src/main/java/taokdao/main/BaseMainActivity.kt` |
| IMainView | `app/src/main/java/taokdao/main/IMainView.kt` |
| Presenters | `app/src/main/java/taokdao/main/business/*/` |
| 应用级 build.gradle | `app/build.gradle` |
| 项目级 build.gradle | `build.gradle` |
| ProGuard 规则 | `app/proguard-rules.pro` |
| API 接口 | `../TaoKDao-API/api_public/src/main/java/taokdao/api/` |
| 插件引擎 | `app/src/main/java/taokdao/plugin/engines/apk/APKPluginEngine.kt` |
| 代码编辑器集成 | `app/src/main/java/taokdao/codeeditor/` |
