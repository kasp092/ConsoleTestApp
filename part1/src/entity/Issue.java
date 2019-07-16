package entity;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

@XmlRootElement
public class Issue extends TableBase implements Serializable {

    private static int idCount = 1;
    private static Set<Issue> issueList;

    public Issue() {
    }

    private Project project;
    private User user;
    private String issueDescription;

    public Issue(Project project, User user, String description) {
        super(idCount);
        idCount++;

        this.project = project;
        this.user = user;
        this.issueDescription = description + id;

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
    public String toString() {
        return id + "  :  " + project.getName() + "  :  " + user.getName() + "  :  " + issueDescription;
    }


    public Project getProject() {
        return project;
    }

    public User getUser() {
        return user;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return issueDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Issue issue = (Issue) o;
        return Objects.equals(project, issue.project) &&
                Objects.equals(user, issue.user) &&
                Objects.equals(issueDescription, issue.issueDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(project, user, issueDescription);
    }
}
