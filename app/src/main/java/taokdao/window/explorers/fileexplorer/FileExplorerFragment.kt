package taokdao.window.explorers.fileexplorer

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import taokdao.api.data.bean.Properties
import taokdao.api.internal.InnerIdentifier
import taokdao.api.main.action.MainAction
import taokdao.api.ui.explorer.callback.ExplorerStateObserver
import taokdao.api.ui.explorer.menu.ExplorerMenu
import taokdao.api.ui.explorer.wrapped.ExplorerFragment
import taokdao.main.IMainView
import taokdao.main.business.file.file_operate.operators.CreateFromTemplate
import taokdao.main.business.file.file_operate.operators.NewFile
import taokdao.main.business.file.file_operate.operators.NewFolder
import tiiehenry.android.text.SimpleTextWatcher
import tiiehenry.ktx.lang.ifTrue
import tiiehenry.ideditor.R
import tiiehenry.ideditor.databinding.ExplorersFileexplorerBinding
import java.io.File

class FileExplorerFragment(val main: IMainView) : ExplorerFragment(
        Properties(InnerIdentifier.Explorer.FILE_EXPLORER, main.context, R.string.drawerleft_spinnar_fileexplorer),
        main.getDrawable(R.drawable.explorers_fileexplorer_icon), R.layout.explorers_fileexplorer) {

    var currentPath: String = ""

    private val newFileOperator = NewFile(main)
    private val newFolderOperator = NewFolder(main)
    private val createFromTemplateOperator = CreateFromTemplate(main)


    init {

        MainAction.onFileCreated.addObserver {
            refresh()
        }
        MainAction.onFileRenamed.addObserver {
            refresh()
        }
        MainAction.onFileDeleted.addObserver {
            refresh()
        }
    }

    private val observer = object : ExplorerStateObserver {
        override fun onAdding() {
        }

        override fun onRemoved() {
        }

        override fun onShow() {
            checkCurrentExplorerPath()
        }

        override fun onHide() {
        }
    }

    override fun getStateObserver(): ExplorerStateObserver {
        return observer
    }

    override fun initView(view: View) {
        val binding=ExplorersFileexplorerBinding.bind(view)
        view.apply {
            binding.etFileFilter.addTextChangedListener(SimpleTextWatcher.newAfterWatcher {
                loadExplorerFiles(currentPath, it)
            })
            binding.rvContent.adapter = fileexploreradapter
            binding.rvContent.layoutManager = LinearLayoutManager(main.activity)
        }
        fileexploreradapter.apply {
            setOnItemClickListener { _, data, _ ->
                if (data.file.isDirectory) {
                    if (data.type == FileExplorerItem.Type.FILE)
                        loadExplorerFiles(data.file.absolutePath,
                            binding.etFileFilter.text.toString()
                        )
                    else
                        loadExplorerFiles(data.file.parentFile?.absolutePath,
                            binding.etFileFilter.text.toString()
                        )
                } else if (data.file.isFile) {
                    if (main.fileOpenManager.requestOpen(data.file.absolutePath)) {
                        main.explorerWindow.hideWindow()
                    }
                }
            }
            setOnItemLongClickListener { _, data, pos ->
                data.file.apply {
                    exists().ifTrue {
                        if (pos == 0)
                            showOperateDialogForUpper(this)
                        else
                            showOperateDialogForFile(this)
                    }
                }
            }
        }

        val locateInFileExplorer = ExplorerMenu(main.getDrawable(R.drawable.explorers_fileexplorer_menu_locate), "locate"
        ) {
            main.contentManager.current?.path?.let {
                if (it.isNotEmpty())
                    loadExplorerFiles(it, binding.etFileFilter.text.toString())
            }
        }
        val refreshMenu = ExplorerMenu(main.getDrawable(R.drawable.explorers_fileexplorer_menu_refresh), "refresh"
        ) { v ->
            v.clearAnimation()
            v.rotation = 0F
            v.animate().rotationBy(-3 * 360F).setDuration(900).start()
            refresh()
        }
        val newFile = ExplorerMenu(main.getDrawable(R.drawable.explorers_fileexplorer_menu_newfile), newFileOperator.label
        ) {
            newFileOperator.call(main, currentPath)
        }
        val newFolder = ExplorerMenu(main.getDrawable(R.drawable.explorers_fileexplorer_menu_newfolder), newFolderOperator.label
        ) {
            newFolderOperator.call(main, currentPath)
        }
        val newProjectTemplate = ExplorerMenu(main.getDrawable(R.drawable.explorer_menu_newtemplate), "newTemplate"
        ) {
            main.projectTemplateGenerator.showChooseDialog(File(currentPath))
        }
        menuList.addAll(mutableListOf(
                newProjectTemplate, locateInFileExplorer, refreshMenu, newFile, newFolder
        ))

    }

    private fun checkCurrentExplorerPath() {
        if (currentPath.isBlank()) {
            currentPath = main.projectManager.project?.projectDir?.absolutePath
                    ?: main.contentManager.current?.path
                            ?: main.fileSystem.projectDir.absolutePath
            refresh()
        }
    }


    private val fileexploreradapter: FileExplorerAdapter = FileExplorerAdapter()


    fun loadExplorerFiles(path: String?, filter: String = "") {
        path?.let {
            var dirFile = File(it)
            if (dirFile.isFile)
                dirFile = dirFile.parentFile ?: dirFile
            if (dirFile.isDirectory) {
                currentPath = dirFile.absolutePath
                fileexploreradapter.refresh(FileExplorerLoader.getExplorerItemList(main, dirFile, filter))
                (view?.findViewById<android.widget.EditText>(R.id.et_file_filter))?.hint = "Filter ${dirFile.name}"
            }
        }
    }


    private fun showOperateDialogForUpper(file: File) {
        main.fileOperateManager.showOperateDialog(file, listOf(newFileOperator, newFolderOperator, createFromTemplateOperator))
    }

    private fun showOperateDialogForFile(file: File) {
        main.fileOperateManager.showOperateDialog(file)
    }


    private fun refresh() {
        loadExplorerFiles(currentPath)
    }


}