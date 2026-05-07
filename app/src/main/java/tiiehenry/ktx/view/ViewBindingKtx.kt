package tiiehenry.ktx.view

import android.view.View
import androidx.viewbinding.ViewBinding

inline fun <reified T : ViewBinding> View.viewBindingOf(): T {
    return T::class.java.getMethod("bind", View::class.java).invoke(null, this) as T
}

