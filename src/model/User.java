package model;

public class User extends Person{
    private String city;
    private String street;

    public User() {
        super();
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public User(long id, String name, String surname, int age, String email, String city, String street) {
        super(id, name, surname, age, email);
        this.city = city;
        this.street = street;
    }
    @Override //annotation
    public String toString() {
        return super.toString() + "/" + city + "/" + street;
    }
}
