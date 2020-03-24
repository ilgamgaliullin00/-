package oodb.lab5;

import com.google.gson.Gson;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class Database {

    private Gson gson = new Gson();


    private final static String URL = "jdbc:postgresql://localhost/postgres";
    private final static String USER = "postgres";
    private final static String PASSWORD = "ilgam2000";


    void saveToDB() {
        System.out.println("Сохранение в БД");
        org.postgresql.Driver driver1 = new org.postgresql.Driver();
        try {
            Class.forName(driver1.getClass().getName());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        final String SQL_INPUT1 = "INSERT INTO cars (car) VALUES (ROW('Парацетамол','Е-272',2500,true)) returning id";
        final String SQL_INPUT2 = "INSERT INTO cars (car) VALUES (ROW('Пластырь','Нурофен',900,false)) returning id";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_INPUT1)) {

            ResultSet resultSet = preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_INPUT2)) {;
            ResultSet resultSet = preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    void update(){
        System.out.println("Обновлние в БД");
        org.postgresql.Driver driver1 = new org.postgresql.Driver();
        try {
            Class.forName(driver1.getClass().getName());

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        final String SQL_UPDATE1 = "UPDATE cars SET car.model = 'Аспирин' WHERE id=1 returning id";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPDATE1)) {

            ResultSet resultSet = preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void searchInDB() {
        System.out.println("Поиск в БД");
        final String SQL_SELECT1 = "SELECT (cars.car).model FROM cars WHERE (cars.car).price > 1100;";
        final String SQL_SELECT2 = "SELECT * FROM cars";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT1)) {

            String example = "";


            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                example = resultSet.getString("model");
                System.out.println("Поле 'name' содержит: " + example);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT2)) {

            String example = "";

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                example = resultSet.getString("car");
                System.out.println("Поле 'car' : " + example);
            }
            System.out.println("Поле 'car' : " + example);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}