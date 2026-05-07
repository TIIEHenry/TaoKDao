package taokdao.main.business.template.template_file

import taokdao.api.file.template.FileTemplate

data class FileTemplateItem(val name: String, val templateList: MutableList<FileTemplate>) {
    companion object {
        fun from(fileTemplate: List<FileTemplate>): FileTemplateItem {
            return FileTemplateItem(fileTemplate.first().group, fileTemplate.toMutableList())
        }

        fun from(fileTemplate: FileTemplate): FileTemplateItem {
            return FileTemplateItem(fileTemplate.name, mutableListOf(fileTemplate))
        }
    }
}