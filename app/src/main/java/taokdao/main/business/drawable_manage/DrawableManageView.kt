package taokdao.main.business.drawable_manage

import android.graphics.drawable.Drawable
import tiiehenry.ideditor.R


interface DrawableManageView : DrawableManageContract.V {
    override fun loadDefaultDrawableMapForDirName(): Map<String, Drawable?> {
        val map = mutableMapOf<String, Drawable?>()
        arrayListOf("src", "java", "lua").forEach {
            map[it] = getDrawable(R.drawable.ic_folder_sourcefolder)
        }
        arrayListOf("libs").forEach {
            map[it] = getDrawable(R.drawable.ic_folder_jardirectory)
        }
        arrayListOf("assets", "resources", "res").forEach {
            map[it] = getDrawable(R.drawable.ic_folder_resourcesroot)
        }
        return map
    }

    override fun loadDefaultDrawableMapForFileName(): Map<String, Drawable?> {
        val map = mutableMapOf<String, Drawable?>()
        arrayListOf("AndroidManifest.xml").forEach {
            map[it] = getDrawable(R.drawable.ic_file_manifest)
        }
        arrayListOf("Manifest.xml").forEach {
            map[it] = getDrawable(R.drawable.ic_file_xml_application)
        }
        return map
    }

    override fun loadDefaultDrawableMapForSuffix(): Map<String, Drawable?> {
        val map = mutableMapOf<String, Drawable?>()
        arrayListOf("zip", "rar", "7z").forEach {
            map[it] = getDrawable(R.drawable.ic_file_archive)
        }
        arrayListOf("ide", "config", "conf").forEach {
            map[it] = getDrawable(R.drawable.ic_file_config)
        }
        arrayListOf("css").forEach {
            map[it] = getDrawable(R.drawable.ic_file_css)
        }
        arrayListOf("class").forEach {
            map[it] = getDrawable(R.drawable.ic_java_class)
        }
        arrayListOf("gradle").forEach {
            map[it] = getDrawable(R.drawable.ic_file_gradle)
        }
        arrayListOf("html").forEach {
            map[it] = getDrawable(R.drawable.ic_file_html)
        }
        arrayListOf("java").forEach {
            map[it] = getDrawable(R.drawable.ic_file_java)
        }
        arrayListOf("ts").forEach {
            map[it] = getDrawable(R.drawable.ic_file_typescript)
        }
        arrayListOf("js", "jsx").forEach {
            map[it] = getDrawable(R.drawable.ic_file_javascript)
        }
        arrayListOf("jsp").forEach {
            map[it] = getDrawable(R.drawable.ic_file_jsp)
        }
        arrayListOf("jspx").forEach {
            map[it] = getDrawable(R.drawable.ic_file_jspx)
        }
        arrayListOf("json").forEach {
            map[it] = getDrawable(R.drawable.ic_file_json)
        }
        arrayListOf("aly", "lua").forEach {
            map[it] = getDrawable(R.drawable.ic_file_lua)
        }
        arrayListOf("properties").forEach {
            map[it] = getDrawable(R.drawable.ic_file_properties)
        }
        arrayListOf("text", "txt").forEach {
            map[it] = getDrawable(R.drawable.ic_file_text)
        }
        arrayListOf("md").forEach {
            map[it] = getDrawable(R.drawable.ic_file_text)
        }
        arrayListOf("py").forEach {
            map[it] = getDrawable(R.drawable.ic_file_text)
        }
        arrayListOf("xhtml").forEach {
            map[it] = getDrawable(R.drawable.ic_file_xhtml)
        }
        arrayListOf("xml").forEach {
            map[it] = getDrawable(R.drawable.ic_file_xml)
        }
        arrayListOf("svg").forEach {
            map[it] = getDrawable(R.drawable.ic_file_xml)
        }
        arrayListOf("xsd").forEach {
            map[it] = getDrawable(R.drawable.ic_file_xsdfile)
        }
        arrayListOf("wsdl").forEach {
            map[it] = getDrawable(R.drawable.ic_file_wsdlfile)
        }
        arrayListOf("jpg", "jpeg", "png", "bmp").forEach {
            map[it] = getDrawable(R.drawable.ic_file_uiform)
        }
        return map
    }

}