package taokdao.main.business.window.window_explorer.popup.tree;

import android.graphics.drawable.Drawable;

import java.io.File;

import tiiehenry.taokdao.ui.view.treeview.LayoutItemType;

public class FileNode implements LayoutItemType {

    public Drawable icon;
    public String label;
    public File path;

    public FileNode(File path) {
        label = path.getName();
        this.path = path;
    }

    public FileNode(String label, File path) {
        this.label = label;
        this.path = path;
    }

    @Override
    public int getLayoutId() {
        return FileNodeBinder.layout;
    }


}
