package taokdao.codeeditor.layout.quickedit;

import java.util.ArrayList;


public class QuickEditMenuSet {

    public static ArrayList<QuickEditMenu> list = new ArrayList<>();

    private QuickEditMenuSet() {

    }


    public static QuickEditMenu addMenu(String label, QuickEditMenu.MenuCallback menuCallback) {
        QuickEditMenu a = null;
        for (QuickEditMenu it : list) {
            if (it.label.equals(label)) {
                a = it;
            }
        }
        if (a != null) {
            list.remove(a);
        }
        QuickEditMenu action = new QuickEditMenu(label, menuCallback);
        list.add(action);
        return action;
    }
}
