package taokdao.main.business.indicate_manage;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import taokdao.api.ui.indicate.IIndicator;

public class TextViewIndicator implements IIndicator {
    private final TextView textView;
    private final View.OnClickListener onClickListener;
    private final View.OnLongClickListener onLongClickListener;

    public TextViewIndicator(@NonNull TextView textView, @Nullable View.OnClickListener onClickListener, @Nullable View.OnLongClickListener onLongClickListener) {
        this.textView = textView;
        this.onClickListener = onClickListener;
        textView.setOnClickListener(onClickListener);
        this.onLongClickListener = onLongClickListener;
        textView.setOnLongClickListener(onLongClickListener);
    }

    @Override
    public void setText(@NonNull String text) {
        textView.setText(text);
    }

    @Override
    public void setVisibility(boolean show) {
        if (show)
            textView.setVisibility(View.VISIBLE);
        else textView.setVisibility(View.GONE);
    }

    @Nullable
    @Override
    public View.OnClickListener getDefaultOnClickListener() {
        return onClickListener;
    }

    @Override
    public void setOnClickListener(@Nullable View.OnClickListener onClickListener) {
        textView.setOnClickListener(onClickListener);
    }

    @Nullable
    @Override
    public View.OnLongClickListener getDefaultOnLongClickListener() {
        return onLongClickListener;
    }

    @Override
    public void setOnLongClickListener(@Nullable View.OnLongClickListener onLongClickListener) {
        textView.setOnLongClickListener(onLongClickListener);
    }
}
