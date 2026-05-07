package tiiehenry.taokdao.ui.view.treeview;


import android.util.SparseArray;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public abstract class TreeBinder<T extends LayoutItemType, VH extends RecyclerView.ViewHolder> implements LayoutItemType {
    private final TreeNodeListener<T, VH> listener;

    public TreeBinder(TreeNodeListener<T, VH> listener) {
        this.listener = listener;
    }

    public abstract VH provideViewHolder(View itemView);

    public abstract void bindView(@NonNull VH holder, int position, @NonNull TreeNode<T> node);

    public TreeNodeListener<T, VH> getListener() {
        return listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final View rootView;
        private final SparseArray<View> viewSparseArray = new SparseArray<>();

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
        }

        public <T extends View> T getView(@IdRes int viewId) {
            View view = viewSparseArray.get(viewId);
            if (view == null) {
                view = rootView.findViewById(viewId);
                viewSparseArray.put(viewId, view);
            }
            return (T) view;
        }
    }

}