package test;

import dal.DBConnection;
import dal.DBUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class DBTest {
    private final DBUtils utils;
    private static DBConnection db;
    public DBTest() throws SQLException {
        utils = new DBUtils();
        db = DBConnection.getInstance();
    }

    @Test
    public void testDBConnection() {
        //Arrange

        //Act
        Connection con = db.getConnection();
        boolean open = db.getOpenStatus();

        //Assert
        assertNotNull(con);
        assertTrue(open);
    }

    @Test
    public void testDropTables() throws SQLException {
        //Arrange

        //Act
        utils.dropTables();

        //Assert

    }

    @AfterAll
    public static void closeConnection() {
        db.closeConnection();
    }
}
