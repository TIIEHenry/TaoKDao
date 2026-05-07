package taokdao.main.business.setting_main.page

import android.content.Context
import taokdao.api.data.bean.Properties
import taokdao.api.data.mmkv.IMMKV
import taokdao.api.setting.preference.base.IPreference
import taokdao.api.setting.preference.wrapped.CategoryPreference
import taokdao.api.setting.preference.wrapped.CheckboxPreference
import taokdao.api.setting.preference.wrapped.SwitchPreference
import taokdao.api.setting.preference.wrapped.TitlePreference
import taokdao.main.business.session_control.SessionControlVariable
import tiiehenry.ideditor.R


class PageSession(val mmkv: IMMKV, val context: Context) {

    class GroupPreservation(main: Context) : TitlePreference(Properties("preservation", main, R.string.main_setting_preservation)) {
        val saveSession = Properties("saveSession", main, R.string.main_setting_savesession)
        val saveOpenedFiles = Properties("saveOpenedFiles", main, R.string.main_setting_saveopenedfiles)
        val recordOpenedFiles = Properties("recordOpenedFiles", main, R.string.main_setting_recordopenedfiles)
        val recordOpenedProject = Properties("recordOpenedProject", main, R.string.main_setting_recordopenedproject)
    }

    class GroupRecovery(main: Context) : TitlePreference(Properties("recovery", main, R.string.main_setting_recovery)) {
        val restoreSession = Properties("restoreSession", main, R.string.main_setting_restoresession)
        val openRecordedFiles = Properties("openRecordedFiles", main, R.string.main_setting_openrecordedfiles)
        val openRecordedProject = Properties("openRecordedProject", main, R.string.main_setting_openrecordedproject)
    }

    fun getPage(): CategoryPreference {
        val session = CategoryPreference(Properties("session", context, R.string.main_setting_session))
        val preservation = getGroupPreservation()
        val recovery = getGroupRecovery()

        val list = mutableListOf<IPreference<*>>().apply {
            add(preservation)
            addAll(preservation.numberList)
            add(recovery)
            addAll(recovery.numberList)
        }
        session.numberList = list
        return session
    }

    private fun getGroupPreservation(): TitlePreference {
        val preservation = GroupPreservation(context)
        val saveOpenedFiles = CheckboxPreference(mmkv, SessionControlVariable.saveOpenedFiles, preservation.saveOpenedFiles) { isOn ->
            SessionControlVariable.saveOpenedFiles = isOn
        }
        val recordOpenedFiles = CheckboxPreference(mmkv, SessionControlVariable.recordOpenedFiles, preservation.recordOpenedFiles) { isOn ->
            SessionControlVariable.recordOpenedFiles = isOn

        }
        val recordOpenedProject = CheckboxPreference(mmkv, SessionControlVariable.recordOpenedProject, preservation.recordOpenedProject) { isOn ->
            SessionControlVariable.recordOpenedProject = isOn
        }

        val saveSession = SwitchPreference(mmkv, SessionControlVariable.saveSession, preservation.saveSession) { isOn ->
            SessionControlVariable.saveSession = isOn
            listOf(saveOpenedFiles, recordOpenedFiles, recordOpenedProject).forEach {
                it.setEnable(isOn)
            }
        }
        preservation.numberList = listOf(saveSession, saveOpenedFiles, recordOpenedFiles, recordOpenedProject)
        return preservation
    }

    private fun getGroupRecovery(): TitlePreference {
        val recovery = GroupRecovery(context)
        val openRecordedFiles = CheckboxPreference(mmkv, SessionControlVariable.openRecordedFiles, recovery.openRecordedFiles) { isOn ->
            SessionControlVariable.openRecordedFiles = isOn
        }
        val openRecordedProject = CheckboxPreference(mmkv, SessionControlVariable.openRecordedProject, recovery.openRecordedProject) { isOn ->
            SessionControlVariable.openRecordedProject = isOn
        }
        val restoreSession = SwitchPreference(mmkv, SessionControlVariable.restoreSession, recovery.restoreSession) { isOn ->
            SessionControlVariable.restoreSession = isOn
            listOf(openRecordedFiles, openRecordedProject).forEach {
                it.setEnable(isOn)
            }
        }
        recovery.numberList = listOf(restoreSession, openRecordedFiles, openRecordedProject)
        return recovery
    }
}
