package tiiehenry.code.language.json;

import tiiehenry.code.LexTask;
import tiiehenry.code.Lexer;
import tiiehenry.code.language.Language;
import tiiehenry.code.language.LanguageCFamily;

public class JsonLanguage extends LanguageCFamily {
    private static class SingletonInner {
        private static Language language = new JsonLanguage();
    }

    public static Language getInstance() {
        return SingletonInner.language;
    }
    @Override
    public String getName() {
        return "Json";
    }

    @Override
    public LexTask  newLexTask() {
        return new JsonLexTask(this);
    }
}
