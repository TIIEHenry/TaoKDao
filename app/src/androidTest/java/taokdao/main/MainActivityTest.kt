package taokdao.main

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lxj.androidktx.core.loge
import org.apache.velocity.VelocityContext
import org.apache.velocity.app.VelocityEngine
import org.junit.Test
import org.junit.runner.RunWith
import tiiehenry.ktx.lang.fileExtension
import java.io.File
import java.io.StringWriter
import java.util.*


@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Test
    fun onCreate() {
        // 初始化（1）
        val templateDir = "/storage/emulated/0/TaoKDao/Project"
        val templateFileName = "Presenter.kt"
        val templateFile = File(templateDir, templateFileName)
        loge(templateFile.toString())

        val properties = Properties()
        properties.setProperty("file.resource.loader.path", templateDir)

        // 创建context，存放变量（2）
        val context = VelocityContext()

        // 加载模板文件到内存（3）
//        val template = Velocity.getTemplate(templateFile)
        val velocityEngine = VelocityEngine(properties)

        val template = velocityEngine.getTemplate(templateFileName)

        val VAR_PACKAGE_NAME = "com.tiiehenry.test"
        context.put("PACKAGE_NAME", VAR_PACKAGE_NAME)
        val VAR_NAME = "PluginPresenter"
        context.put("NAME", VAR_NAME)
        val stringWriter = StringWriter()
        template.merge(context, stringWriter)
        val generatedTemplateText = stringWriter.toString()
        File(templateDir, VAR_NAME + "." + templateFileName.fileExtension()).writeText(generatedTemplateText)
        loge(generatedTemplateText)
    }
}