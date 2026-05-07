package taokdao.main.business.permission_request

import com.qw.soul.permission.bean.Permission
import taokdao.main.IMainView

interface PermissionRequestContract {
    interface V : IMainView {
        val permissionRequestPresenter: PermissionRequestPresenter
        fun onNecessaryPermissionOK(allPermissions: Array<out Permission>?)
        fun onNecessaryPermissionDenied(allPermissions: Array<out Permission>?)
        fun showRequestPermissionDialog()
    }

    interface P {

        fun checkNecessaryPermission()
    }

    interface M
}