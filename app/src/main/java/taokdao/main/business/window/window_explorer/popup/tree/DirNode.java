package taokdao.main.business.window.window_explorer.popup.tree;

import android.graphics.drawable.Drawable;

import java.io.File;

import tiiehenry.taokdao.ui.view.treeview.LayoutItemType;

public class DirNode implements LayoutItemType {

    public Drawable icon;
    public String label;
    public File path;

    public DirNode(String dirName, Drawable icon) {
        this.label = dirName;
        this.icon = icon;
    }

    public DirNode(File path) {
        this.label = path.getName();
        this.path = path;
    }

    public DirNode(String dirName, File path) {
        this.label = dirName;
        this.path = path;
    }

    @Override
    public int getLayoutId() {
        return DirNodeBinder.layout;
    }


}
