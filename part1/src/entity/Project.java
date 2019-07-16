package entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Project extends TableBase implements Serializable {

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

    @Override
    public Set<Project> getList() {
        return projectList;
    }

    @Override
    public void setList(Set entities) {
        projectList = entities;
        idCount = projectList.size() + 1;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return id + "  :  " + name + "  :  " + description;
    }


    public String getName() {
        return name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(name, project.name) &&
                Objects.equals(description, project.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }


}
