package taokdao.window.toolpages.event.tag;

import androidx.annotation.NonNull;

import taokdao.api.event.tag.IEventTag;
import taokdao.api.main.IMainContext;
import taokdao.api.ui.content.IContent;
import tiiehenry.ideditor.R;

public class LogcatReaderTag implements IEventTag {
    private final IContent tabContent;

    public LogcatReaderTag(@NonNull IContent tabContent) {
        this.tabContent = tabContent;
    }

    @NonNull
    @Override
    public String getTag(@NonNull IMainContext main) {
        return main.getString(R.string.event_content_prefix) + "(" + tabContent.id() + ")";
    }

}
