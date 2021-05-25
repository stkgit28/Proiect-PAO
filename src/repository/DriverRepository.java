package repository;

import model.Driver;
import java.sql.*;
import java.util.HashSet;

public class DriverRepository {

    private Connection connection;

    public DriverRepository() {
        this.connection = DatabaseConfiguration.getDatabaseConnection();
    }

    public void insertDriver(Driver driver) {

        String query = "INSERT INTO driver VALUES(default,?,?,?,?,?,?,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, driver.getName());
            statement.setString(2, driver.getSurname());
            statement.setInt(3, driver.getAge());
            statement.setString(4, driver.getEmail());
            statement.setDouble(5, driver.getSalary());
            statement.setDouble(6, driver.getRating());
            int nr;
            boolean free = driver.isFree();
            if ((free == true)){
                nr=1;
            }
            else{
                nr=0;
            }
            //am codat true/false ca 1/0 din cauza ca in mySql nu ma lasa sa pun atributul unei coloane ca tip boolean
            statement.setInt(7,nr);




            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public HashSet<Driver> getAllDrivers() {
        HashSet<Driver> drivers = new HashSet<>();

        String query = "SELECT * FROM driver";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {

                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String surname = resultSet.getString(3);
                int age = resultSet.getInt(4);
                String email = resultSet.getString(5);
                double salary = resultSet.getDouble(6);
                double rating = resultSet.getDouble(7);
                int freeCoded = resultSet.getInt(8);
                boolean free;
                if(freeCoded==1)
                    free=true;
                else{
                    free=false;
                }


                Driver driver = new Driver(id,name,surname,age,email,salary,rating,free);
                drivers.add(driver);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return drivers;
    }

    public Driver getDriver(int driverId) {

        String query = "SELECT * FROM driver WHERE driver_id=?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, driverId);
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
                double salary = resultSet.getDouble(6);
                double rating = resultSet.getDouble(7);
                int freeCoded = resultSet.getInt(8);
                boolean free;
                if(freeCoded==1)
                    free=true;
                else{
                    free=false;
                }


                Driver driver = new Driver(id,name,surname,age,email,salary,rating,free);
                return driver;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public void updateDriverSalary(int id,int salary){
        String query = "UPDATE driver SET driver_salary = ? WHERE driver_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,salary);
            statement.setInt(2,id);
            statement.executeUpdate();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public void deleteDriver(int id){
        String query = "DELETE FROM driver WHERE driver_id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            statement.execute();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}
