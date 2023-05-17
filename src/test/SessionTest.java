package test;

import controller.CourseController;
import model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SessionTest {
    private final CourseController courseController;
    private Course course;
    private Person person;
    private Address address;
    private Subject subject;

    public SessionTest() {
        courseController = new CourseController();
    }

    @BeforeEach
    public void init() {
        course = new Course(1, "Test course", 20, "Test description", LocalDate.of(2023, Month.MAY, 5), LocalDate.of(2024, Month.MAY, 5));
        person = new Person("John", "Doe", null, "email", "phone", 1, "password", 1303014586);
        address = new Address("1234", "Aalborg", "Testvej", "1");
        subject = new Subject(1, "Test Subject", "Test Description");
    }

    @Test
    public void testCreateSession() throws SQLException {
        //Arrange
        Session session = new Session(1, LocalDate.of(2023, Month.MAY, 5), person, course, address, subject, LocalTime.of(14, 30));

        //Act
        courseController.createCourse(course);
        boolean result = courseController.createSession(session);

        //Assert
        assertTrue(result);
    }

    @Test
    public void testGetSession() throws SQLException {
        //Arrange
        Session session = new Session(1, LocalDate.of(2023, Month.MAY, 5), person, course, address, subject, LocalTime.of(14, 30));

        //Act
        courseController.createCourse(course);
        courseController.createSession(session);
        Session result = courseController.getSession(course, 1);

        //Assert
        assertEquals(session.getSessionID(), result.getSessionID());
    }

    @Test
    public void testUpdateSession() throws SQLException {
        //Arrange
        Session session = new Session(1, LocalDate.of(2023, Month.MAY, 5), person, course, address, subject, LocalTime.of(14, 30));
        Session updatedSession = new Session(1, LocalDate.of(2023, Month.MAY, 8), person, course, address, subject, LocalTime.of(14, 30));

        //Act
        courseController.createCourse(course);
        courseController.createSession(session);
        boolean result = courseController.updateSession(updatedSession);

        //Assert
        assertTrue(result);
    }

    @Test
    public void testDeleteSession() throws SQLException {
        //Arrange
        Session session = new Session(1, LocalDate.of(2023, Month.MAY, 5), person, course, address, subject, LocalTime.of(14, 30));

        //Act
        courseController.createCourse(course);
        courseController.createSession(session);
        boolean result = courseController.removeSession(session);

        //Assert
        assertTrue(result);
    }

    @Test
    public void testGetAllSessions() throws SQLException {
        //Arrange
        Session session1 = new Session(1, LocalDate.of(2023, Month.MAY, 5), person, course, address, subject, LocalTime.of(14, 30));
        Session session2 = new Session(2, LocalDate.of(2023, Month.MAY, 6), person, course, address, subject, LocalTime.of(14, 30));
        Session session3 = new Session(3, LocalDate.of(2023, Month.MAY, 7), person, course, address, subject, LocalTime.of(14, 30));

        //Act
        courseController.createCourse(course);
        courseController.createSession(session1);
        courseController.createSession(session2);
        courseController.createSession(session3);
        int result = courseController.getAllSessions().size();

        //Assert
        assertEquals(3, result);
    }

    @Test
    public void testGetAllSessionFromCourse() throws SQLException {
        // Arrange
        Session session1 = new Session(1, LocalDate.of(2023, Month.MAY, 5), person, course, address, subject, LocalTime.of(14, 30));
        Session session2 = new Session(2, LocalDate.of(2023, Month.MAY, 6), person, course, address, subject, LocalTime.of(14, 30));
        Session session3 = new Session(3, LocalDate.of(2023, Month.MAY, 7), person, course, address, subject, LocalTime.of(14, 30));

        // Act
        courseController.createCourse(course);
        courseController.createSession(session1);
        courseController.createSession(session2);
        courseController.createSession(session3);
        int result = courseController.getAllSessionsFromCourse(course).size();

        // Assert
        assertEquals(3, result);
    }

    @AfterEach
    public void tearDown() {
        courseController.deleteAllCourses();
        courseController.deleteAllSessions();
    }
}
