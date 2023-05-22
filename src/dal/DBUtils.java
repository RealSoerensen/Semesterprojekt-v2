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

        /**
         * The DBUtils function is a constructor that creates a connection to the
         * database.
         *
         */
        public DBUtils() throws SQLException {
                DBConnection dbConnection = DBConnection.getInstance();
                connection = dbConnection.getConnection();
        }

        /**
         * The dropTables function drops all tables in the database.
         */
        public void dropTables() throws SQLException {
                // Drop tables
                Statement stmt = connection.createStatement();
                stmt.executeUpdate("DROP TABLE IF EXISTS SessionMember");
                stmt.executeUpdate("DROP TABLE IF EXISTS Session");
                stmt.executeUpdate("DROP TABLE IF EXISTS CourseMember");
                stmt.executeUpdate("DROP TABLE IF EXISTS Course");
                stmt.executeUpdate("DROP TABLE IF EXISTS InstructorSubject");
                stmt.executeUpdate("DROP TABLE IF EXISTS Person");
                stmt.executeUpdate("DROP TABLE IF EXISTS Subject");
                stmt.executeUpdate("DROP TABLE IF EXISTS Address");
        }

        /**
         * The createTables function creates the tables in the database.
         */
        public void createTables() throws SQLException {
                // Create tables
                Statement stmt = connection.createStatement();
                stmt.executeUpdate("""
                                CREATE TABLE Address (\t[zipCode] varchar(50) NOT NULL,
                                \t[houseNumber] varchar(10) NOT NULL,
                                \t[addressID] bigint IDENTITY(1,1) PRIMARY KEY,
                                \t[city] varchar(50) NOT NULL,
                                \t[street] varchar(50) NOT NULL)""");

                stmt.executeUpdate("""
                                CREATE TABLE Subject (\t[subjectID] bigint IDENTITY(1,1) PRIMARY KEY,
                                \t[name] varchar(50) NOT NULL,
                                \t[description] varchar(200) NOT NULL)""");

                stmt.executeUpdate(
                                """
                                                CREATE TABLE Person (\t[firstName] varchar(50) NOT NULL,
                                                \t[lastName] varchar(50) NOT NULL,
                                                \t[addressID] bigint NOT NULL,
                                                \t[email] varchar(50) NOT NULL,
                                                \t[ssn] bigint PRIMARY KEY,
                                                \t[role] int NOT NULL,
                                                \t[phoneNo] varchar(50) NOT NULL,
                                                \t[username] varchar(18) NOT NULL,
                                                \t[password] varchar(30) NOT NULL,

                                                \tCONSTRAINT FK_Person_Address FOREIGN KEY ([addressID]) REFERENCES [Address] ([addressID]))""");

                stmt.executeUpdate(
                                """
                                                CREATE TABLE Instructor (\t[ssn] bigint PRIMARY KEY,
                                                \t[subjectID] bigint NOT NULL,
                                                \tCONSTRAINT FK_Instructor_Person FOREIGN KEY (ssn) REFERENCES Person(ssn),
                                                CONSTRAINT FK_Instructor_Subject FOREIGN KEY (subjectID) REFERENCES Subject(subjectID))""");

                stmt.executeUpdate(
                                """
                                                CREATE TABLE InstructorSubject (\t[ssn] bigint PRIMARY KEY,
                                                \t[subjectID] bigint NOT NULL,
                                                \tCONSTRAINT FK_InstructorSubject_Person FOREIGN KEY (ssn) REFERENCES Person(ssn),
                                                CONSTRAINT FK_InstructorSubject_Subject FOREIGN KEY (subjectID) REFERENCES Subject(subjectID))""");

                stmt.executeUpdate("""
                                CREATE TABLE [Administrator] (
                                \t[ssn] bigint PRIMARY KEY,
                                \tCONSTRAINT FK_Administrator_Person FOREIGN KEY (ssn) REFERENCES Person(ssn))""");

                stmt.executeUpdate("""
                                CREATE TABLE Course (\t[courseID] bigint IDENTITY(1,1) PRIMARY KEY,
                                \t[price] money NOT NULL,
                                \t[name] varchar(50) NOT NULL,
                                \t[description] varchar(200) NOT NULL,
                                \t[period] varchar(50) NOT NULL)""");

                stmt.executeUpdate("CREATE TABLE Member (" +
                                "\t[ssn] bigint PRIMARY KEY,\n" +
                                "\tCONSTRAINT FK_Member_Person FOREIGN KEY (ssn) REFERENCES Member(ssn))");

                stmt.executeUpdate(
                                """
                                                CREATE TABLE CourseMember (\t[ssn] bigint PRIMARY KEY,
                                                \t[courseID] bigint NOT NULL,
                                                \tCONSTRAINT FK_CourseMember_Person FOREIGN KEY (ssn) REFERENCES Person(ssn),
                                                \tCONSTRAINT FK_CourseMember_Course FOREIGN KEY (courseID) REFERENCES Course(courseID))""");

                stmt.executeUpdate(
                                """
                                                CREATE TABLE Session (\t[date] varchar(50) NOT NULL,
                                                \t[sessionID] bigint IDENTITY(1,1) PRIMARY KEY,
                                                \t[courseID] bigint NOT NULL,
                                                \t[instructorSsn] bigint NOT NULL,
                                                \t[subjectID] bigint NOT NULL,
                                                \t[addressID] bigint NOT NULL,
                                                \tCONSTRAINT FK_Session_Course FOREIGN KEY (courseID) REFERENCES Course(courseID),
                                                \tCONSTRAINT FK_Session_Person FOREIGN KEY (instructorSsn) REFERENCES Person(ssn),
                                                \tCONSTRAINT FK_Session_Subject FOREIGN KEY (subjectID) REFERENCES Subject(subjectID),
                                                \tCONSTRAINT FK_Session_Address FOREIGN KEY (addressID) REFERENCES Address(addressID))""");

                stmt.executeUpdate(
                                """
                                                CREATE TABLE SessionMember (\t[ssn] bigint NOT NULL,
                                                \t[sessionID] bigint PRIMARY KEY,
                                                \tCONSTRAINT FK_SessionMember_Person FOREIGN KEY (ssn) REFERENCES Person(ssn),
                                                \tCONSTRAINT FK_SessionMember_session FOREIGN KEY (sessionID) REFERENCES Session(sessionID))""");
        }

        /**
         * The resetDB function drops all tables in the database and then creates them
         * again.
         */
        public void resetDB() throws SQLException {
                dropTables();
                createTables();
                connection.commit();
        }
}
