package taokdao.main.business.template.template_file

import com.alibaba.fastjson.JSON
import org.apache.velocity.VelocityContext
import org.apache.velocity.app.VelocityEngine
import taokdao.api.file.system.IFileSystem
import taokdao.api.file.template.FileTemplate
import tiiehenry.io.Filej
import tiiehenry.io.Zipl
import java.io.File
import java.io.StringWriter
import java.text.SimpleDateFormat
import java.util.*

class FileTemplateGenerateModel : FileTemplateGenerateContract.M {
    override fun initInternalFileTemplate(apkPath: String, fileSystem: IFileSystem) {
        val templateDir = Filej(getConfigDir(fileSystem))
        if (templateDir.list()?.isEmpty() != false) {
            Zipl(File(apkPath)).unZipDir("assets/template/file", templateDir)
        }
    }

    override fun getConfigDir(fileSystem: IFileSystem): File {
        return File(fileSystem.configDir, CONFIG_DIR_NAME).apply {
            mkdirs()
        }
    }

    override fun loadFileTemplateList(fileSystem: IFileSystem): MutableList<FileTemplate> {
        val list = mutableListOf<FileTemplate>()
        val files = getConfigDir(fileSystem).listFiles { _, name -> name.endsWith(".$CONFIG_FILE_EXTENSION") }
                ?: return list
        for (file in files) {
            try {
                val template = importFromJson(file.readText())
                list.add(template)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return list
    }

    override fun importFromJson(json: String): FileTemplate {
        return JSON.parseObject(json, FileTemplate::class.java)
    }

    override fun exportToJson(fileTemplate: FileTemplate): String {
        return JSON.toJSONString(fileTemplate)
    }

    override fun generateFileTemplate(templateFile: File, parameters: MutableMap<String, String>): String {
        val properties = Properties()
        properties.setProperty("file.resource.loader.path", templateFile.parent)

        // 创建context，存放变量（2）
        val context = VelocityContext()

        val velocityEngine = VelocityEngine(properties)

        // 加载模板文件到内存（3）
        val template = velocityEngine.getTemplate(templateFile.name)
        for (parameter in parameters) {
            context.put(parameter.key, parameter.value)
        }
        val stringWriter = StringWriter()
        template.merge(context, stringWriter)
        return stringWriter.toString()
    }

    override fun classifyTemplateList(templateList: MutableList<FileTemplate>): Map<String, List<FileTemplate>> {
        return templateList.groupBy { it.group }
    }

    override fun initInternalVariable(parameterMap: MutableMap<String, String>) {
        val locale = Locale.getDefault()
        val calendar: Calendar = Calendar.getInstance()
        val date = SimpleDateFormat("YYYY/MM/dd", locale).format(calendar.time)
        val time = SimpleDateFormat("HH:mm", locale).format(calendar.time)
        val year = SimpleDateFormat("YYYY", locale).format(calendar.time)
        val month = SimpleDateFormat("MM", locale).format(calendar.time)
        val monthNameShort = SimpleDateFormat("MMMM", locale).format(calendar.time)
        val monthNameFull = SimpleDateFormat("MMMMMM", locale).format(calendar.time)
//    val day: Int = calendar.get(Calendar.DAY_OF_MONTH)
        val day = SimpleDateFormat("dd", locale).format(calendar.time)
        val dayNameShort = SimpleDateFormat("E", locale).format(calendar.time)
        val dayNameFull = SimpleDateFormat("EE", locale).format(calendar.time)
//    SimpleDateFormat("DD").parse(dayNameShort.toString())
//    val hour: Int = calendar.get(Calendar.HOUR_OF_DAY)
        val hour = SimpleDateFormat("HH", locale).format(calendar.time)
//    val minute: Int = calendar.get(Calendar.MINUTE)
        val minute = SimpleDateFormat("mm", locale).format(calendar.time)
        parameterMap["DATE"] = date
        parameterMap["TIME"] = time
        parameterMap["YEAR"] = year
        parameterMap["MONTH"] = month
        parameterMap["MONTH_NAME_SHORT"] = monthNameShort
        parameterMap["MONTH_NAME_FULL"] = monthNameFull
        parameterMap["DAY"] = day
        parameterMap["DAY_NAME_SHORT"] = dayNameShort
        parameterMap["DAY_NAME_FULL"] = dayNameFull
        parameterMap["HOUR"] = hour
        parameterMap["MINUTE"] = minute
//        parameterMap["PROJECT_NAME"]=minute
    }

    companion object {
        const val CONFIG_DIR_NAME = "FileTemplate"
        const val CONFIG_FILE_EXTENSION = "filetemplate"
    }
}
