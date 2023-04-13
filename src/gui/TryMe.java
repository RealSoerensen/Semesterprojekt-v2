package gui;

import java.sql.SQLException;

import controller.PersonController;
import model.Address;
import model.Person;

public class TryMe {
	public void main (String[] args) {
		
		//Creating Persons in the system
		try {
			PersonController personController = new PersonController();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
		}
		Address patricksAddress = new Address("9000", "Aalborg", "Her", "3");
		Person patrick = new Person("Patrick", "Sørensen", patricksAddress, "PatrickUwU@gmail.com", "112", 3, "UwUPatrick", "0", 1301015961);
		
		
	}
}
