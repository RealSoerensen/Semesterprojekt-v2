import java.sql.SQLException;

import controller.PersonController;
import dal.address.AddressContainer;
import dal.person.PersonContainer;
import gui.LoginMenu;
import gui.MainMenu;
import model.Address;
import model.Person;

public class Main {

	public static void main(String[] args) throws SQLException {
		
		//Create Addresses
		Address address1 = new Address("9000", "Aalborg", "Her", "5A");
		Address address2 = new Address("9000", "Aalborg", "Der", "1");
		Address address3 = new Address("9220", "Aalborg", "Hvorhenne", "73");
		Address address4 = new Address("9200", "Aalborg", "Bilka", "252");
		Address address5 = new Address("9000", "Aalborg", "Langbortistan", "3523");
		Address address6 = new Address("8900", "Randers", "Storegade", "17");
		Address address7 = new Address("9000", "Aalborg", "Jomfru Ane Gade", "23");
		Address address8 = new Address("9000", "Aalborg", "Papkasse", "3");
		Address address9 = new Address("9000", "Aalbrog", "Jomfru Ane Gade", "5");
		Address address10 = new Address("9200", "Aalborg", "Sofiendalsvej", "60");
		
		
		
		//Create members, instructors and administrators
		PersonController personController = new PersonController(PersonContainer.getInstance(), AddressContainer.getInstance());
		
		Person person1 = new Person("Patrick", "Sørensen", address1, "UwULover@gmail.com", "112", 3, "uwu", "0", 1111);
		Person person2 = new Person("Jonas", "Jørgensen", address2, "Jonas.birch00@gmail.com", "51408590", 3, "Birch", "1", 9999);
		Person person3 = new Person("Svend", "Damsgaard", address3, "svendmaster@gmail.com", "11111111", 3, "Svend", "0", 3333);
		
		Person person4 = new Person("Filip", "Nymann", address4, "filipnymann@hotmail.com", "88888888", 2, "Filip", "2", 7777);
		Person person5 = new Person("Nicklas", "Aagaard", address5, "nicklasspillerkata@hotmail.com", "25252525", 2, "Nicklas", "2", 5555);
		
		Person person6 = new Person("Britta", "Mokaisen", address6, "mokaidrikker@gmail.com", "11411414", 1, "Mokai", "0", 9878);
		Person person7 = new Person("Jannik", "Bøllesen", address7, "nordjyde123@live.dk", "69646567", 1, "Brød", "0", 5687);
		Person person8 = new Person("Mogens", "Hansen", address8, "hjemløsherre1525@gmail.com", "11223344", 1, "Homelessdude", "0", 5547);
		Person person9 = new Person("Bente", "Mortensen", address9, "mortensen.bente@hotmail.com", "99887766", 1, "Bente", "0", 6658);
		Person person10 = new Person("Ollie", "Osbourne", address10, "osbourne@live.dk", "55566688", 1, "Osbourne", "0", 6659);
		
		
		personController.createPerson(person1);
		personController.createPerson(person2);
		personController.createPerson(person3);
		personController.createPerson(person4);
		personController.createPerson(person5);
		personController.createPerson(person6);
		personController.createPerson(person7);
		personController.createPerson(person8);
		personController.createPerson(person9);
		personController.createPerson(person10);
		

		new LoginMenu().run();
		
	}
}
