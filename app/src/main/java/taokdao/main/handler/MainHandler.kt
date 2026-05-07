package taokdao.main.handler

import android.os.Handler
import android.os.Message
import android.util.Log
import android.widget.Toast
import taokdao.main.IMainView

class MainHandler(val main: IMainView) : Handler() {
    private val TAG: String = javaClass.name
    private val toastBuilder: StringBuilder = StringBuilder()
    var lastShow: Long = 0

    override fun handleMessage(msg: Message) {
        super.handleMessage(msg)
        when (msg.what) {
            TOAST -> toast(msg.obj.toString())
            LOG -> log(msg.obj.toString())
            PRINT -> print(msg.obj.toString())
        }
    }

    fun toast(text: String) {
        val now = System.currentTimeMillis()
        if (now - lastShow > 1000) {
            toastBuilder.setLength(0)
            toastBuilder.append(text)
        } else {
            toastBuilder.append("\n")
            toastBuilder.append(text)
        }
        lastShow = now
        Toast.makeText(main.context, toastBuilder.toString(), Toast.LENGTH_SHORT).show()
    }

    fun log(text: String) {
        Log.i(TAG, text)
    }


    fun print(text: String) {
        Log.i(TAG, text)
        toast(text)
    }

    companion object {
        val TOAST = "toast".hashCode()
        val LOG = "log".hashCode()
        val PRINT = "print".hashCode()
    }
}