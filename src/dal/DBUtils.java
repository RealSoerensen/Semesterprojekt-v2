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
        stmt.executeUpdate("CREATE TABLE Address (" +
                "addressID BIGINT IDENTITY(1,1) NOT NULL, " +
                "houseNumber VARCHAR(10) NOT NULL, " +
                "street VARCHAR(50) NOT NULL, " +
                "city VARCHAR(50) NOT NULL, " +
                "zipCode VARCHAR(10) NOT NULL," +
                "CONSTRAINT PK_Address PRIMARY KEY (addressID))"
        );

        stmt.executeUpdate("CREATE TABLE Subject (" +
                "subjectID BIGINT IDENTITY(1,1) NOT NULL, " +
                "name VARCHAR(50) NOT NULL, " +
                "description VARCHAR(255) NOT NULL," +
                "CONSTRAINT PK_Subject PRIMARY KEY (subjectID))"
        );

        stmt.executeUpdate("CREATE TABLE Person (" +
                "ssn BIGINT NOT NULL, " +
                "firstName VARCHAR(50) NOT NULL, " +
                "lastName VARCHAR(50) NOT NULL, " +
                "addressID BIGINT NOT NULL, " +
                "email VARCHAR(50) NOT NULL, " +
                "phoneNo VARCHAR(50) NOT NULL, " +
                "role int NOT NULL, " +
                "username VARCHAR(50) NOT NULL, " +
                "password VARCHAR(50) NOT NULL, " +
                "CONSTRAINT PK_Person PRIMARY KEY (ssn), " +
                "CONSTRAINT FK_Person_Address FOREIGN KEY (addressID) REFERENCES Address (addressID))"
        );

        stmt.executeUpdate("CREATE TABLE Instructor (" +
                "ssn BIGINT NOT NULL, " +
                "CONSTRAINT PK_Instructor PRIMARY KEY (ssn), " +
                "CONSTRAINT FK_Instructor_Person FOREIGN KEY (ssn) REFERENCES Person (ssn))"
        );

        stmt.executeUpdate("CREATE TABLE InstructorSubjects (" +
                "ssn BIGINT NOT NULL, " +
                "subjectID BIGINT NOT NULL, " +
                "CONSTRAINT PK_InstructorSubjects PRIMARY KEY (ssn), " +
                "CONSTRAINT FK_InstructorSubjects_Instructor FOREIGN KEY (ssn) REFERENCES Instructor (ssn), " +
                "CONSTRAINT FK_InstructorSubjects_Subject FOREIGN KEY (subjectID) REFERENCES Subject (subjectID))"
        );

        stmt.executeUpdate("CREATE TABLE Administrator (" +
                "ssn BIGINT NOT NULL, " +
                "CONSTRAINT PK_Administrator PRIMARY KEY (ssn), " +
                "CONSTRAINT FK_Administrator_Person FOREIGN KEY (ssn) REFERENCES Person (ssn))"
        );

        stmt.executeUpdate("CREATE TABLE Course (" +
                "courseID BIGINT IDENTITY(1,1) NOT NULL, " +
                "name VARCHAR(50) NOT NULL, " +
                "description VARCHAR(255) NOT NULL, " +
                "subjectID BIGINT NOT NULL, " +
                "period VARCHAR(50) NOT NULL, " +
                "CONSTRAINT PK_Course PRIMARY KEY (courseID), " +
                "CONSTRAINT FK_Course_Subject FOREIGN KEY (subjectID) REFERENCES Subject (subjectID))"
        );

        stmt.executeUpdate("CREATE TABLE Member (" +
                "ssn BIGINT NOT NULL, " +
                "CONSTRAINT PK_Member PRIMARY KEY (ssn), " +
                "CONSTRAINT FK_Member_Person FOREIGN KEY (ssn) REFERENCES Person (ssn))"
        );

        stmt.executeUpdate("CREATE TABLE MemberCourses (" +
                "ssn BIGINT NOT NULL, " +
                "courseID BIGINT NOT NULL, " +
                "CONSTRAINT PK_MemberCourses PRIMARY KEY (ssn), " +
                "CONSTRAINT FK_MemberCourses_Member FOREIGN KEY (ssn) REFERENCES Member (ssn), " +
                "CONSTRAINT FK_MemberCourses_Course FOREIGN KEY (courseID) REFERENCES Course (courseID))"
        );

        stmt.executeUpdate("CREATE TABLE CourseSession (" +
                "courseSessionID BIGINT IDENTITY(1,1) NOT NULL, " +
                "courseID BIGINT NOT NULL, " +
                "instructorID BIGINT NOT NULL, " +
                "subjectID BIGINT NOT NULL, " +
                "addressID BIGINT NOT NULL, " +
                "date DATETIME2 NOT NULL, " +
                "CONSTRAINT PK_CourseSession PRIMARY KEY (courseSessionID), " +
                "CONSTRAINT FK_CourseSession_Course FOREIGN KEY (courseID) REFERENCES Course (courseID), " +
                "CONSTRAINT FK_CourseSession_Instructor FOREIGN KEY (instructorID) REFERENCES Instructor (ssn), " +
                "CONSTRAINT FK_CourseSession_Subject FOREIGN KEY (subjectID) REFERENCES Subject (subjectID), " +
                "CONSTRAINT FK_CourseSession_Address FOREIGN KEY (addressID) REFERENCES Address (addressID))"
        );

        stmt.executeUpdate("CREATE TABLE CourseSessionMembers (" +
                "courseSessionID BIGINT NOT NULL, " +
                "ssn BIGINT NOT NULL, " +
                "CONSTRAINT PK_CourseSessionMembers PRIMARY KEY (courseSessionID), " +
                "CONSTRAINT FK_CourseSessionMembers_CourseSession FOREIGN KEY (courseSessionID) REFERENCES CourseSession (courseSessionID), " +
                "CONSTRAINT FK_CourseSessionMembers_Member FOREIGN KEY (ssn) REFERENCES Member (ssn))"
        );

        stmt.executeUpdate("CREATE TABLE CourseMembers (" +
                "courseID BIGINT NOT NULL, " +
                "ssn BIGINT NOT NULL, " +
                "CONSTRAINT PK_CourseMembers PRIMARY KEY (courseID), " +
                "CONSTRAINT FK_CourseMembers_Course FOREIGN KEY (courseID) REFERENCES Course (courseID), " +
                "CONSTRAINT FK_CourseMembers_Member FOREIGN KEY (ssn) REFERENCES Member (ssn))"
        );
        
    }

    public void resetDB() throws SQLException {
        dropTables();
        createTables();
        connection.commit();
    }
}
