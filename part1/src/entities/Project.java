package entities;


import java.util.Set;
import java.util.TreeSet;

public class Project extends TableBase {

    private static int idCount = 1;
    private static Set<Project> projectList;

    public Project() {
    }

    private String name;
    private String description;

    public Project(String name, String description) {
        super(idCount);
        idCount++;
        this.name = name + id;
        this.description = description + id;

        addProject(this);
    }

    public static void addProject(Project project) {
        if (projectList == null)
            projectList = new TreeSet<>();
        projectList.add(project);
    }

    public TreeSet<Project> getList() {
        return new TreeSet<>(projectList);
    }

    static Set<Project> getProjectList() {
        return projectList;
    }

    @Override
    public void setList(Set entities) {
        projectList = entities;
        idCount = projectList.size() + 1;
    }

    @Override
    public String toString() {
        return super.toString() + name + "  :  " + description;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
