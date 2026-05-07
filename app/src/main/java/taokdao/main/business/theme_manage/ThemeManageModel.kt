package taokdao.main.business.theme_manage

import android.content.res.Configuration

class ThemeManageModel : ThemeManageContract.M {
    override fun observeUiMode(newConfig: Configuration, isDarkNow: Boolean): Int {
        when (newConfig.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> {
                if (!isDarkNow)
                    return Configuration.UI_MODE_NIGHT_YES
            }
            Configuration.UI_MODE_NIGHT_NO -> {
                if (isDarkNow)
                    return Configuration.UI_MODE_NIGHT_NO
            }
        }
        return -1
    }
}
