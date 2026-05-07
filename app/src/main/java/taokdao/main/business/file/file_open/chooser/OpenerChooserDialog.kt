package taokdao.main.business.file.file_open.chooser

import taokdao.api.file.open.IFileOpener
import taokdao.api.main.IMainContext
import tiiehenry.android.ui.dialogs.api.IDialog
import tiiehenry.android.ui.dialogs.api.strategy.Dialogs
import tiiehenry.ideditor.R

object OpenerChooserDialog {
    fun show(main: IMainContext, path: String, openerList: List<IFileOpener>): IDialog {
        val adp = OpenerChooserAdapter(openerList)

        return Dialogs.global
                .asList()
                .typeCustom()
                .title(main.getString(R.string.business_file_open_choose_opener_unsupported))
                .adapter(adp)
                .negativeText()
                .show()
                .let {
                    adp.apply {
                        setOnItemClickListener { _, item, _ ->
                            item.click(main, main.contentManager, path)
                            it.dismiss()
                        }
                        setOnItemLongClickListener { _, item, _ ->
                            item.longClick(main, main.contentManager, path)
                        }
                    }
                    return@let it
                }
    }
}