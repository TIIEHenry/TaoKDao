package tiiehenry.taokdao.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import tiiehenry.ideditor.R
import io.noties.markwon.Markwon
import tiiehenry.ideditor.databinding.ActivityMarkedBinding
import java.io.File


open class MarkedActivity : AppCompatActivity(), MenuItem.OnMenuItemClickListener {

    private lateinit var markwon: Markwon
    private val binding by lazy { ActivityMarkedBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        markwon = Markwon.create(this)


        intent?.let {
            handleIntent(it)
        }
    }

    private fun handleIntent(it: Intent) {
        if (handleIntentByExtra(it)) {
            return
        }
        if (handleIntentByDataString(it)) {
            return
        }
        if (handleIntentByData(it)) {
            return
        }
    }

    private fun handleIntentByData(it: Intent): Boolean {
        val path = it.data?.path ?: return false
        return processWithPath(path)
    }

    private fun handleIntentByDataString(it: Intent): Boolean {
        val path = it.dataString ?: return false
        return processWithPath(path)
    }

    private fun handleIntentByExtra(it: Intent): Boolean {
        val path = it.getStringExtra(EXTRA_KEY_PATH) ?: return false
        return processWithPath(path)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menu.add(0, R.string.back, 0, R.string.back).setOnMenuItemClickListener(this)
            .setShowAsAction(2)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        item?.let {
            if (item.itemId == R.string.back) {
                finish()
            }
        }
        return false
    }

    fun loadAssets(name: String) {
        assets.open(name).use {
            setMarkdown(it.bufferedReader().readText())
        }
    }

    fun setMarkdown(text: String) {
        markwon.setMarkdown(binding.tvMd, text)
    }

    fun processWithPath(path: String): Boolean {
        when {
            path.startsWith("file://") -> {
                val spath = path.substring("file://".length)
                val file = File(spath)
                if (file.exists()) {
                    title = file.nameWithoutExtension
                    setMarkdown(file.readText())
                    return true
                }
                return false
            }

            File(path).isFile -> {
                title = File(path).nameWithoutExtension
                setMarkdown(File(path).readText())
                return true
            }

            else -> return false
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    companion object {
        const val EXTRA_KEY_PATH = "path"
    }
}
