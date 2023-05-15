package controller;

import java.sql.SQLException;
import java.util.List;


import dal.address.AddressDataAccessIF;
import dal.person.PersonDataAccessIF;
import model.Address;
import dal.person.PersonContainer;
import model.Person;

public class PersonController {

	private PersonDataAccessIF personDB;
	private AddressDataAccessIF addressDB;
	
	public PersonController(PersonDataAccessIF personDataAccess, AddressDataAccessIF addressDataAccess) {
		setPersonDB(personDataAccess);
		setAddressDB(addressDataAccess);
	}

	private PersonDataAccessIF getPersonDB() {
		return personDB;
	}

	private void setPersonDB(PersonDataAccessIF personDB) {
		this.personDB = personDB;
	}

	private AddressDataAccessIF getAddressDB() {
		return addressDB;
	}

	private void setAddressDB(AddressDataAccessIF addressDB) {
		this.addressDB = addressDB;
	}

	public boolean createPerson(Person person) throws SQLException {
		return getPersonDB().create(person);
	}

	public Person getPerson(long personID) throws SQLException {
		return getPersonDB().get(personID);
	}

	public List<Person> getAllPersons() throws SQLException {
		return getPersonDB().getAll();
	}

	public boolean updatePerson(Person person) throws SQLException {
		return getPersonDB().update(person);
	}

	public boolean deletePerson(Person person) throws SQLException {
		return getPersonDB().delete(person);
	}

	public boolean createAddress(Address address) throws SQLException {
		return getAddressDB().create(address);
	}

	public Address getAddress(long addressID) throws SQLException {
		return getAddressDB().get(addressID);
	}

	public List<Address> getAllAddresses() throws SQLException {
		return getAddressDB().getAll();
	}

	public boolean updateAddress(Address address) throws SQLException {
		return getAddressDB().update(address);
	}

	public boolean deleteAddress(Address address) throws SQLException {
		return getAddressDB().delete(address);

	}
}
