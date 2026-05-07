package tiiehenry.code.praser;

import android.util.Pair;

import java.util.ArrayList;

public class BlockVars {
    public Variable.Type type;
    public String name;


    public BlockVars(Variable.Type type, String name) {
        this.type = type;
        this.name = name;
    }

    public BlockVars(String name) {
        this.type = Variable.Type.Var;
        this.name = name;
    }
}
