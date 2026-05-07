package taokdao.main.business.menu_catagory.popup

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lxj.xpopup.core.PositionPopupView
import org.jetbrains.anko.find
import taokdao.api.main.IMainContext
import taokdao.api.main.menu.MainMenuCategory
import tiiehenry.ideditor.R

@SuppressLint("ViewConstructor")
class MenuPopupView(private val main: IMainContext, private val menuPopup: MenuPopup) : PositionPopupView(main.activity), View.OnClickListener {

    private val menuAdapter = MainMenuAdapter().apply {
        setOnItemClickListener { _, item, _ ->
            item.callback.onAction(main, main.contentManager.current?.editor)
            menuPopup.hide()
        }
    }

    private val idList = arrayOf(MainMenuCategory.FILE, MainMenuCategory.EDIT, MainMenuCategory.BUILD, MainMenuCategory.PROJECT, MainMenuCategory.DISPLAY, MainMenuCategory.TOOL, MainMenuCategory.SETTING)


    override fun onClick(v: View) {
        idList.forEach {
            if (it.id == v.id) {
                menuAdapter.refresh(it.list)
                findViewById<View>(it.id).setBackgroundResource(ideditor.api.skin.R.drawable.main_appbar_round_selected)
            } else {
                findViewById<View>(it.id).setBackgroundResource(ideditor.api.skin.R.drawable.main_appbar_round_selector)
            }
        }
    }

    override fun getImplLayoutId() = R.layout.toolbar_mainmenu_layout

    override fun onCreate() {
        idList.forEach {
            findViewById<View>(it.id).setOnClickListener(this)
        }
        findViewById<View>(MainMenuCategory.FILE.id).performClick()
        find<RecyclerView>(R.id.rv_main_menu).apply {
            layoutManager = LinearLayoutManager(main.activity, RecyclerView.VERTICAL, false)
            adapter = menuAdapter
        }
    }


}

