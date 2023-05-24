package dal;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;

/*
    * This class is used to create and drop tables in the database.
    * It is used for testing purposes.
*/
public class DBUtils {
        private final Connection connection;
        DBConnection dbConnection;

        /**
         * The DBUtils function is a constructor that creates a connection to the
         * database.
         *
         */
        public DBUtils() throws SQLException {
                dbConnection = DBConnection.getInstance();
                connection = dbConnection.getConnection();
        }

        /**
         * The dropTables function drops all tables in the database.
         */
        public void dropTables() throws SQLException {
                Statement stmt = connection.createStatement();
                dbConnection.startTransaction();
                stmt.executeUpdate("DROP TABLE IF EXISTS SessionMember");
                stmt.executeUpdate("DROP TABLE IF EXISTS InstructorSubject");
                stmt.executeUpdate("DROP TABLE IF EXISTS CourseMember");
                stmt.executeUpdate("DROP TABLE IF EXISTS Session");
                stmt.executeUpdate("DROP TABLE IF EXISTS Course");
                stmt.executeUpdate("DROP TABLE IF EXISTS Subject");
                stmt.executeUpdate("DROP TABLE IF EXISTS Person");
                stmt.executeUpdate("DROP TABLE IF EXISTS Address");
                dbConnection.commitTransaction();
                System.out.println("Tables dropped!");
        }


        /**
         * The createTables function creates the tables in the database.
         */
        public void createTables() throws SQLException {
                try {
                        dbConnection.startTransaction();
                        // Create tables
                        Statement stmt = connection.createStatement();
                        String sql = "CREATE TABLE Address ("
                                + "zipCode VARCHAR(10),"
                                + "houseNumber VARCHAR(10),"
                                + "addressID BIGINT IDENTITY(1,1),"
                                + "city VARCHAR(50),"
                                + "street VARCHAR(50),"
                                + "PRIMARY KEY (addressID)"
                                + ")";
                        stmt.executeUpdate(sql);

                        sql = "CREATE TABLE Subject ("
                                + "subjectID BIGINT IDENTITY(1,1),"
                                + "name VARCHAR(50),"
                                + "description VARCHAR(200),"
                                + "PRIMARY KEY (subjectID)"
                                + ")";
                        stmt.executeUpdate(sql);

                        sql = "CREATE TABLE Person ("
                                + "firstName VARCHAR(50),"
                                + "lastName VARCHAR(50),"
                                + "addressID BIGINT,"
                                + "email VARCHAR(50),"
                                + "ssn BIGINT,"
                                + "role INT,"
                                + "phoneNo VARCHAR(16),"
                                + "password VARCHAR(30),"
                                + "PRIMARY KEY (ssn),"
                                + "FOREIGN KEY (addressID) REFERENCES Address (addressID)"
                                + ")";
                        stmt.executeUpdate(sql);

                        sql = "CREATE TABLE InstructorSubject ("
                                + "ssn BIGINT,"
                                + "subjectID BIGINT,"
                                + "PRIMARY KEY (ssn, subjectID),"
                                + "FOREIGN KEY (ssn) REFERENCES Person (ssn),"
                                + "FOREIGN KEY (subjectID) REFERENCES Subject (subjectID)"
                                + ")";
                        stmt.executeUpdate(sql);

                        sql = "CREATE TABLE Course ("
                                + "courseID BIGINT IDENTITY(1,1),"
                                + "price DECIMAL(19, 4),"
                                + "name VARCHAR(50),"
                                + "description VARCHAR(200),"
                                + "startDate DATE,"
                                + "endDate DATE,"
                                + "PRIMARY KEY (courseID)"
                                + ")";
                        stmt.executeUpdate(sql);

                        sql = "CREATE TABLE CourseMember ("
                                + "ssn BIGINT,"
                                + "courseID BIGINT,"
                                + "PRIMARY KEY (ssn, courseID),"
                                + "FOREIGN KEY (ssn) REFERENCES Person (ssn),"
                                + "FOREIGN KEY (courseID) REFERENCES Course (courseID)"
                                + ")";
                        stmt.executeUpdate(sql);

                        sql = "CREATE TABLE Session ("
                                + "startDate DATE,"
                                + "startTime TIME,"
                                + "endTime TIME,"
                                + "sessionID BIGINT IDENTITY(1,1),"
                                + "courseID BIGINT,"
                                + "instructorSsn BIGINT,"
                                + "subjectID BIGINT,"
                                + "addressID BIGINT,"
                                + "PRIMARY KEY (sessionID),"
                                + "FOREIGN KEY (courseID) REFERENCES Course (courseID),"
                                + "FOREIGN KEY (instructorSsn) REFERENCES Person (ssn),"
                                + "FOREIGN KEY (subjectID) REFERENCES Subject (subjectID),"
                                + "FOREIGN KEY (addressID) REFERENCES Address (addressID)"
                                + ")";
                        stmt.executeUpdate(sql);

                        sql = "CREATE TABLE SessionMember ("
                                + "ssn BIGINT,"
                                + "sessionID BIGINT,"
                                + "PRIMARY KEY (ssn, sessionID),"
                                + "FOREIGN KEY (ssn) REFERENCES Person (ssn),"
                                + "FOREIGN KEY (sessionID) REFERENCES Session (sessionID)"
                                + ")";
                        stmt.executeUpdate(sql);
                        dbConnection.commitTransaction();

                        System.out.println("Tables created successfully!");
                } catch (SQLException e) {
                        System.out.println("Failed to create tables: " + e.getMessage());
                        dbConnection.rollbackTransaction();
                }
        }

        /**
         * The resetDB function drops all tables in the database and then creates them
         * again.
         */
        public void resetDB() throws SQLException {
                dropTables();
                createTables();
        }
}
