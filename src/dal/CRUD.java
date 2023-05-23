package dal;

import java.sql.SQLException;
import java.util.List;

/*
    * CRUD interface
 */
public interface CRUD<C> {
    C create(C obj) throws SQLException;
    C get(long id) throws SQLException;
    List<C> getAll() throws SQLException;
    boolean update(C obj) throws SQLException;
    boolean delete(C obj) throws SQLException;
}
