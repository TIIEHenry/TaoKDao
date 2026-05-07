package taokdao.main.business.build_manage

import taokdao.api.data.mmkv.IMMKV
import taokdao.api.data.mmkv.IMMKVManager
import taokdao.api.file.build.FileBuilderPool
import taokdao.api.project.bean.Project

class BuildManageModel : BuildManageContract.M {
    private lateinit var mmkvFile: IMMKV

    override fun init(mmkvManager: IMMKVManager) {
        FileBuilderPool.newInstance()
        mmkvFile = mmkvManager.getMMKV("buildmanage_file")
    }

    override fun setDefaultBuilderId(suffix: String, id: String) {
        mmkvFile.encode(suffix, id)
    }


    override fun getDefaultBuilderId(suffix: String): String? {
        return mmkvFile.decodeString(suffix)
    }


    override fun setDefaultBuilderOptionId(suffix: String, builderId: String, optionId: String) {
        mmkvFile.encode("$suffix.$builderId", optionId)
    }


    override fun getDefaultBuilderOptionId(suffix: String, builderId: String): String? {
        return mmkvFile.decodeString("$suffix.$builderId")
    }

    override fun setDefaultProjectBuilderOptionId(project: Project, optionId: String) {
        mmkvFile.encode(project.projectDir.absolutePath, optionId)
    }


    override fun getDefaultProjectBuilderOptionId(project: Project): String? {
        return mmkvFile.decodeString(project.projectDir.absolutePath)
    }


}
