import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;

import controller.CourseController;
import controller.PersonController;
import dal.DBUtils;
import gui.LoginMenu;
import model.*;

public class Main {

	public static void main(String[] args) throws Exception {

		new DBUtils().resetDB();
		//Create members, instructors and administrators
		PersonController personController = new PersonController();

		Address address1 = new Address("9000", "Aalborg", "Her", "5A");
		Person person1 = new Person("Patrick", "Sørensen", address1, "UwULover@gmail.com", "112", 3, "0", 1111);

		Address address2 = new Address("9000", "Aalborg", "Der", "1");
		Person person2 = new Person("Jonas", "Jørgensen", address2, "Jonas.birch00@gmail.com", "51408590", 3, "1", 9999);

		Address address3 = new Address("9220", "Aalborg", "Hvorhenne", "73");
		Person person3 = new Person("Svend", "Damsgaard", address3, "svendmaster@gmail.com", "11111111", 3, "0", 3333);

		Address address4 = new Address("9200", "Aalborg", "Bilka", "252");
		Person person4 = new Person("Filip", "Nymann", address4, "filipnymann@hotmail.com", "88888888", 2,"2", 7777);

		Address address5 = new Address("9000", "Aalborg", "Langbortistan", "3523");
		Person person5 = new Person("Nicklas", "Aagaard", address5, "nicklasspillerkata@hotmail.com", "25252525", 2, "2", 5555);

		Address address6 = new Address("8900", "Randers", "Storegade", "17");
		Person person6 = new Person("Britta", "Mokaisen", address6, "mokaidrikker@gmail.com", "11411414", 1,  "0", 9878);
		Address address7 = new Address("9000", "Aalborg", "Jomfru Ane Gade", "23");
		Person person7 = new Person("Jannik", "Bøllesen", address7, "nordjyde123@live.dk", "69646567", 1,  "0", 5687);
		Address address8 = new Address("9000", "Aalborg", "Papkasse", "3");
		Person person8 = new Person("Mogens", "Hansen", address8, "hjemløsherre1525@gmail.com", "11223344", 1,  "0", 5547);
		Address address9 = new Address("9000", "Aalbrog", "Jomfru Ane Gade", "5");
		Person person9 = new Person("Bente", "Mortensen", address9, "mortensen.bente@hotmail.com", "99887766", 1,  "0", 6658);
		Address address10 = new Address("9200", "Aalborg", "Sofiendalsvej", "60");
		Person person10 = new Person("Ollie", "Osbourne", address10, "osbourne@live.dk", "55566688", 1,  "0", 6659);
		
		
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

		CourseController courseController = new CourseController();

		//Create courses
		Course course1 = new Course("Svømning", 10, "Svømning for begyndere", LocalDate.of(2023, Month.MAY, 5), LocalDate.of(2024, Month.MAY, 5));
		Course course2 = new Course("Fodbold", 20, "Fodbold for begyndere", LocalDate.of(2023, Month.MAY, 5), LocalDate.of(2024, Month.MAY, 5));
		Course course3 = new Course("Håndbold", 30, "Håndbold for begyndere", LocalDate.of(2023, Month.MAY, 5), LocalDate.of(2024, Month.MAY, 5));
		Course course4 = new Course("Tennis", 40, "Tennis for begyndere", LocalDate.of(2023, Month.MAY, 5), LocalDate.of(2024, Month.MAY, 5));
		Course course5 = new Course("Badminton", 50, "Badminton for begyndere", LocalDate.of(2023, Month.MAY, 5), LocalDate.of(2024, Month.MAY, 5));
		Course course6 = new Course("Golf", 60, "Golf for begyndere", LocalDate.of(2023, Month.MAY, 5), LocalDate.of(2024, Month.MAY, 5));

		course1 = courseController.createCourse(course1);
		course2 = courseController.createCourse(course2);
		course3 = courseController.createCourse(course3);
		course4 = courseController.createCourse(course4);
		course5 = courseController.createCourse(course5);
		course6 = courseController.createCourse(course6);

		//Create course members
		courseController.createCourseMember(course1, person1);
		courseController.createCourseMember(course2, person2);
		courseController.createCourseMember(course3, person3);
		courseController.createCourseMember(course4, person4);
		courseController.createCourseMember(course5, person5);
		courseController.createCourseMember(course6, person6);


		//Create subjects
		Subject subject1 = new Subject("Svømning", "Svømning for begyndere");
		Subject subject2 = new Subject("Fodbold", "Fodbold for begyndere");
		Subject subject3 = new Subject("Håndbold", "Håndbold for begyndere");
		Subject subject4 = new Subject("Tennis", "Tennis for begyndere");
		Subject subject5 = new Subject("Badminton", "Badminton for begyndere");
		Subject subject6 = new Subject("Golf", "Golf for begyndere");

		subject1 = courseController.createSubject(subject1);
		subject2 = courseController.createSubject(subject2);
		subject3 = courseController.createSubject(subject3);
		subject4 = courseController.createSubject(subject4);
		subject5 = courseController.createSubject(subject5);
		subject6 = courseController.createSubject(subject6);

		// Assign person to subjectss
		courseController.createInstructorSubject(person4, subject1);
		courseController.createInstructorSubject(person4, subject2);
		courseController.createInstructorSubject(person4, subject3);

		courseController.createInstructorSubject(person5, subject4);
		courseController.createInstructorSubject(person5, subject5);
		courseController.createInstructorSubject(person5, subject6);


		//Create sessions
		// Create addresses for sessions
		Address address11 = new Address("9000", "Aalborg", "Papkasse", "3");
		Address address12 = new Address("9000", "Aalborg", "Papkasse", "3");
		Address address13 = new Address("9000", "Aalborg", "Papkasse", "3");
		Address address14 = new Address("9000", "Aalborg", "Papkasse", "3");
		Address address15 = new Address("9000", "Aalborg", "Papkasse", "3");
		Address address16 = new Address("9000", "Aalborg", "Papkasse", "3");
		Session session1 = new Session(LocalDate.of(2023, Month.MAY, 5), person5, course1, address11, subject1, LocalTime.of(14, 30), LocalTime.of(16, 30));
		Session session2 = new Session(LocalDate.of(2023, Month.MAY, 5), person5, course2, address12, subject2, LocalTime.of(14, 30), LocalTime.of(16, 30));
		Session session3 = new Session(LocalDate.of(2023, Month.MAY, 5), person5, course3, address13, subject3, LocalTime.of(14, 30), LocalTime.of(16, 30));
		Session session4 = new Session(LocalDate.of(2023, Month.MAY, 5), person4, course4, address14, subject4, LocalTime.of(14, 30), LocalTime.of(16, 30));
		Session session5 = new Session(LocalDate.of(2023, Month.MAY, 5), person4, course5, address15, subject5, LocalTime.of(14, 30), LocalTime.of(16, 30));
		Session session6 = new Session(LocalDate.of(2023, Month.MAY, 5), person4, course6, address16, subject6, LocalTime.of(14, 30), LocalTime.of(16, 30));

		session1 = courseController.createSession(session1);
		session2 = courseController.createSession(session2);
		session3 = courseController.createSession(session3);
		session4 = courseController.createSession(session4);
		session5 = courseController.createSession(session5);
		session6 = courseController.createSession(session6);

		//Create session members
		courseController.createSessionMember(session1, person1);
		courseController.createSessionMember(session1, person2);
		courseController.createSessionMember(session2, person1);
		courseController.createSessionMember(session2, person2);
		courseController.createSessionMember(session3, person1);
		courseController.createSessionMember(session3, person2);
		courseController.createSessionMember(session4, person1);
		courseController.createSessionMember(session4, person2);
		courseController.createSessionMember(session5, person1);
		courseController.createSessionMember(session6, person1);

		new LoginMenu().run();
	}
}
