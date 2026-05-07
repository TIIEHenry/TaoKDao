package tiiehenry.taokdao.activity

import android.net.Uri
import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.just.agentweb.AgentWeb
import com.just.agentweb.AgentWeb.PreAgentWeb
import com.just.agentweb.WebViewClient
import tiiehenry.ideditor.R
import tiiehenry.ideditor.databinding.ActivityWebBinding

class WebActivity : AppCompatActivity(), MenuItem.OnMenuItemClickListener {
    lateinit var mAgentWeb: PreAgentWeb

    private val mWebViewClient: WebViewClient = object : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
            val url = request.url.toString()
            if (url.startsWith("file://")) {
                mAgentWeb.go(url)
                return true
            }
            //            return shouldOverrideUrlLoading(view,.toString());
            return super.shouldOverrideUrlLoading(view, request)
        }

        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            if (url.startsWith("file://")) {
                mAgentWeb.go(url)
                return true
            }
            return super.shouldOverrideUrlLoading(view, url)
        }
    }
    lateinit var binding: ActivityWebBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mAgentWeb = AgentWeb.with(this)
            .setAgentWebParent(binding.llContainer, LinearLayout.LayoutParams(-1, -1))
            .useDefaultIndicator()
            .setWebViewClient(mWebViewClient)
            .createAgentWeb()
            .ready()
        mAgentWeb.get().agentWebSettings.webSettings.useWideViewPort = false

        intent?.let {

            when {
                !it.dataString.isNullOrEmpty() ->
                    processWithDataString(it.dataString!!)

                it.data != null ->
                    processWithData(it.data!!)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menu.add(0, R.string.back, 0, R.string.back).setOnMenuItemClickListener(this)
            .setShowAsAction(2)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPause() {
        mAgentWeb.get().webLifeCycle.onPause()
        super.onPause()
    }

    override fun onResume() {
        mAgentWeb.get().webLifeCycle.onResume()
        super.onResume()
    }

    override fun onDestroy() {
        mAgentWeb.get().webLifeCycle.onDestroy()
        super.onDestroy()
    }

    override fun onMenuItemClick(item: MenuItem): Boolean {
        item?.let {
            if (item.itemId == R.string.back) {
                finish()
            }
        }
        return false
    }


    private fun processWithDataString(dataString: String) {
        processWithPath(dataString)
    }

    private fun processWithData(data: Uri) {
        val path = data.path
        path?.let { processWithPath(it) }
    }

    private fun processWithPath(path: String) {
        mAgentWeb.go(path)
//        when {
//            path.startsWith("file://") -> {
//                mAgentWeb.go(path)
//            }
//            File(path).isFile -> {
//                mAgentWeb.go(path)
//            }
//            else -> 0
//        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return if (mAgentWeb.get().handleKeyEvent(keyCode, event)) {
            true
        } else super.onKeyDown(keyCode, event)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

}
