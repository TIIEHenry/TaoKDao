package tiiehenry.taokdao.ui.view.treeview;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public interface TreeNodeListener<T extends LayoutItemType, VH extends RecyclerView.ViewHolder> {
    /**
     * @return weather consume the click event.
     */
    boolean onClick(@NonNull TreeNode<T> node, @NonNull VH holder, @NonNull TreeViewAdapter<T, VH> adapter);

    boolean onLongClick(@NonNull TreeNode<T> node, @NonNull VH holder, @NonNull TreeViewAdapter<T, VH> adapter);

}
