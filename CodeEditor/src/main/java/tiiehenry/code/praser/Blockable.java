package tiiehenry.code.praser;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public interface Blockable extends Drawer{
    int startIndex();

    int endIndex();

    ArrayList<Blockable> children();

    @Nullable
    Blockable parent();

    String getText();
//    Drawer getDrawer();

}
