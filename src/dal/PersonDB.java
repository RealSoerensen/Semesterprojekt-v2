package dal;

import model.Person;

import java.sql.SQLException;
import java.util.List;

public class PersonDB implements CRUD<Person>{
    @Override
    public boolean create(Person obj) throws SQLException {
        return false;
    }

    @Override
    public Person get(long id) throws SQLException {
        return null;
    }

    @Override
    public List<Person> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean update(long id, Person obj) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(long id) throws SQLException {
        return false;
    }
}
