package tiiehenry.code.language.ecmascript;

import tiiehenry.code.LexTask;
import tiiehenry.code.language.Language;
import tiiehenry.code.language.LanguageCFamily;

public class ECMAScriptLanguage extends LanguageCFamily {
    private static class SingletonInner {
        private static Language language = new ECMAScriptLanguage();
    }

    public static Language getInstance() {
        return SingletonInner.language;
    }
    @Override
    public String getName() {
        return "ECMAScript";
    }
    @Override
    public LexTask newLexTask() {
        return new ECMAScriptLexTask(this);
    }
}
