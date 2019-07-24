package DataBase;

import java.sql.*;

public class H2_DB {
    private Connection connection = null;
    private static final String DB_CONNECTION = "jdbc:h2:~/test";
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

    void readRow(String str) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(str);
        while (resultSet.next()) {

            int colums = resultSet.getMetaData().getColumnCount();
            StringBuilder stringBuilder
                    = new StringBuilder();
            stringBuilder.append(resultSet.getInt(1));
            for (int i = 2; i <= colums; i++) {
                stringBuilder.append(("  :  ")).append(resultSet.getString(i));
            }
            System.out.println(stringBuilder);
        }
    }

    private void startDB() throws SQLException {
        PreparedStatement statement = null;


        String strUser = "CREATE TABLE USER(" +
                "id int generated ALWAYS AS IDENTITY PRIMARY KEY," +
                " name varchar(20))";

        String strProject = "CREATE TABLE PROJECT(" +
                "id int generated ALWAYS AS IDENTITY PRIMARY KEY," +
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

//                            PROJECT
        String query = "INSERT INTO Project (name, description) VALUES ('Project1','Project1 Description')";
        insert(query);
        query = "INSERT INTO Project(name, description) VALUES('Project2','Project2 Description')";
        insert(query);
        query = "INSERT INTO Project (name, description) VALUES('Project3','Project3 Description')";
        insert(query);

//                            USER
        query = "INSERT INTO User(name) VALUES('User1')";
        insert(query);
        query = "INSERT INTO User(name) VALUES('User2')";
        insert(query);
        query = "INSERT INTO User(name) VALUES('User3')";
        insert(query);

//                            ISSUE                                       projectID / user id
        query = "INSERT INTO Issue (projectID, userID, description) VALUES('1', '1', 'Issue Description')";
        insert(query);
        query = "INSERT INTO Issue (projectID, userID, description) VALUES('1', '1', 'Issue Description')";
        insert(query);
        query = "INSERT INTO Issue (projectID, userID, description) VALUES('1', '2', 'Issue Description')";
        insert(query);
        query = "INSERT INTO Issue (projectID, userID, description) VALUES('2', '2', 'Issue Description')";
        insert(query);
        query = "INSERT INTO Issue (projectID, userID, description) VALUES('3', '2', 'Issue Description')";
        insert(query);
        query = "INSERT INTO Issue (projectID, userID, description) VALUES('3', '3', 'Issue Description')";
        insert(query);
        query = "INSERT INTO Issue (projectID, userID, description) VALUES('3', '1', 'Issue Description')";
        insert(query);
    }
}

// query for select all from issue
/*
    SELECT ISSUE.ID, USER.NAME, PROJECT.NAME, ISSUE.DESCRIPTION FROM ISSUE
        JOIN USER ON USERID =USER.ID
        JOIN PROJECT ON PROJECTID = PROJECT.ID ;
*/
