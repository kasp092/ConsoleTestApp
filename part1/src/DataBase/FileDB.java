package DataBase;

import com.fasterxml.jackson.databind.ObjectMapper;
import entities.*;

import java.io.File;
import java.io.IOException;

public class FileDB {

    public static void initData() {
        load();

        for (int i = 0; i < 5; i++) {
            new Issue(new Project("project", "description"), new User("user"), "issueDescription");
        }
    }

    public static void save() {
        File users = new File("users.json");
        File projects = new File("projects.json");
        File issues = new File("issues.json");
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(users, new User().getList());
            objectMapper.writeValue(projects, new Project().getList());
            objectMapper.writeValue(issues, new Issue().getList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void load() {
        File users = new File("users.json");
        File projects = new File("projects.json");
        File issues = new File("issues.json");

        DBacces fileDB = new DBacces();
        try {
            fileDB.load(users, User.class);
            fileDB.load(projects, Project.class);
            fileDB.load(issues, Issue.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}