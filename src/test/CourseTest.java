package test;

import controller.CourseController;
import controller.PersonController;
import model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.SQLException;

public class CourseTest {
    private final CourseController courseController;
    private final PersonController personController;

    public CourseTest() {
        courseController = new CourseController();
        personController = new PersonController();
    }

    @Test
    public void testCreateCourse() throws SQLException {
        //Arrange
        Course course = new Course(1, "Test Name", 20.00, "Test Description", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));

        //Act
        boolean result = courseController.createCourse(course);

        //Assert
        assertTrue(result);
    }

    @Test
    public void testGetCourse() throws SQLException {
        //Arrange
        Course course = new Course(1, "Test Name", 20.00, "Test Description", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));

        //Act
        courseController.createCourse(course);
        Course result = courseController.getCourse(1);

        //Assert
        assertEquals(course.getCourseID(), result.getCourseID());
    }

    @Test
    public void testUpdateCourse() throws SQLException {
        //Arrange
        Course course = new Course(1, "Test Name", 20.00, "Test Description", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));
        Course updatedCourse = new Course(1, "Updated Name", 20.00, "Test Description", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));

        //Act
        courseController.createCourse(course);
        boolean result = courseController.updateCourse(updatedCourse);

        //Assert
        assertTrue(result);
    }

    @Test
    public void testDeleteCourse() throws SQLException {
        //Arrange
        Course course = new Course(1, "Test Name", 20.00, "Test Description", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));

        //Act
        courseController.createCourse(course);
        boolean result = courseController.removeCourse(course);

        //Assert
        assertTrue(result);
    }

    @Test
    public void testGetAllCourses() throws SQLException {
        //Arrange
        Course course1 = new Course(1, "Test Name", 20.00, "Test Description", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));
        Course course2 = new Course(2, "Test Name", 20.00, "Test Description", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));
        Course course3 = new Course(3, "Test Name", 20.00, "Test Description", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));

        //Act
        courseController.createCourse(course1);
        courseController.createCourse(course2);
        courseController.createCourse(course3);
        int result = courseController.getAllCourses().size();

        //Assert
        assertEquals(3, result);
    }

    @Test
    public void testEnrollMember() throws SQLException {
        //Arrange
        Course course = new Course(1, "Test Name", 20.00, "Test Description", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));
        Address address = new Address("1234", "Aalborg", "Testvej", "1");
        Person person = new Person("John", "Doe", address, "email", "phone", 1,  "password", 1303014586);

        //Act
        courseController.createCourse(course);
        personController.createPerson(person);
        boolean result = courseController.createCourseMember(course, person);

        //Assert
        assertTrue(result);
    }

    @Test
    public void testUnenrollMember() throws SQLException {
        //Arrange
        Course course = new Course(1, "Test Name", 20.00, "Test Description", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));
        Address address = new Address("1234", "Aalborg", "Testvej", "1");
        Person person = new Person("John", "Doe", address, "email", "phone", 1, "password", 1303014586);

        //Act
        courseController.createCourse(course);
        courseController.createCourseMember(course, person);
        boolean result = courseController.removeCourseMember(course, person);

        //Assert
        assertTrue(result);
    }

    @Test
    public void testGetAllCourseMembers() throws SQLException {
        //Arrange
        Course course = new Course(1, "Test Name", 20.00, "Test Description", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()));
        Address address = new Address("1234", "Aalborg", "Testvej", "1");
        Person person1 = new Person("John", "Doe", address, "email", "phone", 1, "password", 1303014586);
        Person person2 = new Person("John", "Doe", address, "email", "phone", 1, "password", 1303014586);
        Person person3 = new Person("John", "Doe", address, "email", "phone", 1, "password", 1303014586);

        //Act
        courseController.createCourse(course);
        courseController.createCourseMember(course, person1);
        courseController.createCourseMember(course, person2);
        courseController.createCourseMember(course, person3);
        int result = courseController.getAllCourseMembers(course).size();

        //Assert
        assertEquals(3, result);
    }

    @AfterEach
    public void tearDown() {
        courseController.deleteAllCourses();
        courseController.deleteAllSessions();
    }

}
