package test;

import controller.CourseController;
import controller.PersonController;
import dal.address.AddressContainer;
import dal.course.CourseContainer;
import dal.coursemember.CourseMemberContainer;
import dal.person.PersonContainer;
import dal.session.SessionContainer;
import dal.sessionmember.SessionMemberContainer;
import model.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

public class CourseTest {
    private final CourseController courseController;
    private final PersonController personController;

    public CourseTest() {
        CourseContainer courseContainer = CourseContainer.getInstance();
        CourseMemberContainer courseMemberContainer = CourseMemberContainer.getInstance();
        SessionContainer sessionContainer = SessionContainer.getInstance();
        SessionMemberContainer sessionMemberContainer = SessionMemberContainer.getInstance();
        courseController = new CourseController(courseContainer, courseMemberContainer, sessionContainer, sessionMemberContainer);
        PersonContainer personContainer = PersonContainer.getInstance();
        AddressContainer addressContainer = AddressContainer.getInstance();
        personController = new PersonController(personContainer, addressContainer);
    }

    @Test
    public void testCreateCourse() throws SQLException {
        //Arrange
        Course course = new Course(1, "Test Name", 20.00, "Test Description", "5");

        //Act
        boolean result = courseController.createCourse(course);

        //Assert
        assertTrue(result);
    }

    @Test
    public void testGetCourse() throws SQLException {
        //Arrange
        Course course = new Course(1, "Test Name", 20.00, "Test Description", "5");

        //Act
        courseController.createCourse(course);
        Course result = courseController.getCourse(1);

        //Assert
        assertEquals(course.getCourseID(), result.getCourseID());
    }

    @Test
    public void testUpdateCourse() throws SQLException {
        //Arrange
        Course course = new Course(1, "Test Name", 20.00, "Test Description", "5");
        Course updatedCourse = new Course(1, "Updated Name", 20.00, "Updated Description", "5");

        //Act
        courseController.createCourse(course);
        boolean result = courseController.updateCourse(updatedCourse);

        //Assert
        assertTrue(result);
    }

    @Test
    public void testDeleteCourse() throws SQLException {
        //Arrange
        Course course = new Course(1, "Test Name", 20.00, "Test Description", "5");

        //Act
        courseController.createCourse(course);
        boolean result = courseController.removeCourse(course);

        //Assert
        assertTrue(result);
    }

    @Test
    public void testGetAllCourses() throws SQLException {
        //Arrange
        Course course1 = new Course(1, "Test Name", 20.00, "Test Description", "5");
        Course course2 = new Course(2, "Test Name", 20.00, "Test Description", "5");
        Course course3 = new Course(3, "Test Name", 20.00, "Test Description", "5");

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
        Course course = new Course(1, "Test Name", 20.00, "Test Description", "5");
        Address address = new Address("1234", "Aalborg", "Testvej", "1");
        Person person = new Person("John", "Doe", address, "email", "phone", 1, "username", "password", 1303014586);

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
        Course course = new Course(1, "Test Name", 20.00, "Test Description", "5");
        Address address = new Address("1234", "Aalborg", "Testvej", "1");
        Person person = new Person("John", "Doe", address, "email", "phone", 1, "username", "password", 1303014586);

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
        Course course = new Course(1, "Test Name", 20.00, "Test Description", "5");
        Address address = new Address("1234", "Aalborg", "Testvej", "1");
        Person person1 = new Person("John", "Doe", address, "email", "phone", 1, "username", "password", 1303014586);
        Person person2 = new Person("John", "Doe", address, "email", "phone", 1, "username", "password", 1303014586);
        Person person3 = new Person("John", "Doe", address, "email", "phone", 1, "username", "password", 1303014586);

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
    public void tearDown() throws SQLException {
        courseController.deleteAllCourses();
        courseController.deleteAllSessions();
    }

}
