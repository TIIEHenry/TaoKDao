package taokdao.main.business.template.template_file

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import taokdao.api.file.template.FileTemplate
import taokdao.api.main.action.MainAction
import taokdao.main.business.file.file_open.opener.FileTemplateOpener
import tiiehenry.ideditor.R
import tiiehenry.taokdao.ui.dialog.ErrorDialog
import java.io.File
import java.io.IOException


class FileTemplateGeneratePresenter(internal val view: FileTemplateGenerateContract.V) : FileTemplateGenerateContract.P {
    private val model = FileTemplateGenerateModel()

    override fun init() {
        view.launchMain {
            try {
                withContext(Dispatchers.IO) {
                    model.initInternalFileTemplate(view.context.applicationInfo.publicSourceDir, view.fileSystem)
                }
            } catch (e: IOException) {
                e.printStackTrace()
                ErrorDialog(view.context, view.getString(R.string.error), e.message.toString()).show()
            }
        }

    }

    override fun importFromJson(json: String): FileTemplate {
        return model.importFromJson(json)
    }

    override fun exportToJson(fileTemplate: FileTemplate): String {
        return model.exportToJson(fileTemplate)
    }

    override fun showChooseDialog(dir: File) {
        view.launchMain {
            val templateList = withContext(Dispatchers.IO) { model.loadFileTemplateList(view.fileSystem) }
            val templateMap = model.classifyTemplateList(templateList)
            view.showFileTemplateChooseDialog(templateMap, dir)
        }
    }

    override fun generate(fileTemplate: FileTemplate, parameters: MutableMap<String, String>): String {
        val templateFile = File(view.fileSystem.cacheDir, fileTemplate.name + "." + fileTemplate.extension)
        if (!templateFile.exists()) {
            templateFile.createNewFile()
        }
        templateFile.writeText(fileTemplate.templateText)
        return model.generateFileTemplate(templateFile, parameters)
    }

    override fun showGenerateDialog(fileTemplate: FileTemplate, dir: File, fileName: String) {
        view.showFileTemplateGenerateDialog(fileTemplate, dir, fileName)
    }

    override fun create() {
        val fileTemplate = FileTemplate("template1", "", "", "txt", "this template use velocity", mutableListOf(""))
        val templatePath = File(view.fileSystem.cacheDir, fileTemplate.name + "_" + fileTemplate.extension + "." + FileTemplateGenerateModel.CONFIG_FILE_EXTENSION).absolutePath
        exportToJson(fileTemplate).let {
            File(templatePath).writeText(it)
        }
        view.main.fileOpenPresenter.requestOpenByOpenerId(templatePath, FileTemplateOpener.ID)
    }

    override fun edit(template: FileTemplate) {
        val templatePath = getConfigFilePath(template)
        view.main.fileOpenPresenter.requestOpenByOpenerId(templatePath, FileTemplateOpener.ID)
    }

    override fun getConfigDir(): File {
        return model.getConfigDir(view.fileSystem)
    }

    override fun getConfigFilePath(fileTemplate: FileTemplate): String {
        return File(configDir, fileTemplate.name + "_" + fileTemplate.extension + "." + FileTemplateGenerateModel.CONFIG_FILE_EXTENSION).absolutePath
    }

    override fun generateFile(fileTemplate: FileTemplate, file: File, parameterMap: MutableMap<String, String>) {
        model.initInternalVariable(parameterMap)
        if (!file.exists()) {
            file.parentFile?.mkdirs()
            file.createNewFile()
        }
        try {
            val txt = generate(fileTemplate, parameterMap)
            file.writeText(txt)
        } catch (e: Exception) {
            e.printStackTrace()
            ErrorDialog(view, e.message).show()
        }
        MainAction.onFileCreated.runObservers(view)
    }
}