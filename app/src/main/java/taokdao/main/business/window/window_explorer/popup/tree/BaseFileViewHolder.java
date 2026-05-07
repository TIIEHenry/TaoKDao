package taokdao.main.business.window.window_explorer.popup.tree;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import tiiehenry.ideditor.R;
import tiiehenry.taokdao.ui.view.treeview.TreeBinder;

public class BaseFileViewHolder extends TreeBinder.ViewHolder {
    public ImageView ivIcon;
    public TextView tvName;

    public BaseFileViewHolder(View rootView) {
        super(rootView);
        ivIcon = getView(R.id.iv_icon);
        tvName = getView(R.id.tv_name);
    }
}
