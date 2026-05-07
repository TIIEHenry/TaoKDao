package taokdao.codeeditor.ieditor

import android.util.Log
import taokdao.api.event.senders.ContentSender
import taokdao.api.main.IMainContext
import taokdao.api.ui.content.editor.base.io.ITextIOController
import taokdao.codeeditor.CodeIEditor
import taokdao.codeeditor.detect.BytesEncodingDetect
import java.io.File
import java.io.IOException
import java.nio.charset.Charset

open class CEIOController(val editor: CodeIEditor) : ITextIOController<String> {
    private var canRead = false
    private var canWrite = false
    private var readCharset: Charset? = Charsets.UTF_8
    private var currCharset: Charset? = Charsets.UTF_8

    override fun getReadCharset(): Charset? {
        return readCharset
    }

    override fun setReadCharset(charset: Charset?) {
        readCharset = charset
    }

    private var writeCharset: Charset? = Charsets.UTF_8

    override fun getWriteCharset(): Charset? {
        return writeCharset
    }

    override fun setWriteCharset(charset: Charset?) {
//        loge("setWriteCharset"+charset.toString())
        writeCharset = charset
    }

    override fun getDefaultCharset(): Charset = Charsets.UTF_8
    override fun getCurrentCharset(): Charset? {
        return currCharset
    }

    override fun open(path: String): Boolean {
        ContentSender.open(path).log(editor.context as IMainContext)
        val file = File(path)
        if (file.exists() && file.canRead()) {
            currentPath = path
            setReadable(true)
            return true
        }
        setReadable(false)
        return false
    }

    private fun detectCharset(bytes: ByteArray): Charset? {
        try {
            val index = BytesEncodingDetect().detectEncoding(bytes)
            val encoding = BytesEncodingDetect.javaname[index]
            return Charset.forName(encoding)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    override fun read(): String {
       return readWithCharset(readCharset)
    }

    private fun readWithCharset(charset: Charset?): String {
        if (!canRead())
            throw IOException("can not read")
//            return ""
        val path = currentPath ?: throw IOException("read path null")

        setReadable(false)
        val file = File(path)
        val bytes = file.readBytes()


        val chars = charset ?: (detectCharset(bytes) ?: defaultCharset)
        currCharset = chars

        setReadable(true)
        setWritable(true)
        ContentSender.read(path, chars).log(editor.context as IMainContext)
        return String(bytes, chars)
    }

    override fun reloadWith(charset: Charset) {
        val oldCharset = currCharset
        try {
            val data = readWithCharset(charset)
            importData(data)
        } catch (e: Exception) {
            e.printStackTrace()
            currCharset = oldCharset
        }
    }

    override fun convertTo(charset: Charset) {
        currCharset = charset
        editor.dataController.isChanged = true
    }
//
//    override fun read(): String {
//        if (!canRead())
//            return ""
//        currentPath?.let { path ->
//            setReadable(false)
//            val file = File(path)
//            val bytes = file.readBytes()
//            val detector = CharsetDetector().setText(bytes)
//            val detectedAll=detector.detectAll()
//            val match = detectedAll.maxBy { it.confidence }
//
//            val encodings = EncodingDetector.detectEncoding(bytes)
//            val index= BytesEncodingDetect().detectEncoding(bytes)
//
//            Log.e("CEIOController", "read a: " + BytesEncodingDetect.javaname[index])
//            Log.e("CEIOController", "read w: " + encodings.intersect(detectedAll.map { it.name }))
//            Log.e("CEIOController", "read: " + encodings)
//            Log.e("CEIOController", "read: " + detector.detectAll().map { it.name })
//            setReadable(true)
//            setWritable(true)
//            if (match != null) {
//                charset = Charset.forName(match.name) ?: charset
//                return match.string
//            }
//            return String(bytes, charset)
//        }
//        return ""
//    }

    override fun importData(data: String) {
        editor.setText(data)
    }

    override fun exportData(): String {
        return editor.string
    }

    override fun write(data: String) {
        if (!canWrite())
            return
        if (editor.dataController.isChanged) {
            currentPath?.let {
                writeTo(data, it)
            }
        }
    }

    override fun writeTo(data: String, path: String) {
        if (!canWrite())
            return
        setReadable(false)
        setWritable(false)
        val file = File(path)

        val charsets =  writeCharset ?:currCharset ?: defaultCharset
        file.apply {
            if (!exists()) {
                parentFile?.mkdirs()
                createNewFile()
            }
            ContentSender.write(path, charsets).log(editor.context as IMainContext)
            writeText(data, charsets)
        }
        currCharset = charsets
        editor.dataController.isChanged = false
        currentPath = path
        setWritable(true)
    }

    override fun close(): Boolean {
        setReadable(true)
        setWritable(false)
        editor.dataController.isChanged = false
        return currentPath?.let {
            ContentSender.close(it).log(editor.context as IMainContext)
            File(it).exists()
        } ?: false
    }


    override fun canRead(): Boolean {
        return canRead
    }

    override fun setReadable(readable: Boolean) {
        canRead = readable
    }

    override fun canWrite(): Boolean {
        return canWrite
    }

    override fun setWritable(writable: Boolean) {
        canWrite = writable
    }

    private var _currentPath: String? = null

    override fun setCurrentPath(path: String?) {
        _currentPath = path
    }

    override fun getCurrentPath(): String? {
        return _currentPath
    }
}