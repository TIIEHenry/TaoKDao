package tiiehenry.code.language.css;

import tiiehenry.code.LexTask;
import tiiehenry.code.Lexer;
import tiiehenry.code.language.Language;

public class CssLanguage extends Language {
    private static class SingletonInner {
        private static Language language = new CssLanguage();
    }

    public static Language getInstance() {
        return SingletonInner.language;
    }

    @Override
    public String getName() {
        return "Css";
    }
    @Override
    public String getLineNoteStringStart() {
        return "/*";
    }

    @Override
    public String getLineNoteStringEnd() {
        return "*/";
    }

    @Override
    public String getRegionNoteStringStart() {
        return "/*";
    }

    @Override
    public String getRegionNoteStringMiddle() {
        return "";
    }

    @Override
    public String getRegionNoteStringEnd() {
        return "*/";
    }

    @Override
    public String getRegionDocNoteStringStart() {
        return "/*";
    }

    @Override
    public String getRegionDocNoteStringMiddle() {
        return "*";
    }

    @Override
    public String getRegionDocNoteStringEnd() {
        return "*/";
    }

    @Override
    public LexTask newLexTask() {
        return new CssLexTask(this);
    }
}
