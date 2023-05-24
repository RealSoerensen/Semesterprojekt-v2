package dal.person;

import dal.CRUD;
import model.Person;

import java.util.List;

public interface PersonDataAccessIF extends CRUD<Person> {
	boolean isSsnUnique(long ssn);
	Person login(long ssn, String password);
	List<Person> getAllMembers();
	List<Person> getAllInstructors();
	List<Person> getAllAdmins();
}
