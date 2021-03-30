package model;

public class Admin extends Person {
    private int numberOfComands;

    public Admin(long id, String name, String surname, int age, String email, int numberOfComands) {
        super(id, name, surname, age, email);
        this.numberOfComands = numberOfComands;
    }
}
