import dal.person.PersonContainer;
import gui.LoginMenu;
import gui.MainMenu;
import model.Address;
import model.Person;

public class Main {

	public static void main(String[] args) {
		
		//Create Addresses
		Address address1 = new Address("9000", "Aalborg", "Her", "5A");
		
		
		//Create members, instructors and administrators
		PersonContainer personContainer = PersonContainer.getInstance();
		Person person1 = new Person("Patrick", "Sørensen", address1, "UwULover@gmail.com", "112", 3, "uwu", "0", 111);
		Person person2 = new Person("Jonas", "Jørgensen", address1, "Jonas.birch00@gmail.com", "51408590", 3, "Birch", "1", 222);
		personContainer.create(person1);
		personContainer.create(person2);
		new LoginMenu().run();
		
	}
}
