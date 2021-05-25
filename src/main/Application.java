package main;
import java.sql.Timestamp;
import java.util.*;

import repository.*;
import service.AppService;
import model.App;
import model.User;
import model.*;

public class Application {
    public static void main(String[] args) {
        AppService appService = AppService.getInstance();
        App app = new App();
        Scanner scanner = new Scanner(System.in);

        RestaurantRepository restRepo = new RestaurantRepository();
        DrinkRepository drinkRepo = new DrinkRepository();
        FoodRepository foodRepo = new FoodRepository();
        UserRepository userRepo = new UserRepository();
        DriverRepository driverRepo = new DriverRepository();

        while(true){
            System.out.println("Please type a comand!");
            String line = scanner.nextLine();
            Date date = new Date();
            switch(line){
                case "exit" :
                    System.out.println("Have a good day!");
                    System.exit(0);
                    break;

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
                    userRepo.insertUser(user);
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
                    String employeeName = hires[0];
                    String employeeSurname = hires[1];
                    int employeeAge = Integer.valueOf(hires[2]);
                    String employeeEmail = hires[3];
                    double salary = Double.valueOf(hires[4]);
                    double rating = Double.valueOf(hires[5]);
                    boolean free = Boolean.valueOf(hires[6]);
                    Driver driver = new Driver(new Random().nextInt(100),
                            employeeName,employeeSurname,employeeAge,employeeEmail,
                            salary,rating,free);
                    driverRepo.insertDriver(driver);
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
                    Set<Product> products = new TreeSet<>();
                    Restaurant rest = new Restaurant(new Random().nextInt(100),
                            restName,products);
                    restRepo.insertRestaurant(rest);
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
                    int nr = appService.checkExistRestaurantName(app, restname);
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
                            foodRepo.insertFood((Food) prodToAdd);
                            if (nr >=0) {
                                appService.addProduct(app.getRestaurants().get(nr), prodToAdd);
                            } else {
                                Set<Product> products1 = new TreeSet<>();
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
                            drinkRepo.insertDrink((Drink) drinkToAdd);
                            if (nr >= 0) {
                                appService.addProduct(app.getRestaurants().get(nr), drinkToAdd);
                                break;
                            } else {
                                Set<Product> products2 = new TreeSet<>();
                                Restaurant rest2 = new Restaurant(new Random().nextInt(100),
                                        drinkRestName,products2);
                                appService.addRestaurant(app,rest2);
                                appService.addProduct(rest2,drinkToAdd );
                                break;
                            }
                    }
                    break;
                case "show_products":
                    System.out.println(foodRepo.getAllFoods());
                    System.out.println(drinkRepo.getAllDrinks());
                    break;
                case "food_byId":
                    System.out.println("Specify the product id!");
                    int next = Integer.valueOf(scanner.nextLine());
                    System.out.println(foodRepo.getFood(next));
                    break;
                case "food_byName":
                    System.out.println("Specify the product name!");
                    String nameSearch = scanner.nextLine();
                    System.out.println(foodRepo.getFoodByName(nameSearch));
                    break;
                case "update_priceFood":
                    System.out.println("Specify the product id that you want to update!");
                    int idUpdate =Integer.valueOf(scanner.nextLine());
                    System.out.println("Specify the price you wan to set!");
                    int priceUpdate = Integer.valueOf(scanner.nextLine());
                    foodRepo.updateFoodPrice(idUpdate,priceUpdate);
                    break;
                case "delete_food":
                    System.out.println("Specify the product id that you want to delete!");
                    int idDelete =Integer.valueOf(scanner.nextLine());
                    foodRepo.deleteFood(idDelete);
                    break;
                case "drink_byId":
                    System.out.println("Specify the product id!");
                    int nextDrink = Integer.valueOf(scanner.nextLine());
                    System.out.println(drinkRepo.getDrink(nextDrink));
                    break;
                case "update_priceDrink":
                    System.out.println("Specify the product id that you want to update!");
                    int idUpdate1 =Integer.valueOf(scanner.nextLine());
                    System.out.println("Specify the price you wan to set!");
                    int priceUpdate1 = Integer.valueOf(scanner.nextLine());
                    drinkRepo.updateDrinkPrice(idUpdate1,priceUpdate1);
                    break;
                case "delete_drink":
                    System.out.println("Specify the product id that you want to delete!");
                    int idDelete1 =Integer.valueOf(scanner.nextLine());
                    drinkRepo.deleteDrink(idDelete1);
                    break;
                case "get_restaurant":
                    System.out.println("Specify the restaurant id!");
                    int idRest = Integer.valueOf(scanner.nextLine());
                    System.out.println(restRepo.getRestaurant(idRest));
                    break;

                case "update_res":
                    System.out.println("Specify the restaurant id that you want to update!");
                    int idNew =Integer.valueOf(scanner.nextLine());
                    System.out.println("Specify the restaurant new name!");
                    String newName = scanner.next();
                   restRepo.updateRestaurant(idNew,newName);
                    break;
                case "get_food_byres":
                    System.out.println("Specify the restaurant name id that you want to get the menu for!");
                    String searchName = scanner.next();
                    System.out.println(foodRepo.GetProdFromRest(searchName));
                    break;
                case "get_restaurants":
                    System.out.println(restRepo.getAllRestaurants());
                    break;
                case "get_users":
                    System.out.println(userRepo.getAllUsers());
                    break;
                case "get_user":
                    System.out.println("Pleate type the id of the user you want to find!");
                    int idUser = Integer.valueOf(scanner.next());
                    System.out.println(userRepo.getUser(idUser));
                    break;
                case "update_user":
                    System.out.println("Specify the user id that you want to update!");
                    int idUserUpdate =Integer.valueOf(scanner.nextLine());
                    System.out.println("Specify the user's new email!");
                    String newEmail = scanner.next();
                    userRepo.updateUserEmail(idUserUpdate,newEmail);
                    break;
                case "delete_user":
                    System.out.println("Specify the user id that you want to update!");
                    int idUserDelete =Integer.valueOf(scanner.nextLine());
                    userRepo.deleteUser(idUserDelete);
                    break;
                case "get_drivers":
                    System.out.println(driverRepo.getAllDrivers());
                    break;
                case "get_driver":
                    System.out.println("Pleate type the id of the driver you want to find!");
                    int idDriver = Integer.valueOf(scanner.next());
                    System.out.println(driverRepo.getDriver(idDriver));
                    break;
                case "update_driver":
                    System.out.println("Specify the driver id that you want to update!");
                    int idDriverUpdate =Integer.valueOf(scanner.nextLine());
                    System.out.println("Specify the driver's new salary!");
                    int driverSalary = Integer.valueOf(scanner.next());
                    driverRepo.updateDriverSalary(idDriverUpdate,driverSalary);
                    break;
                case "delete_driver":
                    System.out.println("Specify the driver id that you want to delete!");
                    int idDriverDelete =Integer.valueOf(scanner.nextLine());
                    driverRepo.deleteDriver(idDriverDelete);
                default: System.out.println("Unknown comand!");

            }



        }

    }
}
