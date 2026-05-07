package taokdao.main.business.template.template_file

import taokdao.api.file.system.IFileSystem
import taokdao.api.file.template.FileTemplate
import taokdao.api.file.template.IFileTemplateGenerator
import taokdao.main.IMainView
import tiiehenry.android.ui.dialogs.api.IDialog
import java.io.File


interface FileTemplateGenerateContract {
    interface V : IMainView {
        val fileTemplateGeneratePresenter: FileTemplateGeneratePresenter

        fun showFileTemplateChooseDialog(fileTemplateMap: Map<String, List<FileTemplate>>, dir: File): IDialog

        fun showFileTemplateGenerateDialog(fileTemplate: FileTemplate, dir: File, fileName: String)

    }

    interface P : IFileTemplateGenerator

    interface M {
        fun getConfigDir(fileSystem: IFileSystem): File

        fun importFromJson(json: String): FileTemplate
        fun exportToJson(fileTemplate: FileTemplate): String

        fun generateFileTemplate(templateFile: File, parameters: MutableMap<String, String>): String
        fun loadFileTemplateList(fileSystem: IFileSystem): MutableList<FileTemplate>
        fun classifyTemplateList(templateList: MutableList<FileTemplate>): Map<String, List<FileTemplate>>
        fun initInternalVariable(parameterMap: MutableMap<String, String>)
        fun initInternalFileTemplate(apkPath: String, fileSystem: IFileSystem)
    }
}