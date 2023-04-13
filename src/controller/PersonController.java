package controller;

import java.sql.SQLException;

import dal.*;

public class PersonController {

	private PersonDB personDB;
	
	public PersonController() throws SQLException {
		personDB = new PersonDB();
	}
	
	public int getRoleOfPerson(long ssn) {
		return personDB.get(ssn).getRole();
	}
}
