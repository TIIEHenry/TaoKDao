package taokdao.window.toolpages.event.tag;

import androidx.annotation.NonNull;

import taokdao.api.event.tag.IEventTag;
import taokdao.api.main.IMainContext;
import tiiehenry.ideditor.R;

public class InitializerTag implements IEventTag {

    public InitializerTag() {
    }

    @NonNull
    @Override
    public String getTag(@NonNull IMainContext main) {
        return main.getString(R.string.event_initializer_prefix);
    }

}