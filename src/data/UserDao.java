package data;

import model.Author;
import model.User;

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
            String query = "INSERT INTO users(login, password) VALUES(?, ?)";
            PreparedStatement preparedStatement = LibraryDB.connection.prepareStatement(query);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
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
            String query = "UPDATE users SET login = ?, password = ? WHERE user_id = " + id;
            PreparedStatement preparedStatement = LibraryDB.connection.prepareStatement(query);
            preparedStatement.setString(1, newUser.getLogin());
            preparedStatement.setString(2, newUser.getPassword());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("User is up to date now! :)");
        } catch (SQLException e) {
            System.out.println("Could not update the user :(");
            e.printStackTrace();
        }
    }
}