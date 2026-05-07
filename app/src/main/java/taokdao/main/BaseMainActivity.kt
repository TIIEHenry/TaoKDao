package taokdao.main


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import taokdao.api.base.annotation.relation.MainMethod
import taokdao.api.data.mmkv.IMMKV
import taokdao.api.event.tag.IEventTag
import taokdao.api.file.system.IFileSystem
import taokdao.window.toolpages.event.EventItem
import taokdao.window.toolpages.event.EventTypes
import tiiehenry.ktx.content.getHeight
import tiiehenry.ktx.content.getWidth
import tiiehenry.ktx.res.getAttrColor
import tiiehenry.ktx.res.getDimen
import tiiehenry.taokdao.app.App
import ideditor.api.skin.R

abstract class BaseMainActivity : AppCompatActivity(), IMainView, CoroutineScope by MainScope() {

    override fun getContext(): Context = this

    override fun getFileSystem(): IFileSystem = application as App

    override fun onCreate(savedInstanceState: Bundle?) {
        resetPolicy()

        super.onCreate(savedInstanceState)
    }


    override fun onDestroy() {
        cancel()
        super.onDestroy()
    }

    override fun finish() {
        super.finish()
        overridePendingTransitionExit()
    }

    override fun startActivity(intent: Intent?) {
        super.startActivity(intent)
        overridePendingTransitionOpen()
    }

    override fun getAttrColor(id: Int) = context.getAttrColor(id)
    override fun getDimen(id: Int) = context.getDimen(id)


    override fun runOnUIThread(runnable: Runnable) {
        launchMain {
            runnable.run()
        }
    }

    override fun getMMKV(): IMMKV {
        return mmkvManager.getMMKV("main")
    }

    @MainMethod
    override fun log(tag: String, message: String) {
        launchMain {
            tabToolInternal.eventTabToolFragment.addEvent(EventItem(tag, message))
        }
    }

    override fun log(message: String) {
        log(EventTypes.DEFAULT, message)
    }

    override fun log(tag: IEventTag, message: String) {
        log(tag.getTag(this), message)
    }

    override fun log(tag: IEventTag, message: Int) {
        log(tag, getString(message))
    }

    override fun log(message: Int) {
        log(EventTypes.MAIN.getTag(this), getString(message))
    }

    @MainMethod
    override fun notify(tag: String, message: String) {
        launchMain {
            mainHandler.toast(message)
        }
    }

    override fun notify(message: String) {
        notify(EventTypes.DEFAULT.getTag(this), message)
    }

    override fun notify(message: Int) {
        notify(EventTypes.MAIN.getTag(this), getString(message))
    }

    override fun notify(tag: IEventTag, message: String) {
        notify(tag.getTag(this), message)
    }

    override fun notify(tag: IEventTag, message: Int) {
        notify(tag, getString(message))
    }

    override fun getWidth(): Int {
        return context.getWidth()
    }

    override fun getHeight(): Int {
        return context.getHeight()
    }

    private fun overridePendingTransitionOpen() {
        overridePendingTransition(R.anim.activity_open_enter, R.anim.activity_open_exit)
    }

    private fun overridePendingTransitionExit() {
        overridePendingTransition(R.anim.activity_close_enter, R.anim.activity_close_exit)
    }

    private fun resetPolicy() {
        StrictMode.setThreadPolicy(StrictMode.ThreadPolicy.Builder().permitAll().build())
        val builder = StrictMode.VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())//绕过provider
    }

}