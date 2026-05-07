package taokdao.content.filetemplate

import taokdao.api.file.template.FileTemplate
import taokdao.api.main.action.MainAction
import taokdao.codeeditor.CodeIEditor
import taokdao.codeeditor.ieditor.CEIOController
import taokdao.main.IMainView
import taokdao.main.business.template.template_file.FileTemplateGeneratePresenter
import tiiehenry.ideditor.databinding.ContentsFileTemplateBinding
import java.io.File

class CTIOController(
    val main: IMainView,
    val layout: ContentsFileTemplateBinding,
    codeIEditor: CodeIEditor
) : CEIOController(codeIEditor) {
    private val fileTemplateGeneratePresenter: FileTemplateGeneratePresenter =
        main.main.fileTemplateGeneratePresenter

    override fun importData(data: String) {
        val codeTemplate = fileTemplateGeneratePresenter.importFromJson(data)
        layout.etName.setText(codeTemplate.name)
        layout.etExtension.setText(codeTemplate.extension)
        layout.etGroup.setText(codeTemplate.group)
        layout.etDescription.setText(codeTemplate.description)
        val s = StringBuilder()
        for (parameter in codeTemplate.parameters) {
            s.append(parameter)
            s.append(",")
        }
        layout.etParameters.setText(s.toString().trim(','))
        super.importData(codeTemplate.templateText)
    }

    override fun exportData(): String {
        val name = layout.etName.text.toString()
        val group = layout.etGroup.text.toString()
        val extension = layout.etExtension.text.toString()
        val description = layout.etDescription.text.toString()
        val parameterText = layout.etParameters.text.toString()
        val parameters = parameterText.split(",").filter { it.isNotBlank() && it != "," }
        val templateText = editor.string
        val codeTemplate =
            FileTemplate(name, group, description, extension, templateText, parameters)
        val newPath = fileTemplateGeneratePresenter.getConfigFilePath(codeTemplate)
        if (currentPath != newPath) {
            currentPath?.let {
                File(it).delete()
                MainAction.onFileDeleted.runObservers(main)
            }
            currentPath = newPath
        }
        return fileTemplateGeneratePresenter.exportToJson(codeTemplate)
    }
}