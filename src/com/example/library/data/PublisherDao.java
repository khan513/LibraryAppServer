package com.example.library.data;

import com.example.library.model.Genre;
import com.example.library.model.Publisher;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class PublisherDao {

    public static void addPublisher(Publisher publisher) {
        try {
            String query = "INSERT INTO publishers(name) VALUES(?)";
            PreparedStatement preparedStatement = LibraryDB.connection.prepareStatement(query);
            preparedStatement.setString(1, publisher.getName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("Publisher was inserted successfully! :)");
        } catch (SQLException e) {
            System.out.println("Could not insert the publisher :(");
            e.printStackTrace();
        }
    }

    public static List<Publisher> getAllPublishers() {
        List<Publisher> publishers = new LinkedList<>();
        try {
            Statement statement = LibraryDB.connection.createStatement();
            String query = "SELECT * FROM publishers";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                publishers.add(
                        new Publisher(
                                result.getLong("publisher_id"),
                                result.getString("name")
                        )
                );
            }
            statement.close();
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("All the publishers were retrieved successfully! :)");
        return publishers;
    }

    public static void editPublisher(Long id, Publisher newPublisher) {
        try {
            String query = "UPDATE publishers SET name = ? WHERE publisher_id = " + id;
            PreparedStatement preparedStatement = LibraryDB.connection.prepareStatement(query);
            preparedStatement.setString(1, newPublisher.getName());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("Publisher is up to date now! :)");
        } catch (SQLException e) {
            System.out.println("Could not update the publisher :(");
            e.printStackTrace();
        }
    }

    public static void deletePublisherById(Long id) {
        try {
            Statement statement = LibraryDB.connection.createStatement();
            String query = "DELETE FROM publishers WHERE publisher_id = " + id;
            statement.executeUpdate(query);
            statement.close();
            System.out.println("Publisher was deleted successfully! :)");
        } catch (SQLException e) {
            System.out.println("Could not delete the publisher :(");
            e.printStackTrace();
        }
    }
}