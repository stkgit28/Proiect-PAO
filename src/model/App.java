package model;

public class App {
    private Restaurant[] restaurants = new Restaurant[100];
    private Driver[] drivers = new Driver[100];
    private User[] users = new User[100];

    public Restaurant[] getRestaurants() {
        return restaurants;
    }

    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public void setRestaurants(Restaurant[] restaurants) {
        this.restaurants = restaurants;
    }

    public Driver[] getDrivers() {
        return drivers;
    }

    public void setDrivers(Driver[] drivers) {
        this.drivers = drivers;
    }
}
