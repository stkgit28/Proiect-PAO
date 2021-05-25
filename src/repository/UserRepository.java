package repository;


import model.User;

import java.sql.*;
import java.util.HashSet;

public class UserRepository {

    private Connection connection;

    public UserRepository() {
        this.connection = DatabaseConfiguration.getDatabaseConnection();
    }

    public void insertUser(User user) {

        String query = "INSERT INTO user VALUES(default,?,?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setInt(3, user.getAge());
            statement.setString(4, user.getEmail());
            statement.setString(5, user.getCity());
            statement.setString(6, user.getStreet());

            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public HashSet<User> getAllUsers() {
        HashSet<User> users = new HashSet<>();

        String query = "SELECT * FROM user";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String surname = resultSet.getString(3);
                int age = resultSet.getInt(4);
                String email = resultSet.getString(5);
                String city = resultSet.getString(6);
                String street = resultSet.getString(7);


                User user = new User(id,name,surname,age,email,city,street);
                users.add(user);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }
    public User getUser(int userId) {

        String query = "SELECT * FROM user WHERE user_id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                return null;
            } else {
                resultSet.next();
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String surname = resultSet.getString(3);
                int age = resultSet.getInt(4);
                String email = resultSet.getString(5);
                String city = resultSet.getString(6);
                String street = resultSet.getString(7);

                User user = new User(id,name,surname,age,email,city,street);
                return user;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void updateUserEmail(int id,String email){
        String query = "UPDATE user SET user_email = ? WHERE user_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1,email);
            statement.setInt(2,id);
            statement.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void deleteUser(int id){
        String query = "DELETE FROM user WHERE user_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            statement.execute();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }







}
