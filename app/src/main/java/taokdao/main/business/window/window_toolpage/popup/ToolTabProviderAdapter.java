package taokdao.main.business.window.window_toolpage.popup;

import androidx.annotation.NonNull;

import java.util.Collection;

import taokdao.api.ui.toolpage.group.tooltab.IToolTab;
import tiiehenry.android.view.recyclerview.adapter.InflateRecyclerAdapter;
import tiiehenry.android.view.recyclerview.adapter.SimpleIdRecyclerAdapter;
import tiiehenry.android.view.recyclerview.holder.RecyclerViewHolder;

public class ToolTabProviderAdapter extends SimpleIdRecyclerAdapter<IToolTab> {
    public ToolTabProviderAdapter(int layoutId) {
        super(layoutId);
    }

    public ToolTabProviderAdapter(int layoutId, @NonNull Collection<IToolTab> list) {
        super(layoutId, list);
    }

    public ToolTabProviderAdapter(int layoutId, @NonNull IToolTab[] data) {
        super(layoutId, data);
    }

    @Override
    protected void bindData(@NonNull RecyclerViewHolder recyclerViewHolder, int i, @NonNull IToolTab toolTab) {

    }

    @NonNull
    @Override
    public InflateRecyclerAdapter<IToolTab> getInstance() {
        return this;
    }
}
