package com.example.library.data;

import com.example.library.model.Book;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class BookDao {
    public static void addBook(Book book) {
        try {
            String query = "INSERT INTO books(title, total_pages, rating, isbn, published_date, publisher_id, user_id) VALUES(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = prepareStatement(book, query);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("Book was inserted successfully! :)");
        } catch (SQLException e) {
            System.out.println("Could not insert the book :(");
            e.printStackTrace();
        }
    }

    public static List<Book> getAllBooks() {
        List<Book> books = new LinkedList<>();
        try {
            Statement statement = LibraryDB.connection.createStatement();
            String query = "SELECT * FROM books";
            books = queryBooks(statement, query);
        } catch (SQLException e) {
            System.out.println("Could not retrieve all the books :(");
            e.printStackTrace();
        }
        System.out.println("All the books were retrieved successfully! :)");
        return books;
    }

    public static List<Book> getBooksByReadId(Long id) {
        List<Book> books = new LinkedList<>();
        try {
            Statement statement = LibraryDB.connection.createStatement();
            String query = "SELECT * FROM books WHERE user_id = " + id;
            books = queryBooks(statement, query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("All the books were retrieved successfully! :)");
        return books;
    }

    public static void deleteBookById(Long id) {
        try {
            Statement statement = LibraryDB.connection.createStatement();
            String query = "DELETE FROM books WHERE book_id = " + id;
            statement.executeUpdate(query);
            statement.close();
            System.out.println("Book was deleted successfully! :)");
        } catch (SQLException e) {
            System.out.println("Could not delete the book :(");
            e.printStackTrace();
        }
    }

    public static void deleteBookByTitle(String title) {
        try {
            Statement statement = LibraryDB.connection.createStatement();
            String query = "DELETE FROM books WHERE title = '" + title + "'";
            statement.executeUpdate(query);
            statement.close();
            System.out.println("Book was deleted successfully! :)");
        } catch (SQLException e) {
            System.out.println("Could not delete the book :(");
            e.printStackTrace();
        }
    }

    public static void editBook(Long id, Book newBook) {
        try {
            String query = "UPDATE books SET title = ?, total_pages = ?, rating = ?, isbn = ?, published_date = ?, publisher_id = ?, user_id = ? WHERE book_id = " + id;
            PreparedStatement preparedStatement = prepareStatement(newBook, query);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("Book is up to date now! :)");
        } catch (SQLException e) {
            System.out.println("Could not update the book :(");
            e.printStackTrace();
        }
    }

    private static PreparedStatement prepareStatement(Book book, String query) throws SQLException {
        PreparedStatement preparedStatement = LibraryDB.connection.prepareStatement(query);
        preparedStatement.setString(1, book.getTitle());
        preparedStatement.setInt(2, book.getTotal_pages());
        preparedStatement.setDouble(3, book.getRating());
        preparedStatement.setString(4, book.getIsbn());
        preparedStatement.setDate(5, book.getPublished_date());
        preparedStatement.setLong(6, book.getPublisher_id());
        preparedStatement.setLong(7, book.getUser_id());
        return preparedStatement;
    }

    private static List<Book> queryBooks(Statement statement, String query) throws SQLException {
        List<Book> books = new LinkedList<>();
        ResultSet result = statement.executeQuery(query);
        while (result.next()) {
            books.add(new Book(
                    result.getLong("book_id"),
                    result.getString("title"),
                    result.getInt("total_pages"),
                    result.getDouble("rating"),
                    result.getString("isbn"),
                    result.getDate("published_date"),
                    result.getLong("publisher_id"),
                    result.getLong("user_id")
            ));
        }
        statement.close();
        result.close();
        return books;
    }
}