package taokdao.main.business.file.file_open.opener

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.interfaces.XPopupImageLoader
import taokdao.api.data.bean.Properties
import taokdao.api.file.open.wrapped.SuffixFileOpener
import taokdao.api.file.open.wrapped.SuffixFileOpener.Callback
import taokdao.main.IMainView
import tiiehenry.ideditor.R
import java.io.File

class ImageViewerOpener(main: IMainView)
    : SuffixFileOpener(
        arrayOf("jpg", "jpeg", "png", "bmp"),
        Properties("ImageViewer", main.context, R.string.business_file_open_opener_imagevieweropener_label, R.string.business_file_open_opener_imagevieweropener_description)) {
    init {
        click = Callback { main, _, path ->
            XPopup.Builder(main.activity)
                    .asImageViewer(null, path, true, -1, -1, 50, false, object : XPopupImageLoader {
                        override fun getImageFile(context: Context, uri: Any): File {
                            return File(uri as String)
                        }

                        override fun loadImage(position: Int, uri: Any, imageView: ImageView) {
                            imageView.setImageDrawable(Drawable.createFromPath(uri.toString()))
                        }
                    })
                    .show()
        }
    }

}