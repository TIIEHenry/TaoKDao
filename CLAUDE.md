# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

TaoKDao is an Android IDE/editor platform (similar to VSCode/IntelliJ) with a plugin system, multi-language code editor, project builder, and dynamic loader. It is built as a multi-module Gradle project with a composite build dependency on `../TaoKDao-API`.

## Build System

- **Gradle**: 9.1.0
- **Android Gradle Plugin**: 9.0.0
- **Kotlin**: 2.3.0
- **Compile SDK**: 36, **Min SDK**: 28, **Target SDK**: 36
- **Java**: 17 (sourceCompatibility/targetCompatibility)
- **NDK**: 28.2.13676358

### Common Commands

```bash
./gradlew assembleDebug          # Build debug APK
./gradlew assembleRelease        # Build release APK (ProGuard + shrinkResources)
./gradlew clean                  # Clean build outputs
./gradlew test                   # Run unit tests
./gradlew connectedAndroidTest   # Run instrumented tests (requires device/emulator)
```

### Composite Build

The project includes a composite build `../TaoKDao-API` (declared in `settings.gradle`). The `app` module consumes `tiiehenry.taokdao.api:api_public:+` from this included build. The `../TaoKDao-API` repository must be present at the expected relative path for builds to succeed.

### ABI Configuration

The app splits APKs by ABI, including only `arm64-v8a`, with `universalApk true`. This is configured in `app/build.gradle` under `android.splits.abi`.

## Module Structure

| Module | Type | Key Details |
|--------|------|-------------|
| `app` | Application | Main entry point. Application ID: `tiiehenry.taokdao`. Namespace: `tiiehenry.ideditor`. |
| `common` | Library | Namespace: `tiiehenry.android`. Provides AppCompat, RecyclerView, Fragment, ViewBinding. Used by all other modules. |
| `CodeEditor` | Library | Code editor core. Namespace: `tiiehenry.code`. Depends on LSP4J (`org.eclipse.lsp4j:0.10.0`). |
| `CodeEditorAntlr` | Library | ANTLR-based language parsing. Depends on `CodeEditor`, ANTLR4 runtime (`4.8`), and LSP4J. |
| `DynamicLoader` | Library | Dynamic plugin loading system. Namespace: `tiiehenry.android.dl.core_library`. |

### Inactive / Commented Modules

Several modules are commented out in `settings.gradle` and not part of the active build: `CodeEditorClient`, `CodeEditorAntlr-smali`, `markwon-editor`, `dx`, and others.

## Architecture

### MVP (Model-View-Presenter) Pattern

The app uses a heavy MVP pattern. `MainActivity` (`app/src/main/java/taokdao/main/MainActivity.kt`) implements 20+ View interfaces and hosts a corresponding Presenter for each. Each presenter is initialized as a lazy property and wired to the activity.

**Key presenters** (each in its own package under `app/src/main/java/taokdao/main/business/`):
- `FileOpenPresenter` / `FileOperatePresenter` — File I/O operations
- `PluginManagePresenter` / `PluginLoadPresenter` / `PluginInstallPresenter` — Plugin lifecycle
- `ProjectManagePresenter` / `ProjectBuildPresenter` — Project management and building
- `ContentManagePresenter` — Content/guider management
- `ExplorerWindowPresenter` / `ToolPageWindowPresenter` — Window/explorer UI
- `ThemeManagePresenter` / `LanguageManagePresenter` — Theming and localization
- `SessionControlPresenter` — Session save/restore
- `BuildManagePresenter` — Build orchestration

### Plugin System

Plugins are loaded dynamically via `DynamicLoader`. The app distinguishes plugin types via `PluginType.TYPE_ENGINE` and `PluginType.TYPE_COMMON`. Plugins are discovered, loaded, and initialized in `onPrepareStartupStage()` in `MainActivity`. The plugin API surface is defined in the separate `TaoKDao-API` composite build.

### Resource Organization

The `app` module uses multiple `res-*` source sets for organizational separation:

```gradle
res.srcDirs += 'src/main/res-content'
res.srcDirs += 'src/main/res-explorer'
res.srcDirs += 'src/main/res-tabtool'
res.srcDirs += 'src/main/res-codeeditor'
res.srcDirs += 'src/main/res-fileicon'
res.srcDirs += 'src/main/res-setting'
res.srcDirs += 'src/main/res-plugin'
```

## Key Dependencies

Notable dependencies outside the standard AndroidX stack:
- **LSP4J** (`0.10.0`) — Language Server Protocol support in the editor
- **ANTLR4 Runtime** (`4.8`) — Language parsing in `CodeEditorAntlr`
- **Markwon** (`4.6.2`) — Markdown rendering
- **MMKV** (`2.3.0`) — Fast key-value storage
- **Velocity Tools** (`3.1`) — File template generation
- **xCrash** (`3.1.0`) — Crash reporting
- **AgentWeb** (`4.1.3`) — In-app WebView
- **SoulPermission** (`1.2.0_x`) — Permission handling
- **XPopup** (`1.9.12`) — Popup/dialog UI
- **Material Dialogs** (`3.3.0`) — Dialog framework
- **Kotlin Reflect** (`2.3.0`) — Reflection support

## Important Code Locations

| Concern | Location |
|---------|----------|
| Main Activity / App startup | `app/src/main/java/taokdao/main/MainActivity.kt` |
| Presenters | `app/src/main/java/taokdao/main/business/*/` |
| Code Editor | `app/src/main/java/taokdao/codeeditor/` |
| Content / Guider | `app/src/main/java/taokdao/content/` |
| Plugin API consumption | `app/src/main/java/taokdao/plugin/` |
| Window / Explorer | `app/src/main/java/taokdao/window/` |
| API interfaces (external) | `../TaoKDao-API/` (composite build) |

## Lint & ProGuard

- ProGuard rules: `app/proguard-rules.pro`
- Consumer ProGuard rules exist in each library module
- `lintOptions` in `app/build.gradle` has `checkReleaseBuilds false` and `abortOnError false`
- Release builds have `minifyEnabled true` and `shrinkResources true`
