package tiiehenry.code.language.bash;

import tiiehenry.code.LexTask;
import tiiehenry.code.Lexer;
import tiiehenry.code.language.Language;

public class BashLanguage  extends Language {
    private static class SingletonInner {
        private static Language language = new BashLanguage();
    }

    public static Language getInstance() {
        return SingletonInner.language;
    }

    @Override
    public String getName() {
        return "Bash";
    }
    
    @Override
    public String getLineNoteStringStart() {
        return "#";
    }

    @Override
    public String getLineNoteStringEnd() {
        return "";
    }

    @Override
    public String getRegionNoteStringStart() {
        return ":<<EOF";
    }

    @Override
    public String getRegionNoteStringMiddle() {
        return "";
    }

    @Override
    public String getRegionNoteStringEnd() {
        return "EOF";
    }

    @Override
    public String getRegionDocNoteStringStart() {
        return ":<<EOF!";
    }

    @Override
    public String getRegionDocNoteStringMiddle() {
        return "";
    }

    @Override
    public String getRegionDocNoteStringEnd() {
        return "!";
    }

    @Override
    public LexTask newLexTask() {
        return null;
//        return new BashLexTask(this);
    }
}
