package tiiehenry.taokdao.ui.view

import android.content.Context
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import tiiehenry.android.view.recyclerview.adapter.InflateRecyclerAdapter
import tiiehenry.ktx.res.dp2px

open class UIRecyclerPopup<T>
/**
 * Constructor.
 *
 * @param context   Context
 * @param direction
 */
(context: Context, val adapter: InflateRecyclerAdapter<T>, direction: Int = DIRECTION_TOP, val anim: Int = -1) :
        UIPopup(context, direction) {

    private val recyclerView: RecyclerView = RecyclerView(context)


    /**
     * 创建弹窗
     *
     * @param width     弹窗的宽度
     * @param maxHeight 弹窗最大的高度
     * @return
     */
    fun create(width: Int, maxHeight: Int): UIRecyclerPopup<T> {
        val margin = context.dp2px(5f)
        //        int margin =0;
        val height = if (maxHeight != 0) maxHeight else FrameLayout.LayoutParams.WRAP_CONTENT
        val lp = FrameLayout.LayoutParams(width, height)
        lp.setMargins(margin, margin, margin, margin)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
//            setPadding(0, 0, 0, 0)
            isVerticalScrollBarEnabled = false
            overScrollMode = View.OVER_SCROLL_NEVER
        }
        recyclerView.adapter = adapter
        setContentView(recyclerView)
        updateLayoutParams(lp)
//        Log.e("UIRecyclerPopup", "create: " + adapter.dataList)
        recyclerView.background = ColorDrawable(0x00000000)
        return this
    }

    fun updateLayoutParams(layoutParams: ViewGroup.LayoutParams) {
        recyclerView.layoutParams = layoutParams
    }

    fun updateDataList(list: MutableList<T>) {
        adapter.refresh(list)
    }

    /**
     * 创建弹窗
     *
     * @param width 弹窗的宽度
     * @return
     */
    protected fun create(width: Int): UIRecyclerPopup<T> {
        return create(width, 0)
    }

    override fun onShow(attachedView: View?): Point {
        val s = super.onShow(attachedView)
        if (anim != -1)
            popupWindow.animationStyle = anim
        return s
    }
}