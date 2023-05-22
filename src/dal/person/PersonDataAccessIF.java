package dal.person;

import dal.CRUD;
import model.Person;

public interface PersonDataAccessIF extends CRUD<Person> {
	boolean isSsnUnique(long ssn);
	Person login(long ssn, String password);
}
