package dal;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.SQLException;

public class DBUtils {
    private final Connection connection;

    public DBUtils() throws SQLException {
        DBConnection dbConnection = DBConnection.getInstance();
        connection = dbConnection.getConnection();

    }

    public void dropTables() throws SQLException {
        // Drop tables
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DROP TABLE IF EXISTS CourseMembers");
        stmt.executeUpdate("DROP TABLE IF EXISTS CourseSessionMembers");
        stmt.executeUpdate("DROP TABLE IF EXISTS CourseSession");
        stmt.executeUpdate("DROP TABLE IF EXISTS MemberCourses");
        stmt.executeUpdate("DROP TABLE IF EXISTS Member");
        stmt.executeUpdate("DROP TABLE IF EXISTS Course");
        stmt.executeUpdate("DROP TABLE IF EXISTS Administrator");
        stmt.executeUpdate("DROP TABLE IF EXISTS InstructorSubjects");
        stmt.executeUpdate("DROP TABLE IF EXISTS Instructor");
        stmt.executeUpdate("DROP TABLE IF EXISTS Person");
        stmt.executeUpdate("DROP TABLE IF EXISTS Subject");
        stmt.executeUpdate("DROP TABLE IF EXISTS Address");
    }

    public void createTables() throws SQLException {
        // Create tables
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("""
                CREATE TABLE Address (\t[zipCode] varchar(50) NOT NULL,
                \t[houseNumber] varchar(10) NOT NULL,
                \t[addressID] bigint IDENTITY(1,1) PRIMARY KEY,
                \t[city] varchar(50) NOT NULL,
                \t[street] varchar(50) NOT NULL)"""
        );

        stmt.executeUpdate("""
                CREATE TABLE Subject (\t[subjectID] bigint IDENTITY(1,1) PRIMARY KEY,
                \t[name] varchar(50) NOT NULL,
                \t[description] varchar(200) NOT NULL)"""
        );

        stmt.executeUpdate("""
                CREATE TABLE Person (\t[firstName] varchar(50) NOT NULL,
                \t[lastName] varchar(50) NOT NULL,
                \t[addressID] bigint NOT NULL,
                \t[email] varchar(50) NOT NULL,
                \t[ssn] bigint PRIMARY KEY,
                \t[role] int NOT NULL,
                \t[phoneNo] varchar(50) NOT NULL,
                \t[username] varchar(18) NOT NULL,
                \t[password] varchar(30) NOT NULL,

                \tCONSTRAINT FK_Person_Address FOREIGN KEY ([addressID]) REFERENCES [Address] ([addressID]))"""
        );

        stmt.executeUpdate("""
                CREATE TABLE Instructor (\t[ssn] bigint PRIMARY KEY,
                \t[subjectID] bigint NOT NULL,
                \tCONSTRAINT FK_Instructor_Person FOREIGN KEY (ssn) REFERENCES Person(ssn),
                    CONSTRAINT FK_Instructor_Subject FOREIGN KEY (subjectID) REFERENCES Subject(subjectID))"""
        );

        stmt.executeUpdate("""
                CREATE TABLE InstructorSubjects (\t[ssn] bigint PRIMARY KEY,
                \t[subjectID] bigint NOT NULL,
                \tCONSTRAINT FK_InstructorSubjects_Person FOREIGN KEY (ssn) REFERENCES Person(ssn),
                    CONSTRAINT FK_InstructorSubjects_Subject FOREIGN KEY (subjectID) REFERENCES Subject(subjectID))"""
        );

        stmt.executeUpdate("""
            CREATE TABLE [Administrator] (
            \t[ssn] bigint PRIMARY KEY,
            \tCONSTRAINT FK_Administrator_Person FOREIGN KEY (ssn) REFERENCES Person(ssn))"""
        );

        stmt.executeUpdate("""
                CREATE TABLE Course (\t[courseID] bigint IDENTITY(1,1) PRIMARY KEY,
                \t[price] money NOT NULL,
                \t[name] varchar(50) NOT NULL,
                \t[description] varchar(200) NOT NULL,
                \t[period] varchar(50) NOT NULL)"""
        );

        stmt.executeUpdate("CREATE TABLE Member (" +
                "\t[ssn] bigint PRIMARY KEY,\n" +
                "\tCONSTRAINT FK_Member_Person FOREIGN KEY (ssn) REFERENCES Member(ssn))"
        );

        stmt.executeUpdate("""
                CREATE TABLE MemberCourses (\t[ssn] bigint PRIMARY KEY,
                \t[courseID] bigint NOT NULL,
                \tCONSTRAINT FK_MemberCourses_Person FOREIGN KEY (ssn) REFERENCES Member(ssn),
                \tCONSTRAINT FK_MemberCourses_Course FOREIGN KEY (courseID) REFERENCES Course(courseID))"""
        );

        stmt.executeUpdate("""
                CREATE TABLE CourseSession (\t[date] varchar(50) NOT NULL,
                \t[courseSessionID] bigint IDENTITY(1,1) PRIMARY KEY,
                \t[courseID] bigint NOT NULL,
                \t[instructorSsn] bigint NOT NULL,
                \t[subjectID] bigint NOT NULL,
                \t[addressID] bigint NOT NULL,
                \tCONSTRAINT FK_CourseSession_Course FOREIGN KEY (courseID) REFERENCES Course(courseID),
                \tCONSTRAINT FK_CourseSession_Instructor FOREIGN KEY (instructorSsn) REFERENCES Instructor(ssn),
                \tCONSTRAINT FK_CourseSession_Subject FOREIGN KEY (subjectID) REFERENCES Subject(subjectID),
                \tCONSTRAINT FK_CourseSession_Address FOREIGN KEY (addressID) REFERENCES Address(addressID))"""
        );

        stmt.executeUpdate("""
                CREATE TABLE CourseSessionMembers (\t[ssn] bigint NOT NULL,
                \t[courseSessionID] bigint PRIMARY KEY,
                \tCONSTRAINT FK_CourseSessionMembers_Member FOREIGN KEY (ssn) REFERENCES Member(ssn),
                \tCONSTRAINT FK_CourseSessionMembers_CourseSession FOREIGN KEY (courseSessionID) REFERENCES CourseSession(courseSessionID))"""
        );
    }

    public void resetDB() throws SQLException {
        dropTables();
        createTables();
        connection.commit();
    }
}
