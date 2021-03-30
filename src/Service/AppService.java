package Service;
import model.*;

import model.Product;

import java.awt.*;

public class AppService {

    public void createAccount(App app, User user){
        int nextUser = numberOfUsers(app);
        app.getUsers()[nextUser] = user;

    }
    private int numberOfUsers(App app){
        int numberOfUsers = 0;
        for(User u : app.getUsers()){
            if(u!=null)
            {
                numberOfUsers++;
            }
        }
        return numberOfUsers;
    }

    public void printUsers(App app) {
        for(User u : app.getUsers()) {
            if(u != null) {
                System.out.println(u);
            }
        }
    }

    public void deleteuser(App app, String email){
        User[] copy = new User[100];
        int i=0;
        for(User u : app.getUsers()) {
            if (u != null && u.getEmail().equals(email)){
                copy[i]=null;
                i++;
            }
            else{
                copy[i] = u;
                i++;
            }

            }
        app.setUsers(copy);

        }

    public void fireEmployee(App app, String email){
        Driver[] cdriver = new Driver[100];
        int i=0;
        for(Driver d : app.getDrivers()) {
            if (d != null && d.getEmail().equals(email)){
                cdriver[i]=null;
                i++;
            }
            else{
                cdriver[i] = d;
                i++;
            }

        }
        app.setDrivers(cdriver);

    }

    public void hirePerson(App app, Driver driver){
        int nextEmployee = numberOfEmployees(app);
        app.getDrivers()[nextEmployee] = driver;

    }

    private int numberOfEmployees(App app){
        int numberOfEmployees = 0;
        for(Driver d : app.getDrivers()){
            if(d!=null)
            {
                numberOfEmployees++;
            }
        }
        return numberOfEmployees;
    }

    public void printEmployees(App app) {
        for(Driver d : app.getDrivers()) {
            if(d != null) {
                System.out.println(d);
            }
        }
    }
    public void addRestaurant(App app, Restaurant rest){
        int nextRest = numberOfRestaurant(app);
        app.getRestaurants()[nextRest] = rest;


    }

    public void printRestaurant(App app) {
        for(Restaurant r : app.getRestaurants()) {
            if(r != null) {
                System.out.println(r);
                for(Product p : r.getProducts()) {
                    if (p != null) {
                        System.out.print(p);
                        System.out.println();
                    }
                }
            }
        }
    }

    private int numberOfRestaurant(App app){
        int numberOfRest = 0;
        for(Restaurant r : app.getRestaurants()){
            if(r!=null)
            {
                numberOfRest++;
            }
        }
        return numberOfRest;
    }

    public void addProduct(Restaurant rest, Product product){
        int nextAvailableIndex = getNumberOfProducts(rest);
        rest.getProducts()[nextAvailableIndex] = product;
    }

    private int getNumberOfProducts(Restaurant rest){
        int numberOfProducts = 0;
        for(Product p :rest.getProducts()){
            if(p!=null)
            {
                numberOfProducts++;
            }
        }
        return numberOfProducts;
    }

    public void printProductDetails(Restaurant rest){
        for(Product p : rest.getProducts()){
            if(p!=null) {
                System.out.print(p);
            }
        }
    }

    public int checkRest(App app, String name){
        int ok=0;
        int i=0;
        for(Restaurant r : app.getRestaurants()) {
            if(r != null && r.getRestaurantName()== name) {
                ok=i;
            }
            else{
                i++;
            }
        }
        return ok;

    }


}
