package taokdao.main.business.permission_request

import android.Manifest
import android.os.Build
import android.os.Environment
import com.qw.soul.permission.SoulPermission
import com.qw.soul.permission.bean.Permission
import com.qw.soul.permission.bean.Permissions
import com.qw.soul.permission.callbcak.CheckRequestPermissionsListener

class PermissionRequestPresenter(private val view: PermissionRequestContract.V) : PermissionRequestContract.P {
    private val model = PermissionRequestModel()
    override fun checkNecessaryPermission() {
        SoulPermission.getInstance().checkAndRequestPermissions(
                Permissions.build(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE), object : CheckRequestPermissionsListener {
            override fun onAllPermissionOk(allPermissions: Array<out Permission>?) {
                checkManageExternalStorage()
            }

            private var onNecessaryPermissionTimes: Int = 0

            override fun onPermissionDenied(refusedPermissions: Array<out Permission>?) {
                view.onNecessaryPermissionDenied(refusedPermissions)
                onNecessaryPermissionTimes++
                if (onNecessaryPermissionTimes == 9) {
                    view.activity.finish()
                } else {
                    view.showRequestPermissionDialog()
                }
            }

        })

    }

    override fun checkManageExternalStorage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!Environment.isExternalStorageManager()) {
                view.showManageStoragePermissionDialog()
                return
            }
        }
        view.onNecessaryPermissionOK(null)
    }
}
