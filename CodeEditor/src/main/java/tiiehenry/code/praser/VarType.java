package tiiehenry.code.praser;

import java.util.ArrayList;

public enum VarType {
    Keyword, Var, Func_Internal, Func_User, Package, Param;

    public ArrayList<BlockVars> variables = new ArrayList<>();
}
