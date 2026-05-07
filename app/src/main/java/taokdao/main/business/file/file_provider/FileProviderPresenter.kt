package taokdao.main.business.file.file_provider

import android.content.ContentResolver
import android.content.ContentUris
import android.content.ContentValues
import android.net.Uri
import android.os.Build
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.text.TextUtils
import androidx.core.content.FileProvider
import androidx.documentfile.provider.DocumentFile
import taokdao.api.file.provider.IFileProvider
import taokdao.api.file.util.FileProviderUtils
import java.io.File
import java.io.FileInputStream


class FileProviderPresenter(internal val view: FileProviderContract.V) : FileProviderContract.P {
    private val model = FileProviderModel()


    override fun getAuthority(): String {
        return view.applicationContext.packageName + ".fileprovider"
    }

    override fun getFileUri(file: File): Uri {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            FileProvider.getUriForFile(view.context, authority, file)
        } else {
            Uri.fromFile(file)
        }
    }

    override fun getContentUri(file: File): Uri? {
        if (!file.exists()) {
            return null
        }

        var uri: Uri? = null
        val path = file.absolutePath
        val mimeType = IFileProvider.getMimeType(file.name)

        val contentResolver: ContentResolver = view.context.contentResolver
        val contentUri = FileProviderUtils.getContentUri(mimeType)
        val projection = arrayOf(MediaStore.MediaColumns._ID)
        val selection = MediaStore.MediaColumns.DATA + "=?"
        val selectionArgs = arrayOf(path)
        val cursor = contentResolver.query(contentUri, projection, selection, selectionArgs, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                val idCol = cursor.getColumnIndex(MediaStore.MediaColumns._ID)
                if (idCol >= 0) {
                    uri = ContentUris.withAppendedId(contentUri, cursor.getLong(idCol))
                }
            }
            cursor.close()
        }
        return uri
    }


    override fun getDuplicateFileUri(file: File): Uri? {
        if (!file.exists()) {
            return null
        }

        var uri: Uri? = null
        val name = file.name
        val size = file.length()
        val mimeType = FileProviderUtils.getMimeType(name)

        val contentResolver: ContentResolver = view.context.contentResolver
        val contentUri = FileProviderUtils.getContentUri(mimeType)
        val projection = arrayOf(MediaStore.MediaColumns._ID)
        val selection = MediaStore.MediaColumns.DISPLAY_NAME + "=?" + " AND " + MediaStore.MediaColumns.SIZE + "=?"
        val selectionArgs = arrayOf(name, size.toString())
        val cursor = contentResolver.query(contentUri, projection, selection, selectionArgs, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                val idCol = cursor.getColumnIndex(MediaStore.MediaColumns._ID)
                if (idCol >= 0) {
                    uri = ContentUris.withAppendedId(contentUri, cursor.getLong(idCol))
                }
            }
            cursor.close()
        }
        return uri
    }

    override fun getName(uri: Uri): String? {
        return DocumentFile.fromSingleUri(view.context, uri)?.name
    }

    override fun getMimeType(uri: Uri): String? {
        return view.context.contentResolver.getType(uri)
    }

    override fun copyFileToExternal(file: File, dirName: String?): Uri? {
        if (!file.exists()) {
            return null
        }

        // 获取是否有重复的文件，避免重复复制
        var uri = FileProviderUtils.getDuplicateFileUri(view.context, file)
        if (uri != null) {
            return uri
        }

        val name = file.name
        val mimeType = FileProviderUtils.getMimeType(name)

        val contentResolver: ContentResolver = view.contentResolver
        val contentUri = FileProviderUtils.getContentUri(mimeType)

        // 插入参数
        val values = ContentValues()
        values.put(MediaStore.MediaColumns.DISPLAY_NAME, name) // 文件名

        values.put(MediaStore.MediaColumns.MIME_TYPE, mimeType) // mimeType

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            var dirPath = FileProviderUtils.getDirName(mimeType)
            if (!TextUtils.isEmpty(dirName)) {
                dirPath += File.separatorChar.toString() + dirName
            }
            values.put(MediaStore.MediaColumns.RELATIVE_PATH, dirPath) // 相对路径
            values.put(MediaStore.MediaColumns.IS_PENDING, 1) // 文件的处理状态(防止写入过程中被其他App查询到，写入完成后记得修改回来)
        }
        // 获取插入的Uri
        uri = contentResolver.insert(contentUri, values)
        if (uri == null) {
            return null
        }

        // 复制文件
        var copySuccess = false
        try {
            contentResolver.openOutputStream(uri).use { outputStream -> FileInputStream(file).use { inputStream -> copySuccess = FileProviderUtils.copy(inputStream, outputStream) } }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        if (!copySuccess) {
            FileProviderUtils.delete(view.context, uri)
            return null
        }

        // 更新文件的处理状态
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            values.clear()
            values.put(MediaStore.MediaColumns.IS_PENDING, 0) // 文件的处理状态(防止写入过程中被其他App查询到，写入完成后记得修改回来)
            contentResolver.update(uri, values, null, null)
        }

        return uri
    }

    override fun deleteSystem(uri: Uri): Boolean {
        var delete = false

        val contentResolver: ContentResolver = view.contentResolver

        try {
            delete = DocumentsContract.deleteDocument(contentResolver, uri)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return delete
    }

    override fun delete(uri: Uri): Boolean {
        var delete = false

        val contentResolver: ContentResolver = view.contentResolver

        try {
            delete = contentResolver.delete(uri, null, null) > 0
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return delete
    }

}