package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    private static Connection con;
    private static DBConnection instance;

    private static final String DBNAME = "DMA-CSD-S223_10461275";
    private static final String SERVERNAME = "hildur.ucn.dk";
    private static final String USERNAME = "DMA-CSD-S223_10461275";
    private static final String PASSWORD = "Password1!";

    private DBConnection() throws SQLException {
        String urlString = String.format("jdbc:sqlserver://%s;databaseName=%s;encrypt=false", SERVERNAME,
                DBNAME);
        con = DriverManager.getConnection(urlString, USERNAME, PASSWORD);
    }

    public static DBConnection getInstance() throws SQLException {
        if(instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return con;
    }

    public boolean getOpenStatus() {
        boolean isOpen = false;
        try {
            isOpen = (!con.isClosed());
        } catch (Exception sclExc) {
            System.out.println("Error checking connection status");
        }
        return isOpen;
    }

    public static void closeConnection() {
        try {
            con.close();
            instance = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
