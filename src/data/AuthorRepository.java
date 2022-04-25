package data;

import model.Author;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AuthorRepository {

    public static void addAuthor(Author author) {
        try {
            String query = "INSERT INTO authors(author_id, first_name, last_name) VALUES(?, ?, ?)";
            PreparedStatement preparedStatement = Dao.connection.prepareStatement(query);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
