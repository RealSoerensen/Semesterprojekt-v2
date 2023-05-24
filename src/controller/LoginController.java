package controller;

import model.Person;

public class LoginController {
    private static LoginController instance;
    private Person person;

    private LoginController() {
    }

    public static LoginController getInstance() {
        if (instance == null) {
            instance = new LoginController();
        }
        return instance;
    }

    public Person getPerson() {
        return person;
    }

    public void setLoggedInPerson(Person p) {
        this.person = p;
    }
}
