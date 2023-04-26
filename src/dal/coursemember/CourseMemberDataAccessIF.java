package dal.coursemember;

import model.Course;
import model.Person;

import java.util.List;

public interface CourseMemberDataAccessIF {
    boolean create(Course course, Person member);
    boolean isPersonIn(Course course, Person person);
    List<Person> getAll(Course course);
    boolean remove(Course course, Person member);
}
