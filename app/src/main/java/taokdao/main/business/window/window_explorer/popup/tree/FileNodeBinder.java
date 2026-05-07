package taokdao.main.business.window.window_explorer.popup.tree;

import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;

import taokdao.main.IMainView;
import taokdao.main.business.drawable_manage.DrawableManagePresenter;
import tiiehenry.ideditor.R;
import tiiehenry.taokdao.ui.view.treeview.TreeBinder;
import tiiehenry.taokdao.ui.view.treeview.TreeNode;
import tiiehenry.taokdao.ui.view.treeview.TreeNodeListener;

public class FileNodeBinder extends TreeBinder<FileNode, FileNodeBinder.ViewHolder> {

    public static int layout = R.layout.explorer_public_tree_item_leaf;

    public FileNodeBinder(TreeNodeListener<FileNode, ViewHolder> listener) {
        super(listener);
    }

    @Override
    public ViewHolder provideViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public void bindView(@NonNull ViewHolder holder, int position, @NonNull TreeNode<FileNode> node) {
        FileNode fileNode = node.getContent();

        holder.tvName.setText(fileNode.label);

        Drawable db = fileNode.icon != null ?
                fileNode.icon : DrawableManagePresenter.Companion.getForFile((IMainView) holder.ivIcon.getContext(), fileNode.path);
        holder.ivIcon.setImageDrawable(db);
    }

    @Override
    public int getLayoutId() {
        return layout;
    }

    public static class ViewHolder extends BaseFileViewHolder {

        ViewHolder(View rootView) {
            super(rootView);
        }
    }
}
