package tiiehenry.code.language.javascript;

import tiiehenry.code.LexTask;
import tiiehenry.code.Lexer;
import tiiehenry.code.language.Language;
import tiiehenry.code.language.LanguageCFamily;

public class JavascriptLanguage extends LanguageCFamily {
    private static class SingletonInner {
        private static Language language = new JavascriptLanguage();
    }

    public static Language getInstance() {
        return SingletonInner.language;
    }
    @Override
    public String getName() {
        return "Javascript";
    }
    @Override
    public LexTask newLexTask() {
        return new JavascriptLexTask(this);
    }
}
