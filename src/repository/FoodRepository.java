package repository;

import java.sql.*;
import java.util.HashSet;
import model.Food;


public class FoodRepository {

    private Connection connection;

    public FoodRepository() {
        this.connection = DatabaseConfiguration.getDatabaseConnection();
    }

    public void insertFood(Food food) {

        String query = "INSERT INTO food VALUES(default,?,?,?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, food.getName());
            statement.setDouble(2, food.getPrice());
            statement.setString(3, food.getRestName());
            statement.setString(4, food.getDetails());
            statement.setString(5, food.getSize());
            statement.setInt(6, food.getWeight());
            if(food.isSpicy()==true) {
                int spicy=1;
                statement.setInt(7,spicy);
            }
            else{
                int spicy=0;
                statement.setInt(7,spicy);

            }
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public HashSet<Food> getAllFoods() {
        HashSet<Food> foods = new HashSet<>();

        String query = "SELECT * FROM food";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                double price = resultSet.getDouble(3);
                String restName = resultSet.getString(4);
                String details = resultSet.getString(5);
                String size = resultSet.getString(6);
                int weight = resultSet.getInt(7);
                int nr = resultSet.getInt(8);
                boolean spicy;
                if(nr==1){
                    spicy=true;
                }
                else{
                    spicy=false;
                }

                Food food = new Food(id,name,price,restName,details,size,weight,spicy);
                foods.add(food);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return foods;
    }

    public Food getFood(int foodId) {
        Food food;
        String query = "SELECT * FROM food WHERE food_id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, foodId);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                return null;
            } else {
                resultSet.next();
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                double price = resultSet.getDouble(3);
                String restName = resultSet.getString(4);
                String details = resultSet.getString(5);
                String size = resultSet.getString(6);
                int weight = resultSet.getInt(7);
                int nr = resultSet.getInt(8);
                boolean spicy;
                if(nr==1){
                    spicy=true;
                }
                else{
                    spicy=false;
                }
                food = new Food(id,name,price,restName,details,size,weight,spicy);
                return food;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public Food getFoodByName(String foodName) {
        Food food;
        String query = "SELECT * FROM food WHERE food_name=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, foodName);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                return null;
            } else {
                resultSet.next();
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                double price = resultSet.getDouble(3);
                String restName = resultSet.getString(4);
                String details = resultSet.getString(5);
                String size = resultSet.getString(6);
                int weight = resultSet.getInt(7);
                int nr = resultSet.getInt(8);
                boolean spicy;
                if(nr==1){
                    spicy=true;
                }
                else{
                    spicy=false;
                }
                food = new Food(id,name,price,restName,details,size,weight,spicy);
                return food;
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

    public void updateFoodPrice(int id,int price){
        String query = "UPDATE food SET food_price = ? WHERE food_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,price);
            statement.setInt(2,id);
            statement.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void deleteFood(int id){
        String query = "DELETE FROM food WHERE food_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            statement.execute();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public HashSet<Food>  GetProdFromRest(String name) {
        HashSet<Food> foods = new HashSet<>();

        Food food;
        String query = "SELECT * FROM food WHERE restaurant_name=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                return null;
            } else {
                resultSet.next();
                int id = resultSet.getInt(1);
                String nameFood = resultSet.getString(2);
                double price = resultSet.getDouble(3);
                String restName = resultSet.getString(4);
                String details = resultSet.getString(5);
                String size = resultSet.getString(6);
                int weight = resultSet.getInt(7);
                int nr = resultSet.getInt(8);
                boolean spicy;
                if (nr == 1) {
                    spicy = true;
                } else {
                    spicy = false;
                }
                food = new Food(id, nameFood, price, restName, details, size, weight, spicy);
                foods.add(food);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return foods;
    }

}


