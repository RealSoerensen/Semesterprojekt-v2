package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dal.DBConnection;
import dal.address.AddressDB;
import dal.address.AddressDataAccessIF;
import dal.person.PersonDB;
import dal.person.PersonDataAccessIF;
import model.Address;
import model.Person;

public class PersonController {
	private PersonDataAccessIF personDB;
	private AddressDataAccessIF addressDB;

	public PersonController() throws SQLException {
		setPersonDB(new PersonDB());
		setAddressDB(new AddressDB());
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

	public Person createPerson(Person person) throws Exception {
		Address address = person.getAddress();
		long addressID = addressDB.createAddressAndGetID(address);
		address.setAddressID(addressID);
		person.setAddress(address);
		return personDB.create(person);
	}
	
	public boolean createPersonWithAddress(Person person, Address address) throws SQLException {
		boolean success = false;
		try {
			DBConnection.getInstance().startTransaction();
			addressDB.create(address);
			personDB.create(person);
			DBConnection.getInstance().commitTransaction();
			success = true;
		}
		catch(SQLException e) {
			DBConnection.getInstance().rollbackTransaction();
		}
		return success;
	}

	public Person getPerson(long personID) throws SQLException {
		return personDB.get(personID);
	}

	public Address createAddress(Address address) throws SQLException {
		return addressDB.create(address);
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

	public List<Person> getAllMembers() throws SQLException {
		return personDB.getAllMembers();
	}

    public List<Person> getAllInstructors() throws SQLException {
		return personDB.getAllInstructors();
    }

	public List<Person> getAllAdmins() throws SQLException{
		return personDB.getAllAdmins();
	}

	public Person login(long ssn, String password) {
		return personDB.login(ssn, password);
	}
}
