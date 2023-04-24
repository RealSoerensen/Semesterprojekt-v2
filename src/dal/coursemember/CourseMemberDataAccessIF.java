package dal.coursemember;

import model.Course;
import model.Person;

import java.util.List;

public interface CourseMemberDataAccessIF {
    boolean create(Course course, Person member);
    Person getMemberFromCourse(long ssn, Course course);
    List<Person> getMembersInCourse(Course course);
    boolean removeMemberFromCourse(Course course, Person member);
}
