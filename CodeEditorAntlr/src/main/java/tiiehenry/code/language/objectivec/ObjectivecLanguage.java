package tiiehenry.code.language.objectivec;

import tiiehenry.code.LexTask;
import tiiehenry.code.Lexer;
import tiiehenry.code.language.Language;
import tiiehenry.code.language.LanguageCFamily;

public class ObjectivecLanguage extends LanguageCFamily {
    private static class SingletonInner {
        private static Language language = new ObjectivecLanguage();
    }

    public static Language getInstance() {
        return SingletonInner.language;
    }
    @Override
    public String getName() {
        return "Objectivec";
    }
    @Override
    public LexTask  newLexTask() {
        return new ObjectivecLexTask(this);
    }
}
