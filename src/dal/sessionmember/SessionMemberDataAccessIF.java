package dal.sessionmember;

import dal.CRUD;
import model.SessionMember;

public interface SessionMemberDataAccessIF extends CRUD<SessionMember> {
    SessionMember getSessionMember(long ssn, long sessionID);
}
