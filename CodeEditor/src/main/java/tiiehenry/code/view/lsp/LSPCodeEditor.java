package tiiehenry.code.view.lsp;

import android.content.Context;
import android.util.AttributeSet;

import org.eclipse.lsp4j.jsonrpc.Launcher;
import org.eclipse.lsp4j.launch.LSPLauncher;
import org.eclipse.lsp4j.services.LanguageClient;
import org.eclipse.lsp4j.services.LanguageClientAware;
import org.eclipse.lsp4j.services.LanguageServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import tiiehenry.code.view.CodeEditor;
import tiiehenry.code.view.lsp.CELanguageClient;

public class LSPCodeEditor extends CodeEditor {
    private CELanguageClient languageClient;
    private Launcher<LanguageServer> lspLauncher;

    public LSPCodeEditor(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initLSP();
    }

    public LSPCodeEditor(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initLSP();
    }

    public LSPCodeEditor(Context context) {
        super(context);
        initLSP();
    }

    private final InputStream lspInput = new InputStream() {
        @Override
        public int read() throws IOException {
            return 0;
        }
    };
    private final OutputStream lspOutput = new OutputStream() {
        @Override
        public void write(int b) throws IOException {

        }

    };

    private void initLSP() {
        languageClient = new CELanguageClient(this);
//        if (myImpl instanceof LanguageClientAware) {
//            LanguageClient client = launcher.getRemoteProxy();
//            ((LanguageClientAware)myImpl).connect(client);
//        }
        lspLauncher = LSPLauncher.createClientLauncher(languageClient, lspInput, lspOutput);
        lspLauncher.startListening();

    }
}
