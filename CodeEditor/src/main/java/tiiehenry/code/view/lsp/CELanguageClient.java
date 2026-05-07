package tiiehenry.code.view.lsp;

import android.util.Log;

import org.eclipse.lsp4j.MessageActionItem;
import org.eclipse.lsp4j.MessageParams;
import org.eclipse.lsp4j.PublishDiagnosticsParams;
import org.eclipse.lsp4j.ShowMessageRequestParams;
import org.eclipse.lsp4j.services.LanguageClient;

import java.util.concurrent.CompletableFuture;

public class CELanguageClient implements LanguageClient {
    public final LSPCodeEditor editor;

    public CELanguageClient(LSPCodeEditor editor) {
        this.editor = editor;
    }

    @Override
    public void telemetryEvent(Object object) {

    }

    @Override
    public void publishDiagnostics(PublishDiagnosticsParams diagnostics) {

    }

    @Override
    public void showMessage(MessageParams message) {
        switch (message.getType()){
            case Log:{
                Log.d("CEshowMessage",message.getMessage());
                break;
            }
            case Info:{
                Log.i("CEshowMessage",message.getMessage());
                break;
            }
            case Error:{
                Log.e("CEshowMessage",message.getMessage());
                break;
            }
            case Warning:{
                Log.w("CEshowMessage",message.getMessage());
                break;
            }
        }
    }

    @Override
    public CompletableFuture<MessageActionItem> showMessageRequest(ShowMessageRequestParams requestParams) {
        return null;
    }

    @Override
    public void logMessage(MessageParams message) {
        switch (message.getType()){
            case Log:{
                Log.d("CodeEditor",message.getMessage());
                break;
            }
            case Info:{
                Log.i("CodeEditor",message.getMessage());
                break;
            }
            case Error:{
                Log.e("CodeEditor",message.getMessage());
                break;
            }
            case Warning:{
                Log.w("CodeEditor",message.getMessage());
                break;
            }
        }
    }
}
