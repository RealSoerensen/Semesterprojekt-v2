package dal;

import java.sql.SQLException;
import java.util.List;

public interface CRUD<C> {
    boolean create(C obj) throws SQLException;
    boolean create(C obj, long id) throws SQLException;
    C get(long id) throws SQLException;
    List<C> getAll() throws SQLException;
    boolean update(long id, C obj) throws SQLException;
    boolean delete(long id) throws SQLException;
}
