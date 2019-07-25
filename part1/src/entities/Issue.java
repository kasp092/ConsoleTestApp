package entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;
import java.util.TreeSet;

public class Issue extends TableBase {

    private static int idCount = 1;
    private static Set<Issue> issueList;

    public Issue() {
    }

    // TODO: 24.07.2019 заменить пользователя и проект в Issue на userId, projectId
    private int projectId;
    private int userId;
    private String description;

    public Issue(Project project, User user, String description) {
        super(idCount);
        idCount++;

        this.projectId = project.getId();
        this.userId = user.getId();
        this.description = description + id;

        addIssue(this);
    }

    public static void addIssue(Issue issue) {
        if (issueList == null)
            issueList = new TreeSet<>();
        issueList.add(issue);
    }

    public Set<Issue> getList() {
        return new TreeSet<>(issueList);
    }

    @Override
    public void setList(Set entities) {
        issueList = entities;
        idCount = issueList.size() + 1;
    }

    @Override
    public String toString() {


        return super.toString() + getProject() + "  :  " + getUser() + "  :  " + description;
    }

    @JsonIgnore
    public String getProject() {
        for (Project project : Project.getProjectList()) {
            if (project.getId() == projectId)
                return project.getName();
        }
        return null;
    }

    @JsonIgnore
    public String getUser() {
        for (User user : User.getUserList()) {
            if (user.getId() == projectId)
                return user.getName();
        }
        return null;
    }

    public int getProjectId() {
        return projectId;
    }

    public int getUserId() {
        return userId;
    }

    public String getDescription() {
        return description;
    }

}
