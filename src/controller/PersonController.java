package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dal.address.AddressContainer;
import dal.address.AddressDataAccessIF;
import dal.person.PersonContainer;
import dal.person.PersonDataAccessIF;
import model.Address;
import model.Person;

public class PersonController {
	private PersonDataAccessIF personDB;
	private AddressDataAccessIF addressDB;

	public PersonController() {
		personDB = PersonContainer.getInstance();
		addressDB = AddressContainer.getInstance();
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
		return personDB.create(person);
	}

	public Person getPerson(long personID) throws SQLException {
		return personDB.get(personID);
	}

	public List<Person> getAllPersons() throws SQLException {
		return personDB.getAll();
	}

	public boolean updatePerson(Person person) throws SQLException {
		return personDB.update(person);
	}

	public boolean deletePerson(Person person) throws SQLException {
		return personDB.delete(person);
	}

	public boolean createAddress(Address address) throws SQLException {
		return addressDB.create(address);
	}

	public Address getAddress(long addressID) throws SQLException {
		return addressDB.get(addressID);
	}

	public List<Address> getAllAddresses() throws SQLException {
		return addressDB.getAll();
	}

	public boolean updateAddress(Address address) throws SQLException {
		return addressDB.update(address);
	}

	public boolean deleteAddress(Address address) throws SQLException {
		return addressDB.delete(address);
	}

	public void removeAllPersons() throws SQLException {
		List<Person> allPersons = getAllPersons();
		while (!allPersons.isEmpty()) {
			deletePerson(allPersons.get(0));
			allPersons = getAllPersons();
		}
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
}
