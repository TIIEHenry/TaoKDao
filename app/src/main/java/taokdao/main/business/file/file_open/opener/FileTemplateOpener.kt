package taokdao.main.business.file.file_open.opener

import taokdao.api.data.bean.Properties
import taokdao.api.file.open.wrapped.SuffixFileOpener
import taokdao.api.file.open.wrapped.SuffixFileOpener.Callback
import taokdao.content.filetemplate.CTFragment
import taokdao.main.IMainView
import taokdao.main.business.template.template_file.FileTemplateGenerateModel
import tiiehenry.ideditor.R

class FileTemplateOpener(val main: IMainView) : SuffixFileOpener(arrayOf(FileTemplateGenerateModel.CONFIG_FILE_EXTENSION),
        Properties(ID, main.context, R.string.business_file_open_opener_filetemplateopener_label, R.string.business_file_open_opener_filetemplateopener_description)) {

    init {
        click = Callback { _, manager, file ->
            val fragment = CTFragment(main, file)
            fragment.opener = id
            manager.add(fragment)
        }
    }

    companion object {
        const val ID = "FileTemplateOpener"
    }
}