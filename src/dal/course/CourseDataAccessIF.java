package dal.course;

import dal.CRUD;
import model.Course;

public interface CourseDataAccessIF extends CRUD<Course> {
    void deleteAll();
    long createCourseAndGetID(Course course);
}
