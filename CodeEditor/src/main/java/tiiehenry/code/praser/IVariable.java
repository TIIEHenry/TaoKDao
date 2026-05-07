package tiiehenry.code.praser;

public class IVariable {
    public String name;
    public String matchText;

    public IVariable(String name) {
        this.name = name;
    }

    public IVariable(String name, String matchText) {
        this.name = name;
        this.matchText = matchText;
    }
}
