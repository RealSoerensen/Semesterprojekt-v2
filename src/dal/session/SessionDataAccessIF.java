package dal.session;

import dal.CRUD;
import model.Session;

public interface SessionDataAccessIF extends CRUD<Session> {
    void deleteAll();
}
