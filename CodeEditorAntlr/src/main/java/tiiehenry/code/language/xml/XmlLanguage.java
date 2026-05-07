package tiiehenry.code.language.xml;

import tiiehenry.code.LexTask;
import tiiehenry.code.language.Language;

public class XmlLanguage extends Language {
    private static class SingletonInner {
        private static XmlLanguage language = new XmlLanguage();
    }

    public static XmlLanguage getInstance() {
        return SingletonInner.language;
    }

    private XmlLanguage() {
    }

    @Override
    public String getName() {
        return "Xml";
    }

    @Override
    public String getLineNoteStringStart() {
        return "<!--";
    }

    @Override
    public String getLineNoteStringEnd() {
        return "-->";
    }

    @Override
    public String getRegionNoteStringStart() {
        return "<!--";
    }

    @Override
    public String getRegionNoteStringMiddle() {
        return "";
    }

    @Override
    public String getRegionNoteStringEnd() {
        return "-->";
    }

    @Override
    public String getRegionDocNoteStringStart() {
        return "<!--";
    }

    @Override
    public String getRegionDocNoteStringMiddle() {
        return "";
    }

    @Override
    public String getRegionDocNoteStringEnd() {
        return "-->";
    }

    @Override
    public LexTask newLexTask() {
        return new XmlLexTask(this);
    }


}
