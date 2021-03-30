package model;

public class Driver extends Person{
    private double salary;
    private double rating;
    private boolean free;




    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }



    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Driver(long id, String name, String surname, int age, String email, double salary, double rating, boolean free) {
        super(id, name, surname, age, email);
        this.salary = salary;
        this.rating = rating;
        this.free = free;

    }
    @Override
    public String toString() {
        return super.toString() + "/" + salary + "/" +  rating + "/" + free;
    }
}
