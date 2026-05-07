package taokdao.main.business.window.window_explorer.popup.tree;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import taokdao.main.IMainView;
import taokdao.main.business.drawable_manage.DrawableManagePresenter;
import tiiehenry.ideditor.R;
import tiiehenry.taokdao.ui.view.treeview.TreeBinder;
import tiiehenry.taokdao.ui.view.treeview.TreeNode;
import tiiehenry.taokdao.ui.view.treeview.TreeNodeListener;

public class DirNodeBinder extends TreeBinder<DirNode, DirNodeBinder.ViewHolder> {


    public static int layout = R.layout.explorer_public_tree_item_branch;

    public DirNodeBinder(TreeNodeListener<DirNode, ViewHolder> listener) {
        super(listener);
    }

    @Override
    public ViewHolder provideViewHolder(View itemView) {
        return new ViewHolder(itemView);
    }

    @Override
    public void bindView(@NonNull ViewHolder holder, int position, @NonNull TreeNode<DirNode> node) {
        DirNode dirNode = node.getContent();

        holder.ivArrow.setRotation(node.isExpand() ? 90 : 0);
        holder.ivArrow.setVisibility(node.isLeaf() ? View.INVISIBLE : View.VISIBLE);

        holder.tvName.setText(dirNode.label);
        DrawableManagePresenter presenter = ((IMainView) holder.ivIcon.getContext()).getMain().getDrawableManagePresenter();
        Drawable db = dirNode.icon != null ?
                dirNode.icon : presenter.getDrawableForDirName(dirNode.path.getName());
        db = db != null ? db : presenter.getDefaultDrawableForDirName();
        holder.ivIcon.setImageDrawable(db);
    }

    @Override
    public int getLayoutId() {
        return layout;
    }

    public static class ViewHolder extends BaseFileViewHolder {
        public ImageView ivArrow;

        ViewHolder(View rootView) {
            super(rootView);
            ivArrow = getView(R.id.iv_arrow);
        }

    }
}
