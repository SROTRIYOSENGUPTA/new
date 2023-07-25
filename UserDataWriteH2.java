// this is the beginning of the UserDataWriteH2.java code
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDataWriteH2 {
    private static final String DATABASE_URL = "jdbc:h2:~/test"; //This will be the H2 database URL 

    private static final String DATABASE_USER = "sa";     // This will be the database username

    private static final String DATABASE_PASSWORD = ""; // This will be the database password

    public static void main(String[] args) {
        // Prompt user for input (username and password)
        // ...

        // Create the User object from user input
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {

            System.out.println("Connection to H2 database successful.");

        } catch (SQLException e) {
            
            System.err.println("Connection failed: " + e.getMessage());
        }
        
        String name = "";

        String username = "sa";

        String password = "";

        User newUser = new User(name, username, password);

        // Insert user data into H2 database
        insertUserIntoDatabase(newUser);
    }

    private static void insertUserIntoDatabase(User user) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {
            String insertQuery = "INSERT INTO users (username, password) VALUES (?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {

                statement.setString(1, user.getUsername());

                statement.setString(2, user.getPassword());

                statement.executeUpdate();

                System.out.println("Now the user data has been updated to the H2 database");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
// this is the end of the UserDataWriteH2.java code
