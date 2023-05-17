package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dal.address.AddressContainer;
import dal.address.AddressDataAccessIF;
import dal.person.PersonContainer;
import dal.person.PersonDataAccessIF;
import model.Person;

public class PersonController {
	private PersonDataAccessIF personDB;
	private AddressDataAccessIF addressDB;

	public PersonController() {
		setPersonDB(PersonContainer.getInstance());
		setAddressDB(AddressContainer.getInstance());
	}

	public boolean isSsnUnique(long ssn) {
		return personDB.isSsnUnique(ssn);
	}

	private void setPersonDB(PersonDataAccessIF personDB) {
		this.personDB = personDB;
	}

	private void setAddressDB(AddressDataAccessIF addressDB) {
		this.addressDB = addressDB;
	}

	public boolean createPerson(Person person) throws SQLException {
		addressDB.create(person.getAddress());
		return personDB.create(person);
	}

	public Person getPerson(long personID) throws SQLException {
		return personDB.get(personID);
	}

	public List<Person> getAllPersons() throws SQLException {
		return personDB.getAll();
	}

	public boolean updatePerson(Person person) throws SQLException {
		addressDB.update(person.getAddress());
		return personDB.update(person);
	}

	public boolean deletePerson(Person person) throws SQLException {
		addressDB.delete(person.getAddress());
		return personDB.delete(person);
	}

	public void removeAllPersons() throws SQLException {
		List<Person> allPersons = getAllPersons();
		while (!allPersons.isEmpty()) {
			deletePerson(allPersons.get(0));
			allPersons = getAllPersons();
		}
	}

	public List<Person> getAllMembers() throws SQLException {
		List<Person> members = new ArrayList<>();
		List<Person> allPersons = getAllPersons();
		for(Person person : allPersons) {
			if(person.getRole() == 1) {
				members.add(person);
			}
		}
		return members;
	}

    public List<Person> getAllInstructors() throws SQLException {
		List<Person> instructors = new ArrayList<>();
		List<Person> allPersons = getAllPersons();
		for(Person person : allPersons) {
			if(person.getRole() == 2) {
				instructors.add(person);
			}
		}
		return instructors;
    }

	public List<Person> getAllAdmins() throws SQLException{
		List<Person> admins = new ArrayList<>();
		List<Person> allPersons = getAllPersons();
		for(Person person : allPersons) {
			if(person.getRole() == 3) {
				admins.add(person);
			}
		}
		return admins;
	}
}
