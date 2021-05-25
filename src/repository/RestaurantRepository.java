package repository;


import model.Product;
import model.Restaurant;
import java.sql.Connection;
import java.sql.*;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;


public class RestaurantRepository {

    private Connection connection;

    public RestaurantRepository() {
        this.connection = DatabaseConfiguration.getDatabaseConnection();
    }


    public void insertRestaurant(Restaurant restaurant) {

        String query = "INSERT INTO restaurant VALUES(default,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, restaurant.getRestaurantName());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public HashSet<Restaurant> getAllRestaurants() {
        HashSet<Restaurant> restaurants = new HashSet<>();

        String query = "SELECT * FROM restaurant";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                Set<Product> products = new TreeSet<>();
                Restaurant restaurant = new Restaurant(id,name,products);
                restaurants.add(restaurant);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return restaurants;
    }

    public Restaurant getRestaurant(int restId) {
        String query = "SELECT * FROM restaurant WHERE restaurant_id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, restId);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                return null;
            } else {
                resultSet.next();
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                Set<Product> products = new TreeSet<>();
                Restaurant restaurant = new Restaurant(id,name,products);
                return restaurant;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public Restaurant getRestaurantByName(String restName) {
        String query = "SELECT * FROM restaurant WHERE restaurant_name=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, restName);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                return null;
            } else {
                resultSet.next();
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                Set<Product> products = new TreeSet<>() ;
                Restaurant restaurant = new Restaurant(id,name,products);


                return restaurant;
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return null;
    }
    public void updateRestaurant(int id,String name){
        String query = "UPDATE restaurant SET restaurant_name = ? WHERE restaurant_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,name);
            statement.setInt(2,id);
            statement.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void deleteRestaurant(int id){
        String query = "DELETE FROM restaurant WHERE restaurant_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            statement.execute();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

}
