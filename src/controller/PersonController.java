package controller;

import java.sql.SQLException;

import dal.person.PersonDataAccessIF;

public class PersonController {

	private PersonDataAccessIF personDB;
	
	public PersonController(PersonDataAccessIF dataAccess) {
		personDB = dataAccess;
	}

	private PersonDataAccessIF getPersonDB() {
		return personDB;
	}

	private void setPersonDB(PersonDataAccessIF personDB) {
		this.personDB = personDB;
	}
}
