package taokdao.main.business.file.file_operate

import taokdao.api.file.operate.FileOperatorPool

class FileOperateModel : FileOperateContract.M {
    override fun initFileOperatorPool() {
        FileOperatorPool.newInstance()
        FileOperatorPool.getInstance().clear()
    }
}