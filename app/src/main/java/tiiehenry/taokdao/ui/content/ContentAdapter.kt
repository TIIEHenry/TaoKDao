package tiiehenry.taokdao.ui.content

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager.widget.PagerAdapter
import taokdao.api.ui.content.IContent
import tiiehenry.android.fragment.adapter.DynamicFragmentStateAdapter

class ContentAdapter(fm: FragmentManager) : DynamicFragmentStateAdapter<ContentAdapter, IContent>(fm) {

    override fun getItem(position: Int): Fragment {
        return dataList[position].fragment
    }

    override fun getPageTitle(position: Int): CharSequence {
        return /*if (dataList[position].editor.dataController.isChanged) {
            "*"
        } else {
            ""
        } + */dataList[position].label
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
    }

    //    override fun instantiateItem(container: ViewGroup, position: Int): Any {
//        return getItem(position)
//    }
    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }

    override fun getInstance(): ContentAdapter {
        return this
    }

}