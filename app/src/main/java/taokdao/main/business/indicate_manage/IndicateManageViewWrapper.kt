package taokdao.main.business.indicate_manage

import taokdao.main.MainActivity
import tiiehenry.ideditor.databinding.ActivityMainBinding
import tiiehenry.taokdao.ui.setting.LeftTopSettingPopup


class IndicateManageViewWrapper(
    val view: IndicateManageContract.V,
    override val indicateManagePresenter: IndicateManagePresenter
) : IndicateManageContract.VW {
    val binding = ActivityMainBinding.bind(view.contentView)
    override val startIndicator = TextViewIndicator(binding.mainToolbarIndicatorStartTv, {
        view.contentManager.showSettingWindow()
    }, {
        LeftTopSettingPopup(
            view,
            (view as MainActivity).settingList,
            binding.mainToolbarIndicatorStartTv
        ).showAt()
        true
    })

    override val endIndicator = TextViewIndicator(binding.mainToolbarIndicatorEndTv, {
        view.pluginManager.showPluginLauncher()
    }, {
        view.contentManager.showListWindow()
        true
    })

}