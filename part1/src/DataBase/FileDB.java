package DataBase;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

import static java.lang.Class.forName;

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

//    public static void save() {
//        Set<String> entities = new DBacces().getExtended();
//
//        ObjectMapper mapper = new ObjectMapper();
//
//        for (String entity : entities) {
//            try {
//                TableBase entityInstance = Class.forName(entity).asSubclass(entities.TableBase);
//                mapper.writeValue(new File(entity + ".json"),
//                        Class.forName(TableBase.class.getPackageName() + "." + entity).newInstance()
//                                .getClass().getMethod("getList", null)
//                                .invoke(Class.forName(null, null)));
//            } catch (IOException | IllegalAccessException | InstantiationException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException e) {
//                e.printStackTrace();
//            }
//        }
//
//    }

    private static void load() {
        File users = new File("users.json");
        File projects = new File("projects.json");
        File issues = new File("issues.json");


        try {
            load(users, User.class);
            load(projects, Project.class);
            load(issues, Issue.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void load(File file, Class<? extends TableBase> clazz) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        try {
            clazz.newInstance().setList(mapper.readValue(file,
                    mapper.getTypeFactory().constructCollectionType(TreeSet.class, forName(clazz.getName()))));
        } catch (FileNotFoundException e) {
            System.out.println(file + " not founded");
        } catch (JsonParseException e) {
            System.out.println("Invalid data format.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}