package controller;

import java.sql.SQLException;

import dal.person.PersonDataAccessIF;

public class PersonController {

	private PersonDataAccessIF personDB;
	
	public PersonController(PersonDataAccessIF dataAccess) {
		setPersonDB(dataAccess);
	}

	private PersonDataAccessIF getPersonDB() {
		return personDB;
	}

	public void setPersonDB(PersonDataAccessIF personDB) {
		this.personDB = personDB;
	}
	
	public int getRoleOfPerson(long ssn) throws SQLException {
		return personDB.get(ssn).getRole();
	}
}
