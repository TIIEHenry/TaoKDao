package taokdao.main.business.window.window_toolpage.popup

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import taokdao.api.ui.toolpage.IToolPage
import tiiehenry.android.fragment.adapter.DynamicFragmentStateAdapter


class ToolPageFragmentAdapter(fm: FragmentManager) : DynamicFragmentStateAdapter<ToolPageFragmentAdapter, IToolPage>(fm) {

    override fun getItem(position: Int): Fragment {
        return dataList[position].fragment
    }

    override fun getPageTitle(position: Int): CharSequence {
        return dataList[position].prop.label
    }


    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }

    override fun getInstance(): ToolPageFragmentAdapter {
        return this
    }
}