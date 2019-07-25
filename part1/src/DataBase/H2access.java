package DataBase;

import java.sql.SQLException;

public class H2access {

    private H2_DB db;

    public void init() {
        try {
            db = new H2_DB();
            read();
        } catch (SQLException e) {
            System.out.println("SQL database is not available");
        }
    }

    public void read() {

        String query = "SHOW TABLES";
        try {
            db.getTables(query);
            db.readRow("SELECT * FROM ISSUE");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
