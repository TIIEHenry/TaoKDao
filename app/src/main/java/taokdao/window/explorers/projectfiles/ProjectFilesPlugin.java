package taokdao.window.explorers.projectfiles;

import androidx.annotation.NonNull;

import java.util.List;

import taokdao.api.internal.InnerIdentifier;
import taokdao.api.project.bean.Project;
import taokdao.api.project.plugin.IProjectPlugin;
import taokdao.main.IMainView;

public class ProjectFilesPlugin implements IProjectPlugin {
    private final IMainView main;
    private final ProjectFiles explorer;

    public ProjectFilesPlugin(IMainView main) {
        this.main = main;
        this.explorer = new ProjectFiles(main);
    }

    @NonNull
    @Override
    public String id() {
        return InnerIdentifier.ProjectPlugin.PROJECT_FILES;
    }

    @Override
    public void onProjectOpened(@NonNull Project project, List<?> parameters) {
        explorer.setCurrentProject(project);
        explorer.refresh();
        main.getExplorerWindow().add(explorer);
        main.getExplorerWindow().show(explorer);
    }

    @Override
    public void onProjectClosed(@NonNull Project project) {
        main.getExplorerWindow().remove(explorer);
        explorer.setCurrentProject(null);
        explorer.refresh();
    }
}
