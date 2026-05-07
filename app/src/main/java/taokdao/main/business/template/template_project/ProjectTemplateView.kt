package taokdao.main.business.template.template_project

import taokdao.api.data.bean.Properties
import taokdao.api.project.template.IProjectTemplate
import taokdao.api.project.template.ProjectTemplatePool
import taokdao.api.ui.base.IPanelProp
import taokdao.api.ui.base.PanelProp
import tiiehenry.android.ui.dialogs.api.strategy.Dialogs
import tiiehenry.ideditor.R
import java.io.File
import java.util.*


interface ProjectTemplateView : ProjectTemplateContract.V {
    override fun addDefaultProjectTemplate() {
        val drawable = getDrawable(R.drawable.ic_file_any)
        val template = object : IProjectTemplate {
            override val prop: IPanelProp=PanelProp("fileTemplate", context, R.string.business_project_templates_filetemplate_label, R.string.business_project_templates_filetemplate_description)
            override fun generate(dir: File) {
                fileTemplateGenerator.showChooseDialog(dir)
            }

            override fun showInfo() {

            }
        }
        ProjectTemplatePool.getInstance().add(template)
    }

    override fun showProjectTemplateChooseDialog(templateList: MutableList<IProjectTemplate>, dir: File) {
        templateList.sortBy { it.prop.label.lowercase(Locale.getDefault()) }

        val chooseAdapter = ProjectTemplateChooserAdapter(templateList)

        Dialogs.global
                .asList()
                .typeCustom()
                .adapter(chooseAdapter)
                .title(R.string.business_project_template_choose_dialog_title)
                .negativeText()
                .show()
                .let {
                    chooseAdapter.apply {
                        setOnItemClickListener { _, item, _ ->
                            projectTemplateGenerator.generate(item, dir)
                            it.dismiss()
                        }
                        setOnItemLongClickListener { _, item, _ ->
                            projectTemplateGenerator.showInfo(item)
                        }
                    }
                }
    }
}