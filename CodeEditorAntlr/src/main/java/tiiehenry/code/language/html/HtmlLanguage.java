package tiiehenry.code.language.html;

import tiiehenry.code.LexTask;
import tiiehenry.code.Lexer;
import tiiehenry.code.language.Language;

public class HtmlLanguage extends Language {
    private static class SingletonInner {
        private static HtmlLanguage language = new HtmlLanguage();
    }

    private HtmlLanguage() {
        super();
//        indentChar = ' ';
    }

    public static HtmlLanguage getInstance() {
        return SingletonInner.language;
    }
    @Override
    public String getName() {
        return "Html";
    }
    @Override
    public String getLineNoteStringStart() {
        return "<!--";
    }

    @Override
    public String getLineNoteStringEnd() {
        return "-->";
    }

    @Override
    public String getRegionNoteStringStart() {
        return "<!--";
    }

    @Override
    public String getRegionNoteStringMiddle() {
        return "";
    }

    @Override
    public String getRegionNoteStringEnd() {
        return "-->";
    }

    @Override
    public String getRegionDocNoteStringStart() {
        return "<!--";
    }

    @Override
    public String getRegionDocNoteStringMiddle() {
        return "";
    }

    @Override
    public String getRegionDocNoteStringEnd() {
        return "-->";
    }

    @Override
    public LexTask newLexTask() {
        return new HtmlLexTask(this);
    }

}
