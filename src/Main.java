import dal.person.PersonContainer;
import gui.LoginWindow;
import gui.MainWindow;
import model.Address;
import model.Person;

public class Main {

	public static void main(String[] args) {
		
		//Create Addresses
		Address address1 = new Address("9000", "Aalborg", "Her", "5A");
		
		
		//Create members, instructors and administrators
		PersonContainer personContainer = PersonContainer.getInstance();
		Person person1 = new Person("Patrick", "Sørensen", address1, "UwULover@gmail.com", "112", 3, "uwu", "0", 111);
		personContainer.create(person1);
		new LoginWindow().run();
	}
}
