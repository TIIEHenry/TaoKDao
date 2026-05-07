package taokdao.main.business.template.template_file

import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import taokdao.api.file.template.FileTemplate
import tiiehenry.android.ui.dialogs.api.IDialog
import tiiehenry.android.ui.dialogs.api.strategy.Dialogs
import tiiehenry.ideditor.R
import tiiehenry.ideditor.databinding.FileTemplateChooseInGroupDialogBinding
import tiiehenry.ideditor.databinding.FileTemplateGenerateDialogBinding
import tiiehenry.ideditor.databinding.FileTemplateGenerateParameterItemBinding
import java.io.File
import java.util.Locale


interface FileTemplateGenerateView : FileTemplateGenerateContract.V {

    private fun sortFileTemplateList(list: MutableList<FileTemplate>) {
        list.sortBy { it.name.toLowerCase(Locale.getDefault()) }
        list.sortBy { it.extension.toLowerCase(Locale.getDefault()) }
    }

    override fun showFileTemplateChooseDialog(fileTemplateMap: Map<String, List<FileTemplate>>, dir: File): IDialog {
        val itemList = mutableListOf<FileTemplateItem>()
        val noGroupList = fileTemplateMap[""]?.toMutableList() ?: mutableListOf()

        for (item in fileTemplateMap) {
            if (item.key == "")
                continue
            val list = item.value.toMutableList()
            if (list.isEmpty()) {
                continue
            }
            itemList.add(FileTemplateItem.from(list))
        }
        sortFileTemplateList(noGroupList)
        for (fileTemplate in noGroupList) {
            itemList.add(FileTemplateItem.from(fileTemplate))
        }

        val chooseAdapter = FileTemplateChooseAdapter(drawableManager, itemList)
        return Dialogs.global
                .asList()
                .typeCustom()
                .adapter(chooseAdapter, LinearLayoutManager(context, RecyclerView.VERTICAL, false))
                .title(R.string.business_file_template_choose_dialog_title)
                .negativeText()
                .neutralText(R.string.business_file_template_choose_dialog_new)
                .onNeutral {
                    fileTemplateGeneratePresenter.create()
                }
                .show()
                .let {
                    chooseAdapter.apply {
                        setOnItemClickListener { _, item, _ ->
                            showFileTemplateChooseInGroupDialog(item, dir)
                            it.dismiss()
                        }
                    }
                    it
                }
    }

    private fun showFileTemplateChooseInGroupDialog(item: FileTemplateItem, dir: File) {
        val binding=FileTemplateChooseInGroupDialogBinding.inflate(layoutInflater)
        val fileTemplateList = item.templateList

        sortFileTemplateList(fileTemplateList)

        val chooseAdapter = FileTemplateChooseInGroupAdapter(drawableManager, fileTemplateList)
        binding.fileTemplateChooseInGroupRv.apply {
            adapter = chooseAdapter
            layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        }
        Dialogs.global
                .asCustom()
                .title(item.name)
                .customView(binding.root, true)
                .negativeText()
                .neutralText(R.string.business_file_template_dialog_back)
                .onNeutral { d ->
                    d.dismiss()
                    fileTemplateGeneratePresenter.showChooseDialog(dir)
                }
                .cancelOnTouchOutside(false)
                .show()
                .let {
                    chooseAdapter.apply {
                        setOnItemClickListener { _, item, _ ->
                            val fileName = binding.etFileName.text.toString()
                            if (fileName.isEmpty()) {
                                binding.etFileName.error = context.getString(R.string.business_file_template_dialog_filenameempty)
                                return@setOnItemClickListener
                            }
                            val file = File(dir, fileName + "." + item.extension)
                            if (file.exists()) {
                                Dialogs.global
                                        .asConfirm()
                                        .title(R.string.business_file_template_fileexists_dialog_title)
                                        .content(file.absolutePath)
                                        .positiveText()
                                        .show()
                                return@setOnItemClickListener
                            }
                            showFileTemplateGenerateDialog(item, dir, fileName)
                            it.dismiss()
                        }
                        setOnItemLongClickListener { _, item, _ ->
                            showFileTemplateManageDialog(item, it, this, fileTemplateList)
                        }
                    }
                }
    }

    private fun showFileTemplateManageDialog(fileTemplate: FileTemplate, chooseDialog: IDialog, fileTemplateChooseAdapter: FileTemplateChooseInGroupAdapter, fileTemplateList: MutableList<FileTemplate>): IDialog? {
        return Dialogs.global
                .asConfirm()
                .title(fileTemplate.name)
                .content(fileTemplate.description)
                .negativeText()
                .positiveText(R.string.business_file_template_dialog_edit)
                .onPositive { _ ->
                    fileTemplateGeneratePresenter.edit(fileTemplate)
                    chooseDialog.dismiss()
                }
                .neutralText(R.string.business_file_template_dialog_delete)
                .onNeutral { dialog ->
                    File(fileTemplateGeneratePresenter.getConfigFilePath(fileTemplate)).delete()
                    dialog.dismiss()
                    fileTemplateList.remove(fileTemplate)
                    fileTemplateChooseAdapter.refresh(fileTemplateList)
                }
                .cancelOnTouchOutside(false)
                .show()
    }

    override fun showFileTemplateGenerateDialog(fileTemplate: FileTemplate, dir: File, fileName: String) {
        val binding=FileTemplateGenerateDialogBinding.inflate(layoutInflater)
        val param2View = mutableMapOf<String, EditText>()
        fun generate() {
            val file = File(dir, fileName + "." + fileTemplate.extension)
            val parameterMap = mutableMapOf("NAME" to fileName)
            for (item in param2View) {
                parameterMap[item.key] = item.value.text.toString()
            }
            fileTemplateGeneratePresenter.generateFile(fileTemplate, file, parameterMap)
            fileOpenManager.requestOpen(file.absolutePath)
        }
        if (fileTemplate.parameters.isEmpty()) {
            generate()
            return
        }
        for (parameter in fileTemplate.parameters) {
            val itemBinding = FileTemplateGenerateParameterItemBinding.inflate(layoutInflater)
            itemBinding.editInput.let {
                it.hint = "Parameter:$parameter"
                param2View[parameter] = it
            }
            binding.llContainer.addView(itemBinding.root)
        }

        Dialogs.global
                .asCustom()
//                .limitIconToDefaultSize()
//                .title("新建模板")
                .title(fileTemplate.name)
                .customView(binding.root, true)
                .cancelOnTouchOutside(false)
                .negativeText()
                .positiveText()
                .onPositive { dialog ->
                    generate()
                    dialog.dismiss()
                }
                .show()
    }
}