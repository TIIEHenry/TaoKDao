package tiiehenry.code.language.smali;


import java.util.ArrayList;

import tiiehenry.code.LexTask;
import tiiehenry.code.language.Language;

public class SmaliLanguage extends Language {
    private static class SingletonInner {
        private static Language language = new SmaliLanguage();
    }

    public static Language getInstance() {
        return SingletonInner.language;
    }

    private SmaliLanguage() {
    }
    @Override
    public String getName() {
        return "Smali";
    }
    public CharSequence complete(ArrayList<String> buf, CharSequence constraint) {
        if (constraint.charAt(0) != 'L')
            return super.complete(buf, constraint);
        String word = constraint.toString();
        int i = word.indexOf(';');
        if (i == -1) {
            word = word.toLowerCase();
            if (word.startsWith("[")) {
                i = word.lastIndexOf('[');

            }
            return word;
        }
        String word1 = word.substring(i + 1);
        if (word1.startsWith("->")) {
            word1 = word1.substring(2).toLowerCase();
        }
        return word1;
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
        return "#";
    }

    @Override
    public String getRegionNoteStringMiddle() {
        return "#";
    }

    @Override
    public String getRegionNoteStringEnd() {
        return "";
    }

    @Override
    public String getRegionDocNoteStringStart() {
        return "#";
    }

    @Override
    public String getRegionDocNoteStringMiddle() {
        return "#";
    }

    @Override
    public String getRegionDocNoteStringEnd() {
        return "";
    }

    @Override
    public LexTask newLexTask() {
        return new SmaliLexTask(this);
    }
}
