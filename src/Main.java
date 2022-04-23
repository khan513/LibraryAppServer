import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    private static Connection connection;

    public static void main(String[] args) {
        connectToTheDatabase();

    }

    private static void connectToTheDatabase () {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            BufferedReader reader = new BufferedReader(new FileReader("/home/khan/IdeaProjects/LibraryAppServer/config"));
            String line, url, user, password;
            line = reader.readLine();
            url = line.substring(line.indexOf(" ") + 1);
            line = reader.readLine();
            user = line.substring(line.indexOf(" ") + 1);
            line = reader.readLine();
            password = line.substring(line.indexOf(" ") + 1);
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the database successfully!");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}