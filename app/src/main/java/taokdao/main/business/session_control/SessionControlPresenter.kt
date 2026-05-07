package taokdao.main.business.session_control

import java.io.File

class SessionControlPresenter(private val view: SessionControlContract.V) : SessionControlContract.P {
    private val model = SessionControlModel()

    override fun saveSession() {
        if (!SessionControlVariable.saveSession)
            return
//        model.clearAll(view.mmkvManager)
        model.preserveProject(view.mmkvManager, view.projectManager.project?.projectDir?.absolutePath
                ?: "")
        val map = linkedMapOf<String, String>()
        for (item in view.contentManager.list) {
            item.editor.ioController.currentPath?.let {
                map[it] = item.opener
            }
        }
        model.preserveFiles(view.mmkvManager, map)
    }

    override fun restoreSession() {
        if (!SessionControlVariable.restoreSession)
            return
        model.recoverProject(view.mmkvManager)?.let {
            view.projectManager.openProject(File(it))
        }
        for (item in model.recoverFiles(view.mmkvManager)) {
            val file = File(item.key)
            if (!file.isFile)
                continue
            view.tryOpenByOpener(file.absolutePath, item.value)
        }
    }

    fun observeOnPause() {
        if (SessionControlVariable.saveSession && SessionControlVariable.saveOpenedFiles) {
            view.contentManager.saveAllAsync()
        }
    }
}