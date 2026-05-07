package tiiehenry.android.view.recyclerview.holder

import android.util.SparseArray
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

open class RecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(
    itemView
), IRecyclerViewHolder<RecyclerViewHolder> {
    private val mViews: SparseArray<View>?

    init {
        mViews = SparseArray()
    }

    override fun getItemView(): View {
        return itemView
    }

    override fun <T : View?> findView(id: Int): T {
        var view = mViews!![id]
        if (view == null) {
            view = itemView.findViewById(id)
            mViews.put(id, view)
        }
        return view as T
    }

    override fun clearViewCache() {
        mViews?.clear()
    }

    override fun getInstance(): RecyclerViewHolder {
        return this
    }

    inline fun <reified T : ViewBinding> bindingOf(): T {
        return T::class.java.getMethod("bind", View::class.java).invoke(null, itemView) as T
    }
}
