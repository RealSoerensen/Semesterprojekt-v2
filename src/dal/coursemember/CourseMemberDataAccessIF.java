package dal.coursemember;

import dal.CRUD;
import model.Course;
import model.CourseMember;

public interface CourseMemberDataAccessIF extends CRUD<CourseMember> {
    CourseMember getCourseMember(long ssn, Course course);
}
