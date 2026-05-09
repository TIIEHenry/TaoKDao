package taokdao.main.business.permission_request

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
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

    override fun showManageStoragePermissionDialog() {
        Dialogs.global
                .asConfirm()
                .title(R.string.business_permission_request_dialog_title)
                .content(R.string.business_permission_request_manage_storage_needed)
                .positiveText(android.R.string.ok)
                .onPositive {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                        val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION).apply {
                            data = Uri.parse("package:${activity.packageName}")
                        }
                        activity.startActivity(intent)
                        // Show follow-up dialog to re-check after user returns
                        showManageStorageRecheckDialog()
                    }
                }
                .cancelable(false)
                .show()
    }

    private fun showManageStorageRecheckDialog() {
        Dialogs.global
                .asConfirm()
                .title(R.string.business_permission_request_dialog_title)
                .content(R.string.business_permission_request_manage_storage_recheck)
                .positiveText(android.R.string.ok)
                .onPositive {
                    permissionRequestPresenter.checkManageExternalStorage()
                }
                .cancelable(false)
                .show()
    }
}
