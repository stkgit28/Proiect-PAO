package main;
import java.util.*;
import Service.AppService;
import model.App;
import model.Person;
import model.User;
import model.*;

public class Application {
    public static void main(String[] args) {
        AppService appService = new AppService();
        App app = new App();
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println("Please type a comand!");
            String line = scanner.nextLine();
            switch(line){
                case "exit" :
                    System.out.println("Have a good day!");
                    System.exit(0);
                    break;
                default: System.out.println("Unknown comand!");
                case "create":
                    System.out.println("To create your account please write: name/surname" +
                            "/age/email/city/street");
                    String create = scanner.nextLine();
                    String[] attributes = create.split("/");
                    String name = attributes[0];
                    String surname = attributes[1];
                    int age = Integer.valueOf(attributes[2]);
                    String email = attributes[3];
                    String city = attributes[4];
                    String street = attributes[5];
                    User user = new User(new Random().nextInt(100),name,
                            surname,age,email,city,street);
                    appService.createAccount(app,user);
                    break;
                case "view_users" :
                    appService.printUsers(app);
                    break;
                case "delete_account":
                    System.out.println("Introduceti email-ul contului pe care doriti sa l stergeti!");
                    String delete = scanner.next();
                    appService.deleteuser(app,delete);
                    break;
                case "hire":
                    System.out.println("Introduce the details of the person you want to hire: name/surname" +
                            "/age/email/salary/rating/free?");
                    String hire = scanner.nextLine();
                    String[] hires = hire.split("/");
                    String employee_name = hires[0];
                    String employee_surname = hires[1];
                    int employee_age = Integer.valueOf(hires[2]);
                    String employee_email = hires[3];
                    double salary = Double.valueOf(hires[4]);
                    double rating = Double.valueOf(hires[5]);
                    boolean free = Boolean.valueOf(hires[6]);
                    Driver driver = new Driver(new Random().nextInt(100),
                            employee_name,employee_surname,employee_age,employee_email,
                            salary,rating,free);

                    appService.hirePerson(app,driver);
                    break;
                case  "view_employees":
                    appService.printEmployees(app);
                    break;
                case "fire_employee":
                    System.out.println("Insert the email of the employee you would liek to fire!");
                    String fire = scanner.next();
                    appService.fireEmployee(app,fire);
                    break;
                case  "add_restaurant":
                    System.out.println("Insert the restaurant details!: restaurant_name");
                    String restName = scanner.nextLine();
                    Product[] products = new Product[100];
                    Restaurant rest = new Restaurant(new Random().nextInt(100),
                            restName,products);
                    appService.addRestaurant(app,rest);
                    break;
                case "view_restaurants":
                    appService.printRestaurant(app);
                    break;
                case "add_product":
                    System.out.println("Select the restaurant that has this product!");
                    String restname = scanner.nextLine();
                    System.out.println("Select the type drink/food");
                    String type = scanner.nextLine();
                    int nr = appService.checkRest(app, restname);
                    switch(type) {
                        case "food":
                            System.out.println("Specify the product details! : name/price/restName/" +
                                    "detail/size/wight/spicy");
                            String productDetails = scanner.nextLine();
                            String[] prodatt = productDetails.split("/");
                            String prodName = prodatt[0];
                            double price = Double.valueOf(prodatt[1]);
                            String restaurantName = prodatt[2];
                            String details = prodatt[3];
                            String size = prodatt[4];
                            int weight = Integer.valueOf(prodatt[5]);
                            boolean spicy = Boolean.valueOf(prodatt[6]);
                            Product prodToAdd = new Food(new Random().nextInt(100),
                                    prodName, price, restaurantName, details, size, weight, spicy);
                            if (nr != 0) {
                                appService.addProduct(app.getRestaurants()[nr], prodToAdd);
                            } else {
                                Product[] products1 = new Product[100];
                                Restaurant rest1 = new Restaurant(new Random().nextInt(100),
                                        restaurantName,products1);
                                appService.addRestaurant(app,rest1);
                                appService.addProduct(rest1, prodToAdd);
                            }
                            break;
                        case "drink":
                            System.out.println("Specify the product details! : name/price/restName/size/volume/details");
                            String drinkDet = scanner.nextLine();
                            String[] drinks = drinkDet.split("/");
                            String drinkName = drinks[0];
                            double drinkprice = Double.valueOf(drinks[1]);
                            String drinkRestName = drinks[2];
                            String drinkSize = drinks[3];
                            int drinkVolume = Integer.valueOf(drinks[4]);
                            String drinkDetails = drinks[5];
                            Product drinkToAdd = new Drink(new Random().nextInt(100),drinkName,drinkprice,drinkRestName,
                                    drinkSize,drinkVolume,drinkDetails);
                            if (nr != 0) {
                                appService.addProduct(app.getRestaurants()[nr], drinkToAdd);
                            } else {
                                Product[] products2 = new Product[100];
                                Restaurant rest2 = new Restaurant(new Random().nextInt(100),
                                        drinkRestName,products2);
                                appService.addRestaurant(app,rest2);
                                appService.addProduct(rest2,drinkToAdd );
                            }
                            break;
                    }


            }








        }

    }
}
