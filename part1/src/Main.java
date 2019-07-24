import entities.*;
import DataBase.DBacces;
import DataBase.FileDB;

import java.util.Scanner;
import java.util.Set;

public class Main {
    private static Scanner scanner;
    private static DBacces fileDB;

    public static void main(String[] args) throws Exception {

        init();

        menu();

        stop();
    }


    private static void menu() {
        boolean working = true;
        do {
            System.out.println("Select operation:");
            System.out.println("Show entities (1) | Show reports: (2) | Exit (0)");
            String line = scanner.nextLine();
            switch (line) {
                case "1":
                    read();
                    break;
                case "2":
                    getIssues();
                    break;
                case "0":
                    working = false;
                    break;
                default:
                    System.out.println("Wrong input, try again.");
                    break;
            }
        } while (working);

    }

    // получить список ошибок по имени проекта и имени пользователя
    private static void getIssues() {

        System.out.print("\nEnter User name: ");
        String userName = scanner.nextLine();
        System.out.print("Enter Project name: ");
        String projectName = scanner.nextLine();
        Set<Issue> issueList = new DBacces().getList(new Issue());

        issueList.removeIf(issue -> !issue.getProject().getName().equals(projectName));
        issueList.removeIf(issue -> !issue.getUser().getName().equals(userName));

        if (issueList.isEmpty()) {
            System.out.println("\nInformation not found.");
        } else {
            System.out.println("Id  -  Description:");
            for (Issue issue : issueList) {
                System.out.println(issue.getId() + "   :  " + issue.getDescription());
            }
            System.out.println("\n");
        }
    }

    private static void read() {
//        получить список возможных таблиц
        fileDB.getExtended();
        System.out.print("\nEnter entity name from the list: ");

//      получить класс по имени
        Class clazz = fileDB.getClazz();

//        получить список сущностей класса
        Set<TableBase> list = null;
        try {
            list = fileDB.getList((TableBase) clazz.newInstance());
            for (TableBase enity : list) {
                System.out.println(enity);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        System.out.println();
    }

    private static void init() {
        FileDB.initData();
        scanner = new Scanner(System.in);
        fileDB = new DBacces();
    }

    private static void stop() {
        scanner.close();
        FileDB.save();
    }
}