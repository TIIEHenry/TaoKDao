package taokdao.main.business.mmkv_manage;


import androidx.annotation.NonNull;

import taokdao.api.data.mmkv.IMMKV;
import taokdao.api.data.mmkv.IMMKVManager;
import taokdao.api.plugin.bean.Plugin;
import taokdao.api.plugin.bean.PluginManifest;
import taokdao.api.plugin.engine.IPluginEngine;
import taokdao.api.project.plugin.IProjectPlugin;
import taokdao.api.ui.content.IContent;
import taokdao.api.ui.explorer.IExplorer;
import taokdao.api.ui.toolpage.IToolPage;

/**
 * 保存各种设置
 */
public interface IMMKVManagerWrapper extends IMMKVManager {

    @Override
    default IMMKV getProjectPluginMMKV(@NonNull String id) {
        return getMMKV("project_plugin_" + id);
    }

    @Override
    @NonNull
    default IMMKV getPluginMMKV(@NonNull String id) {
        return getMMKV("plugin_" + id);
    }

    @Override
    @NonNull
    default IMMKV getPluginEngineMMKV(@NonNull String id) {
        return getMMKV("plugin_engine_" + id);
    }

    @Override
    @NonNull
    default IMMKV getContentMMKV(@NonNull String id) {
        return getMMKV("content_" + id);
    }

    @Override
    @NonNull
    default IMMKV getTabToolMMKV(@NonNull String id) {
        return getMMKV("tabtool_" + id);
    }

    @Override
    @NonNull
    default IMMKV getExplorerMMKV(@NonNull String id) {
        return getMMKV("explorer_" + id);
    }


    @Override
    default IMMKV getProjectPluginMMKV(@NonNull IProjectPlugin projectPlugin) {
        return getProjectPluginMMKV(projectPlugin.id());
    }

    @Override
    @NonNull
    default IMMKV getPluginMMKV(@NonNull PluginManifest pluginManifest) {
        return getPluginMMKV(pluginManifest.id);
    }

    @Override
    @NonNull
    default IMMKV getPluginEngineMMKV(@NonNull IPluginEngine pluginEngine) {
        return getPluginEngineMMKV(pluginEngine.id());
    }

    @Override
    @NonNull
    default IMMKV getPluginMMKV(@NonNull Plugin plugin) {
        return getPluginMMKV(plugin.id);
    }

    @Override
    @NonNull
    default IMMKV getContentMMKV(@NonNull IContent tabContent) {
        return getContentMMKV(tabContent.id());
    }

    @Override
    @NonNull
    default IMMKV getTabToolMMKV(@NonNull IToolPage tabTool) {
        return getTabToolMMKV(tabTool.id());
    }

    @Override
    @NonNull
    default IMMKV getExplorerMMKV(@NonNull IExplorer explorer) {
        return getExplorerMMKV(explorer.id());
    }

}
