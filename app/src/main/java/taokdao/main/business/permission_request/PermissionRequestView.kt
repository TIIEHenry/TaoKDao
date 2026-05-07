package taokdao.main.business.permission_request

import com.qw.soul.permission.bean.Permission
import tiiehenry.android.ui.dialogs.api.strategy.Dialogs
import tiiehenry.ideditor.R

interface PermissionRequestView : PermissionRequestContract.V {

    override fun onNecessaryPermissionDenied(allPermissions: Array<out Permission>?) {
    }

    override fun showRequestPermissionDialog() {
        Dialogs.global
                .asConfirm()
                .title(R.string.business_permission_request_dialog_title)
                .content(R.string.business_permission_request_permission_denied)
                .positiveText(android.R.string.ok)
                .onPositive {
                    permissionRequestPresenter.checkNecessaryPermission()
                }
                .cancelable(false)
                .show()
    }
}