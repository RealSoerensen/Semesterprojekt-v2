package dal.coursessessionmember;

import dal.CRUD;
import model.CourseSessionMember;

public interface CourseSessionMemberDataAccessIF extends CRUD<CourseSessionMember> {
    CourseSessionMember getCourseSessionMember(long ssn, long courseSessionID);
}
