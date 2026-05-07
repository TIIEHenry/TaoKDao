package tiiehenry.code.language.java;

import tiiehenry.code.LexTask;
import tiiehenry.code.Lexer;
import tiiehenry.code.language.Language;
import tiiehenry.code.language.LanguageCFamily;

public class JavaLanguage extends LanguageCFamily {
    private static class SingletonInner {
        private static Language language = new JavaLanguage();
    }

    public static Language getInstance() {
        return SingletonInner.language;
    }
    @Override
    public String getName() {
        return "Java";
    }
    @Override
    public LexTask newLexTask() {
        return new JavaLexTask(this);
    }
}
