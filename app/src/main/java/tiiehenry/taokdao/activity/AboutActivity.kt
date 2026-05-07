package tiiehenry.taokdao.activity

import android.os.Bundle

class AboutActivity : MarkedActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadAssets("doc/app/about.md")
    }
}