package dal;

import java.sql.Connection;
import java.sql.SQLException;

public class DBUtils {
    private final Connection connection;

    public DBUtils() throws SQLException {
        DBConnection dbConnection = DBConnection.getInstance();
        connection = dbConnection.getConnection();

    }

    public void dropTables(){

    }

    public void createTables(){
        // Create tables

    }

    public void resetDatabase() throws SQLException {
        dropTables();
        createTables();
        connection.commit();
    }
}
