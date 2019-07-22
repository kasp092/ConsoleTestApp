package entities;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Issue extends TableBase implements Serializable {

    private static int idCount = 1;
    private static Set<Issue> issueList;

    public Issue() {
    }

    private Project project;
    private User user;
    private String description;

    public Issue(Project project, User user, String description) {
        super(idCount);
        idCount++;

        this.project = project;
        this.user = user;
        this.description = description + id;

        addIssue(this);
    }

    public static void addIssue(Issue issue) {
        if (issueList == null)
            issueList = new TreeSet<>();
        issueList.add(issue);
    }

    @Override
    public Set<Issue> getList() {
        return issueList;
    }

    @Override
    public void setList(Set entities) {
        issueList = entities;
        idCount = issueList.size() + 1;
    }

    @Override
    public String toString() {
        return super.toString() + project.getName() + "  :  " + user.getName() + "  :  " + description;
    }

    public Project getProject() {
        return project;
    }

    public User getUser() {
        return user;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Issue issue = (Issue) o;
        return Objects.equals(project, issue.project) &&
                Objects.equals(user, issue.user) &&
                Objects.equals(description, issue.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(project, user, description);
    }
}
