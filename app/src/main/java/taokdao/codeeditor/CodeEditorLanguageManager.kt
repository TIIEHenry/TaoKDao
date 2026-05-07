package taokdao.codeeditor

import tiiehenry.code.language.Language
import tiiehenry.code.language.c.CLanguage
import tiiehenry.code.language.cpp.CppLanguage
import tiiehenry.code.language.css.CssLanguage
import tiiehenry.code.language.html.HtmlLanguage
import tiiehenry.code.language.java.JavaLanguage
import tiiehenry.code.language.javascript.IDELanguage
import tiiehenry.code.language.json.JsonLanguage
import tiiehenry.code.language.kotlin.KotlinLanguage
import tiiehenry.code.language.lua.LuaLanguage
import tiiehenry.code.language.markdown.MarkdownLanguage
import tiiehenry.code.language.objectivec.ObjectivecLanguage
import tiiehenry.code.language.python.PythonLanguage
import tiiehenry.code.language.python3.Python3Language
import tiiehenry.code.language.smali.SmaliLanguage
import tiiehenry.code.language.text.TextLanguage
import tiiehenry.code.language.xml.XmlLanguage

object CodeEditorLanguageManager {

    fun getLanguageForSuffix(suffix: String): Language? {
        return supportSuffix[suffix]
    }

    val supportSuffix = mapOf(
            "c" to CLanguage.getInstance(),
            "h" to CppLanguage.getInstance(),
            "cc" to CppLanguage.getInstance(),
            "cpp" to CppLanguage.getInstance(),
            "cxx" to CppLanguage.getInstance(),
            "m" to ObjectivecLanguage.getInstance(),
            "mm" to ObjectivecLanguage.getInstance(),
            "java" to JavaLanguage.getInstance(),
            "json" to JsonLanguage.getInstance(),
            "js" to IDELanguage.getInstance(),
            "ide" to IDELanguage.getInstance(),
            "plugin" to IDELanguage.getInstance(),
            "svg" to XmlLanguage.getInstance(),
            "xml" to XmlLanguage.getInstance(),
            "html" to HtmlLanguage.getInstance(),
            "htm" to HtmlLanguage.getInstance(),
            "xhtml" to HtmlLanguage.getInstance(),
            "mht" to HtmlLanguage.getInstance(),
            "css" to CssLanguage.getInstance(),
            "lua" to LuaLanguage.getInstance(),
            "aly" to LuaLanguage.getInstance(),
            "conf" to TextLanguage.getInstance(),
            "log" to TextLanguage.getInstance(),
            "txt" to TextLanguage.getInstance(),
            "md" to MarkdownLanguage.getInstance(),
            "kt" to KotlinLanguage.getInstance(),
            "kts" to KotlinLanguage.getInstance(),
            "py" to PythonLanguage.getInstance(),
            "py3" to Python3Language.getInstance(),
            "smali" to SmaliLanguage.getInstance()
    )


}