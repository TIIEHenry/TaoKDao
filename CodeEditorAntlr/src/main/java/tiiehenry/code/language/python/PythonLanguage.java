package tiiehenry.code.language.python;


import tiiehenry.code.LexTask;
import tiiehenry.code.Lexer;
import tiiehenry.code.language.Language;

public class PythonLanguage extends Language {
    private static class SingletonInner {
        private static Language language = new PythonLanguage();
    }

    public static Language getInstance() {
        return SingletonInner.language;
    }
    @Override
    public String getName() {
        return "Python";
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
        return "\"\"\"";
    }

    @Override
    public String getRegionNoteStringMiddle() {
        return "";
    }

    @Override
    public String getRegionNoteStringEnd() {
        return "\"\"\"";
    }

    @Override
    public String getRegionDocNoteStringStart() {
        return "'''";
    }

    @Override
    public String getRegionDocNoteStringMiddle() {
        return "";
    }

    @Override
    public String getRegionDocNoteStringEnd() {
        return "'''";
    }

    @Override
    public LexTask newLexTask() {
        return new PythonLexTask(this);
    }
}

