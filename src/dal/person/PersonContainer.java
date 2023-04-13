package dal.person;

import model.Person;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonContainer implements PersonDataAccessIF {
    private final List<Person> container;
    private static PersonContainer instance;

    private PersonContainer() {
        container = new ArrayList<>();
    }

    public static PersonContainer getInstance() {
        if (instance == null) {
            instance = new PersonContainer();
        }
        return instance;
    }

    @Override
    public boolean create(Person obj) throws SQLException {
        return container.add(obj);
    }

    @Override
    public Person get(long id) throws SQLException {
        Person person = null;
        for(int i = 0; i < container.size() && person == null; i++) {
            if(container.get(i).getSsn() == id) {
                person = container.get(i);
            }
        }
        return person;
    }

    @Override
    public List<Person> getAll() {
        return container;
    }

    @Override
    public boolean update(long id, Person obj) throws SQLException {
        boolean result = false;
        for (int i = 0; i < container.size() && !result; i++) {
            if (container.get(i).getSsn() == id) {
                container.set(i, obj);
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean delete(long id) throws SQLException {
        boolean result = false;
        for (int i = 0; i < container.size() && !result; i++) {
            if (container.get(i).getSsn() == id) {
                container.remove(i);
                result = true;
            }
        }
        return result;
    }
}
