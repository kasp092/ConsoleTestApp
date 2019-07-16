package entity;

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
    public Set<User> getList() {
        return userList;
    }

    @Override
    public void setList(Set entities) {
        userList = entities;
        idCount = userList.size() + 1;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return id + "  :  " + name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }


}
