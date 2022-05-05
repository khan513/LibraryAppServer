package data;

import model.Author;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class AuthorDao {

    public static void addAuthor(Author author) {
        try {
            String query = "INSERT INTO authors(first_name, last_name) VALUES(?, ?)";
            PreparedStatement preparedStatement = LibraryDB.connection.prepareStatement(query);
            preparedStatement.setString(1, author.getFirst_name());
            preparedStatement.setString(2, author.getLast_name());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("Author was inserted successfully! :)");
        } catch (SQLException e) {
            System.out.println("Could not insert the author :(");
            e.printStackTrace();
        }
    }

    public static List<Author> getAllAuthors() {
        List<Author> authors = new LinkedList<>();
        try {
            Statement statement = LibraryDB.connection.createStatement();
            String query = "SELECT * FROM authors";
            ResultSet result = statement.executeQuery(query);
            while (result.next()) {
                authors.add(
                        new Author(
                                result.getLong("author_id"),
                                result.getString("first_name"),
                                result.getString("last_name")
                        )
                );
            }
            statement.close();
            result.close();
            System.out.println("All the authors were retrieved successfully! :)");
            return authors;
        } catch (SQLException e) {
            System.out.println("Could not retrieve the authors :(");
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    public static void deleteAuthorById(Long id) {
        try {
            Statement statement = LibraryDB.connection.createStatement();
            String query = "DELETE FROM authors WHERE author_id = " + id;
            statement.executeUpdate(query);
            statement.close();
            System.out.println("Author was deleted successfully! :)");
        } catch (SQLException e) {
            System.out.println("Could not delete the author");
            e.printStackTrace();
        }
    }

    public static void editAuthor(Long id, Author newAuthor) {
        try {
            String query = "UPDATE authors SET first_name = ?, last_name = ? WHERE author_id = " + id;
            PreparedStatement preparedStatement = LibraryDB.connection.prepareStatement(query);
            preparedStatement.setString(1, newAuthor.getFirst_name());
            preparedStatement.setString(2, newAuthor.getLast_name());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("Author is up to date now! :)");
        } catch (SQLException e) {
            System.out.println("Could not update the author :(");
            e.printStackTrace();
        }
    }
}
