package taokdao.codeeditor.layout.quickedit;


import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;

import taokdao.api.main.IMainContext;
import taokdao.codeeditor.CodeIEditor;

public class QuickEditMenu {


    public String label;//唯一标识
    public Drawable icon;
    public MenuCallback callback;

    public QuickEditMenu(String label, MenuCallback callback) {
        this.label = label;
        this.callback = callback;
    }

    @Override
    public int hashCode() {
        return label.hashCode();
    }

    public interface MenuCallback {
        void onMenuAction(IMainContext main, @NonNull CodeIEditor editor);
    }
}
