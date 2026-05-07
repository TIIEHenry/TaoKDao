package taokdao.window.toolpages.event.sender;

import androidx.annotation.NonNull;

import taokdao.api.event.send.IEventSender;
import taokdao.api.event.send.wrapped.EventSender;
import taokdao.window.toolpages.event.tag.InitializerTag;
import tiiehenry.taokdao.ui.view.progressview.ProgressView;

public class InitializerSender {

    public InitializerSender() {
    }

    public IEventSender stage(@NonNull ProgressView.Model model) {
        return new EventSender(new InitializerTag(), "Stage: " + model.name);
    }

}