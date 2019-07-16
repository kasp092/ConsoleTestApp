package fileDataBase;

import entity.*;
import java.io.*;

public class FileDB implements Serializable {


    public static void startData() {


        for (int i = 0; i < 5; i++) {
            new Issue(new Project("project", "description"), new User("user"), "issueDescription");
            save();
        }
    }

    public static void save() {

    }

    public void save(FileDB fileDB) {
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("save.file"))) {
            os.writeObject(fileDB);
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static FileDB load() {
        FileDB restored = null;
        try (ObjectInputStream is = new ObjectInputStream((new FileInputStream("save.file")))) {
            restored = (FileDB) is.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Class not Founded!");
        }
        return restored != null ? restored : new FileDB();
    }

}


