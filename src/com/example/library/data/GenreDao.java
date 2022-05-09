package com.example.library.data;

import com.example.library.model.Genre;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class GenreDao {
    public static void addGenre(Genre genre) {
        try {
            String query = "INSERT INTO genres(genre) VALUES(?)";
            PreparedStatement preparedStatement = LibraryDB.connection.prepareStatement(query);
            preparedStatement.setString(1, genre.getGenre());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("Genre was inserted successfully! :)");
        } catch (SQLException e) {
            System.out.println("Could not insert the genre :(");
            e.printStackTrace();
        }
    }

    public static List<Genre> getAllGenres() {
        List<Genre> genres = new LinkedList<>();
        try {
            Statement statement = LibraryDB.connection.createStatement();
            String query = "SELECT * FROM genres";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                genres.add(
                        new Genre(
                                result.getLong("genre_id"),
                                result.getString("genre")
                        )
                );
            }
            statement.close();
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("All the genres were retrieved successfully! :)");
        return genres;
    }

    public static void editGenre(Long id, Genre newGenre) {
        try {
            String query = "UPDATE genres SET genre = ? WHERE genre_id = " + id;
            PreparedStatement preparedStatement = LibraryDB.connection.prepareStatement(query);
            preparedStatement.setString(1, newGenre.getGenre());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("Genre is up to date now! :)");
        } catch (SQLException e) {
            System.out.println("Could not update the genre :(");
            e.printStackTrace();
        }
    }

    public static void deleteGenreById(Long id) {
        try {
            Statement statement = LibraryDB.connection.createStatement();
            String query = "DELETE FROM genres WHERE genre_id = " + id;
            statement.executeUpdate(query);
            statement.close();
            System.out.println("Genre was deleted successfully! :)");
        } catch (SQLException e) {
            System.out.println("Could not delete the genre :(");
            e.printStackTrace();
        }
    }
}