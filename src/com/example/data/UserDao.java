package com.example.data;

import com.example.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class UserDao {

    public static void addUser(User user) {
        try {
            String query = "INSERT INTO users(name, surname, login, password) VALUES(?, ?, ?, ?)";
            PreparedStatement preparedStatement = prepareStatement(user, query);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("User was inserted successfully! :)");
        } catch (SQLException e) {
            System.out.println("Could not insert the user :(");
            e.printStackTrace();
        }
    }

    public static List<User> getAllUsers() {
        List<User> users = new LinkedList<>();
        try {
            Statement statement = LibraryDB.connection.createStatement();
            String query = "SELECT * FROM users";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                users.add(
                        new User(
                                result.getLong("user_id"),
                                result.getString("name"),
                                result.getString("surname"),
                                result.getString("login"),
                                result.getString("password")
                        )
                );
            }
            statement.close();
            result.close();
        } catch (SQLException e) {
            System.out.println("Could not retrieve the users :(");
            e.printStackTrace();
        }
        if (users.isEmpty())
            return Collections.emptyList();
        System.out.println("All the users were retrieved successfully! :)");
        return users;
    }

    public static void deleteUserById(Long id) {
        try {
            Statement statement = LibraryDB.connection.createStatement();
            String query = "DELETE FROM users WHERE user_id = " + id;
            statement.executeUpdate(query);
            statement.close();
            System.out.println("User was deleted successfully! :)");
        } catch (SQLException e) {
            System.out.println("Could not delete the user");
            e.printStackTrace();
        }
    }

    public static void editUser(Long id, User newUser) {
        try {
            String query = "UPDATE users SET name = ?, surname = ?, login = ?, password = ? WHERE user_id = " + id;
            PreparedStatement preparedStatement = prepareStatement(newUser, query);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("User is up to date now! :)");
        } catch (SQLException e) {
            System.out.println("Could not update the user :(");
            e.printStackTrace();
        }
    }

    public static User login(String login, String password) {
        try {
            PreparedStatement preparedStatement = LibraryDB.connection.prepareStatement("SELECT * FROM users WHERE id=? and password=?");
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                return new User(
                        result.getLong("user_id"),
                        result.getString("name"),
                        result.getString("surname"),
                        result.getString("login"),
                        result.getString("password")
                );
            }
            preparedStatement.close();
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static PreparedStatement prepareStatement(User user, String query) throws SQLException {
        PreparedStatement preparedStatement = LibraryDB.connection.prepareStatement(query);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getSurname());
        preparedStatement.setString(3, user.getLogin());
        preparedStatement.setString(4, user.getPassword());
        return preparedStatement;
    }
}