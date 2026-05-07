package taokdao.main.business.file.file_open.opener

import taokdao.api.data.bean.Properties
import taokdao.api.file.open.FileOpenerPool
import taokdao.api.file.open.wrapped.FileOpener
import taokdao.api.file.open.wrapped.SuffixFileOpener
import taokdao.api.file.util.FileUtils
import taokdao.api.main.IMainContext
import taokdao.api.ui.content.manage.IContentManager
import taokdao.main.business.file.file_open.chooser.OpenerChooserDialog
import tiiehenry.ideditor.R


class SuffixOpenerChooserOpener(val main: IMainContext, val path: String, var suffix: String = FileUtils.getSuffix(path))
    : FileOpener(Properties("suffixOpenerChooser", main.context,
        R.string.business_file_open_opener_suffixopener_label, R.string.business_file_open_opener_suffixopener_description), null) {


    private fun getSupportedSuffixOpeners(): HashSet<SuffixFileOpener> {
        val set: HashSet<SuffixFileOpener> = hashSetOf()

        for (fileOpener in FileOpenerPool.getInstance().all) {
            if (fileOpener is SuffixFileOpener) {
                if (fileOpener.supportSuffix.contains(suffix)) {
                    set.add(fileOpener)
                }
            }
        }
        return set
    }

    override fun click(main: IMainContext, manager: IContentManager, path: String) {
        OpenerChooserDialog.show(main, path, getSupportedSuffixOpeners().toList())
    }


    fun click() {
        click(main, main.contentManager, path)
    }

    fun longClick() {
        longClick(main, main.contentManager, path)
    }
}