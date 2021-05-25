package repository;

import model.Drink;

import java.sql.*;
import java.util.HashSet;

public class DrinkRepository {

    private Connection connection;

    public DrinkRepository() {
        this.connection = DatabaseConfiguration.getDatabaseConnection();
    }

    public void insertDrink(Drink drink) {

        String query = "INSERT INTO drink VALUES(default,?,?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, drink.getName());
            statement.setDouble(2, drink.getPrice());
            statement.setString(3, drink.getRestName());
            statement.setString(4, drink.getSize());
            statement.setInt(5, drink.getVolume());
            statement.setString(6, drink.getDetails());

            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public HashSet<Drink> getAllDrinks() {
        HashSet<Drink> drinks = new HashSet<>();

        String query = "SELECT * FROM drink";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                double price = resultSet.getDouble(3);
                String restName = resultSet.getString(4);
                String size = resultSet.getString(5);
                int volume = resultSet.getInt(6);
                String details = resultSet.getString(7);



                Drink drink = new Drink(id,name,price,restName,size,volume,details);
                drinks.add(drink);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return drinks;
    }

    public Drink getDrink(int drinkId) {
        String query = "SELECT * FROM drink WHERE product_id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, drinkId);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                return null;
            } else {
                resultSet.next();
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                double price = resultSet.getDouble(3);
                String restName = resultSet.getString(4);
                String size = resultSet.getString(5);
                int volume = resultSet.getInt(6);
                String details = resultSet.getString(7);

                Drink drink = new Drink(id,name,price,restName,size,volume,details);
                return drink;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void updateDrinkPrice(int id,int price){
        String query = "UPDATE drink SET product_price = ? WHERE product_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,price);
            statement.setInt(2,id);
            statement.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void deleteDrink(int id){
        String query = "DELETE FROM drink WHERE product_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            statement.execute();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public HashSet<Drink>  GetProdFromRest(String name) {
        HashSet<Drink> drinks = new HashSet<>();

        String query = "SELECT * FROM drink WHERE restaurant_name=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                return null;
            } else {
                resultSet.next();
                int id = resultSet.getInt(1);
                String nameDrink = resultSet.getString(2);
                double price = resultSet.getDouble(3);
                String restName = resultSet.getString(4);
                String size = resultSet.getString(5);
                int volume = resultSet.getInt(6);
                String details = resultSet.getString(7);

                Drink drink = new Drink(id,nameDrink,price,restName,size,volume,details);
                drinks.add(drink);

            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return drinks;
    }


}
