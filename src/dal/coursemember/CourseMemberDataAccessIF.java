package dal.coursemember;

import dal.CRUD;
import model.CourseMember;

public interface CourseMemberDataAccessIF extends CRUD<CourseMember> {
    CourseMember getCourseMember(long ssn, long courseSessionID);
}
