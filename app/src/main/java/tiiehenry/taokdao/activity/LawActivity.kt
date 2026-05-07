package tiiehenry.taokdao.activity

import android.os.Bundle

class LawActivity : MarkedActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadAssets("doc/app/law.md")
    }
}