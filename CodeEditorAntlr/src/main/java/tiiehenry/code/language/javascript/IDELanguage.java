package tiiehenry.code.language.javascript;

import java.util.Collections;

import tiiehenry.code.language.Language;
import tiiehenry.code.language.ecmascript.ECMAScriptLanguage;

public class IDELanguage extends ECMAScriptLanguage {
    private static class SingletonInner {
        private static Language language = new IDELanguage();
    }

    public static Language getInstance() {
        return SingletonInner.language;
    }


    private final static String[] NAMES = new String[]{
    };

    {
        String[] sym = new String[]{
                "//",
                "+",
                "=",
                "{", "}",
                "\"",
                "(", ")",
                "_",
                "%",
                "<", ">",
                "[", "]",
                "'",
                "~",
                "^",
                "$"
        };
        symbolList.clear();
        Collections.addAll(symbolList, sym);
    }

    public IDELanguage() {
        addInternalFunc(new String[]{
                "require", "load",
                "logi", "logd", "logw", "loge", "log",
                "logri", "logrd", "logrw", "logre", "logr",
                "printi", "printd", "printw", "printe", "printf", "print",
                "printri", "printrd", "printrw", "printre", "printrf", "printr"
        });
        setInternalFuncs(NAMES);
        addBasePackage("Packages", new String[]{
                "android",
                "com",
                "java",
                "tiiehenry",
                "org",
        });
    }

}
