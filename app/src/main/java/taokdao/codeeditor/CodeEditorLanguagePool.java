package taokdao.codeeditor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import tiiehenry.code.language.ILanguage;

public class CodeEditorLanguagePool {

    public HashMap<String, ILanguage> map = new HashMap<>();

    /**
     * @param o ILanguage
     * @return isSuccess
     */
    public boolean add(@NonNull ILanguage o) {
        if (map.get(o.getName()) == null) {
            map.put(o.getName(), o);
            return true;
        }
        return false;
    }

    @Nullable
    public ILanguage get(@NonNull String id) {
        return map.get(id);
    }

    public void addAll(@NonNull ILanguage[] os) {
        for (ILanguage o : os) {
            add(o);
        }
    }

    public ILanguage remove(@NonNull String id) {
        return map.remove(id);
    }

    public void remove(@NonNull ILanguage o) {
        remove(o.getName());
    }

    public void removeAll(@NonNull ILanguage[] os) {
        for (ILanguage o : os) {
            remove(o);
        }
    }

    public void removeAll(@NonNull Collection<ILanguage> os) {
        for (ILanguage o : os) {
            remove(o);
        }
    }

    public boolean contains(@NonNull String id) {
        return map.get(id) != null;
    }

    public boolean contains(@NonNull ILanguage o) {
        return contains(o.getName());
    }

    public boolean containsAll(@NonNull ILanguage[] os) {
        return containsAll(Arrays.asList(os));
    }

    public boolean containsAll(@NonNull Collection<ILanguage> os) {
        return getAll().containsAll(os);
    }

    public HashSet<ILanguage> getAll() {
        return new HashSet<>(map.values());
    }

    public void clear() {
        map.clear();
    }
}
