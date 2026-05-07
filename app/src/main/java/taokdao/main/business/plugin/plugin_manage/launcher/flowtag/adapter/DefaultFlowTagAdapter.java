package taokdao.main.business.plugin.plugin_manage.launcher.flowtag.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import tiiehenry.ideditor.R;


/**
 * 默认流标签布局适配器
 *
 * @author xuexiang
 * @since 2019/1/14 下午10:00
 */
public class DefaultFlowTagAdapter extends BaseTagAdapter<String, TextView> {

    public DefaultFlowTagAdapter(Context context) {
        super(context);
    }

    @Override
    protected TextView newViewHolder(View convertView) {
        return (TextView) convertView.findViewById(R.id.tv_tag_item);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.flowtag_layout_item;
    }

    @Override
    protected void convert(TextView textView, String item, int position) {
        textView.setText(item);
    }
}
