package taokdao.main.business.window.window_explorer.popup

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import taokdao.api.ui.explorer.IExplorer
import tiiehenry.android.fragment.adapter.DynamicFragmentStateAdapter


class ExplorerFragmentAdapter(fm: FragmentManager)
    : DynamicFragmentStateAdapter<ExplorerFragmentAdapter, IExplorer>(fm) {

    override fun getItem(position: Int): Fragment {
        return dataList[position].fragment
    }


    override fun getPageTitle(position: Int): CharSequence {
        return dataList[position].label
    }

    override fun getItemPosition(`object`: Any): Int {
        return POSITION_NONE
    }

    override fun getInstance(): ExplorerFragmentAdapter {
        return this
    }
}