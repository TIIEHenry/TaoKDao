package tiiehenry.code.language.kotlin;

import tiiehenry.code.LexTask;
import tiiehenry.code.Lexer;
import tiiehenry.code.language.Language;
import tiiehenry.code.language.LanguageCFamily;

public class KotlinLanguage extends LanguageCFamily {
    private static class SingletonInner {
        private static Language language = new KotlinLanguage();
    }

    public static Language getInstance() {
        return SingletonInner.language;
    }
    @Override
    public String getName() {
        return "Kotlin";
    }
    @Override
    public LexTask  newLexTask() {
        return new KotlinLexTask(this);
    }
}