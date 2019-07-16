package fileDataBase;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.TreeSet;

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

        ObjectMapper mapper = new ObjectMapper();

        try {
            new User().setList(mapper.readValue(users, new TypeReference<TreeSet<User>>() {
            }));
        } catch (FileNotFoundException e) {
            System.out.println("users.json wasn't loaded");
        } catch (JsonParseException e) {
            System.out.println("Invalid data format.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            new Project().setList(mapper.readValue(projects, new TypeReference<TreeSet<Project>>() {
            }));
        } catch (FileNotFoundException e) {
            System.out.println("projects.json wasn't loaded");
        } catch (JsonParseException e) {
            System.out.println("Invalid data format.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            new Issue().setList(mapper.readValue(issues, new TypeReference<TreeSet<Issue>>() {
            }));
        } catch (FileNotFoundException e) {
            System.out.println("issues.json wasn't loaded");
        } catch (JsonParseException e) {
            System.out.println("Invalid data format.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}