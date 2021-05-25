package service;
import model.*;
import model.Product;

public class AppService {

    private static final AppService INSTANCE = new AppService();

    private AppService(){

    }
    public static AppService getInstance(){
        return INSTANCE;
    }


    public void createAccount(App app, User user){
        app.getUsers().add(user);

    }


    public void printUsers(App app) {
        for(User u : app.getUsers()) {
            if(u != null) {
                System.out.println(u);
            }
        }
    }

    public void deleteuser(App app, String email) {
        User userdeSters = new User();
        for (User u : app.getUsers()) {
            if (u != null && u.getEmail().equals(email)) {
                userdeSters= u;

            }




        }
        app.getUsers().remove(userdeSters);
    }



    public void fireEmployee(App app, String email) {
        Driver driverdeSters= new Driver();
        //am folosit chestia asta ca sa evit eroarea Modifying (modifici o colectie pe care o parcurgi)
        for (Driver d : app.getDrivers()) {
            if (d != null && d.getEmail().equals(email)) {
                driverdeSters=d;
            }


        }
        app.getDrivers().remove(driverdeSters);
    }

    public void hirePerson(App app, Driver driver){
        app.getDrivers().add(driver);

    }



    public void printEmployees(App app) {
        for(Driver d : app.getDrivers()) {
            if(d != null) {
                System.out.println(d);
            }
        }
    }
    public void addRestaurant(App app, Restaurant rest){
        app.getRestaurants().add(rest);


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



    public void addProduct(Restaurant rest, Product product){
        rest.getProducts().add(product);
    }


    public void printProductDetails(Restaurant rest){
        for(Product p : rest.getProducts()){
            if(p!=null) {
                System.out.print(p);
            }
        }
    }

    public int checkExistRestaurantName(App app, String name){
        boolean ok=false;
        int i=0;
        for(Restaurant r : app.getRestaurants()){
            if(r!=null && r.getRestaurantName().equals(name)){
                ok=true;
                break;

            } else{
                i+=1;
            }
        }

        if(ok==true){
            return i;
        }
        else{
            return -1;
        }
    }


}
