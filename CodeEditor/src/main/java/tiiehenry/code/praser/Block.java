package tiiehenry.code.praser;

import android.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;

public class Block {

    public int startIndex;
    public int endIndex;

    public int charWidth = 1;//{的长度

    public Block parent = null;
    public ArrayList<Block> childList = new ArrayList<>();

    public Block() {
    }

    public Block(int startIndex, int endIndex) {
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    public boolean hasVariable(Variable.Type type, String var) {
/*        ArrayList<Variable> list = variableMap.get(type);
//        if ()
        for (Pair p : variables) {
            if (p.first == type) {
*//*                for (Pair v : p.second) {

                }*//*
            }
//            if (v.name==var)

        }*/
        return false;
    }
    public static class TypedVar{
//        public ArrayList<IVariable>
    }
}
