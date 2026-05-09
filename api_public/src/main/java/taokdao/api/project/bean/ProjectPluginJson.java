package taokdao.api.project.bean;

import androidx.annotation.NonNull;

import java.util.List;

public class ProjectPluginJson {
    public String id;
    public List<?> parameters;

    public ProjectPluginJson() {
    }

    @NonNull
    @Override
    public String toString() {
        return "ProjectPluginJson{id=" + id + ",parameters=" + parameters + "}";
    }
}
