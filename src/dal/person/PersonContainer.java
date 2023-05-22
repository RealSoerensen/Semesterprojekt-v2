package dal.person;

import model.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonContainer implements PersonDataAccessIF {
    private final List<Person> container;
    private static PersonContainer instance;

    /**
     * The PersonContainer function is a singleton class that creates an ArrayList of Person objects.
     * It is private so that it can only be accessed by the getInstance function.
     */
    private PersonContainer() {
        container = new ArrayList<>();
    }

    /**
     * The getInstance function is a static function that returns the singleton instance of PersonContainer.
     * If the instance does not exist, it creates one and then returns it.
     *
     * @return The instance of the class PersonContainer
     */
    public static PersonContainer getInstance() {
        if (instance == null) {
            instance = new PersonContainer();
        }
        return instance;
    }

    /**
     * The create function adds a new Person object to the container.
     *
     * @param obj Add the person to the container
     *
     * @return A boolean value to indicate whether the person was added to the container or not
     */
    @Override
    public boolean create(Person obj) {
        return container.add(obj);
    }

    @Override
    public Person get(long id) {
        Person person = null;
        for(int i = 0; i < container.size() && person == null; i++) {
            if(container.get(i).getSsn() == id) {
                person = container.get(i);
            }
        }
        return person;
    }

    /**
     * The getAll function returns a list of all the people in the container.
     *
     * @return A list of all the people in the container
     */
    @Override
    public List<Person> getAll() {
        return container;
    }

    @Override
    public boolean update(Person obj) {
        boolean result = false;
        for (int i = 0; i < container.size() && !result; i++) {
            if (container.get(i).getSsn() == obj.getSsn()) {
                container.set(i, obj);
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean delete(Person obj) {
        boolean result = false;
        for (int i = 0; i < container.size() && !result; i++) {
            if (container.get(i).getSsn() == obj.getSsn()) {
                container.remove(i);
                result = true;
            }
        }
        return result;
    }

	@Override
	public boolean isSsnUnique(long ssn) {
		boolean unique = true;
		for(int i = 0; i < container.size() && unique; i++) {
            if(container.get(i).getSsn() == ssn) {
            	unique = false;
            }
        }
		return unique;
	}
}
