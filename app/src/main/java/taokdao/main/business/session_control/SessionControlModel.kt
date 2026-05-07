package taokdao.main.business.session_control

import taokdao.api.data.mmkv.IMMKV
import taokdao.api.data.mmkv.IMMKVManager

class SessionControlModel : SessionControlContract.M {
    override fun preserveProject(mmkvManager: IMMKVManager, projectDir: String) {
        if (!SessionControlVariable.recordOpenedProject)
            return
        val mmkv = getMMKV(mmkvManager)
        mmkv.encode(KEY.PROJECT, projectDir)
    }

    override fun preserveFiles(mmkvManager: IMMKVManager, map: Map<String, String>) {
        if (!SessionControlVariable.recordOpenedFiles)
            return
        val mmkv = getMMKV(mmkvManager)
        for (entry in map) {
            mmkv.encode(entry.key, entry.value)
        }
        mmkv.encode(KEY.FILES, map.keys)
    }

    override fun recoverProject(mmkvManager: IMMKVManager): String? {
        if (!SessionControlVariable.openRecordedProject)
            return null
        val mmkv = getMMKV(mmkvManager)
        return mmkv.decodeString(KEY.PROJECT, null)
    }

    override fun recoverFiles(mmkvManager: IMMKVManager): MutableMap<String, String> {
        val map = mutableMapOf<String, String>()
        if (!SessionControlVariable.openRecordedFiles)
            return map
        val mmkv = getMMKV(mmkvManager)
        mmkv.decodeStringSet(KEY.FILES, HashSet())?.let {
            for (path in it) {
                map[path] = mmkv.decodeString(path, "default") ?: "default"
            }
        }
        return map
    }

    private fun getMMKV(mmkvManager: IMMKVManager): IMMKV {
        return mmkvManager.getMMKV(KEY.MMKV)
    }
//
//    fun clearAll(mmkvManager: IMMKVManager) {
//        getMMKV(mmkvManager).clearAll()
//    }

    private object KEY {
        const val MMKV = "session"
        const val FILES = "recordedFiles"
        const val PROJECT = "recordedProject"
    }
}
