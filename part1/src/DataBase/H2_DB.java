package DataBase;

import entities.Issue;
import entities.Project;
import entities.User;

import java.sql.*;

public class H2_DB {
    private Connection connection = null;
    private static final String DB_CONNECTION = "jdbc:h2:tcp://localhost/~/test";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    public H2_DB() throws SQLException {
        try {
            connection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        startDB();
    }


    void insert(String query) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
    }

    void getTables(String query) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1));
        }
    }

    void readRow(String str) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(str);
        while (resultSet.next()) {

            int colums = resultSet.getMetaData().getColumnCount();
            StringBuilder stringBuilder
                    = new StringBuilder();
            stringBuilder.append(resultSet.getString(1));
            for (int i = 2; i <= colums; i++) {
                stringBuilder.append(("  :  ")).append(resultSet.getString(i));
            }
            System.out.println(stringBuilder);
        }
    }

    private void startDB() throws SQLException {
        PreparedStatement statement = null;


        String strUser = "CREATE TABLE USER(" +
                "id int NOT NULL PRIMARY KEY," +
                " name varchar(20))";

        String strProject = "CREATE TABLE PROJECT(" +
                "id int NOT NULL PRIMARY KEY," +
                " name varchar(20)," +
                " description varchar(255))";

        String strIssue = "CREATE TABLE ISSUE(" +
                "id int generated ALWAYS AS IDENTITY PRIMARY KEY," +
                " projectID int ," +
                " userID int ," +
                " description varchar(255)," +
                "FOREIGN KEY (userID) REFERENCES User(id)," +
                "FOREIGN KEY (projectId) REFERENCES Project(id))";

        statement = connection.prepareStatement("DROP TABLE IF EXISTS ISSUE, PROJECT, USER;");
        statement.executeUpdate();
        statement = connection.prepareStatement(strUser);
        statement.executeUpdate();
        statement = connection.prepareStatement(strProject);
        statement.executeUpdate();
        statement = connection.prepareStatement(strIssue);
        statement.executeUpdate();


        statement.close();

        startData();
    }

    private void startData() throws SQLException {
        String query = null;

        //                            USER
        for (User user : new User().getList()) {
            query = "INSERT INTO User VALUES (" + user.getId() + ", '" + user.getName() + "')";
            insert(query);
        }

//                            PROJECT
        for (Project project : new Project().getList()) {
            query = "INSERT INTO Project VALUES (" + project.getId() + ", '" + project.getName() + "', '" + project.getDescription() + "')";
            insert(query);
        }

        //                            ISSUE
        for (Issue issue : new Issue().getList()) {
            query = "INSERT INTO Issue VALUES(" + issue.getId() + ", "
                    + issue.getProjectId() + ", " + issue.getUserId() + ", '" //  projectID / user id
                    + issue.getDescription() + "')";
            insert(query);
        }
    }
}

// query for select all from issue
/*
    SELECT ISSUE.ID, USER.NAME, PROJECT.NAME, ISSUE.DESCRIPTION FROM ISSUE
        JOIN USER ON USERID =USER.ID
        JOIN PROJECT ON PROJECTID = PROJECT.ID ;
*/
