package entities;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class User extends TableBase {

    private static Set<User> userList;

    private static int idCount = 1;

    public User() {
    }

    private String name;

    public User(String name) {
        super(idCount);
        idCount++;
        this.name = name + id;

        addUser(this);
    }

    public String getName() {
        return name;
    }

    public static void addUser(User user) {
        if (userList == null)
            userList = new TreeSet<>();
        userList.add(user);
    }

    @Override
    public TreeSet<User> getList() {
        return new TreeSet<User>(userList);
    }

    @Override
    public void setList(Set entities) {
        userList = new TreeSet<User>(entities);
        idCount = userList.size() + 1;
    }

    @Override
    public String toString() {
        return super.toString() + name;
    }

}
