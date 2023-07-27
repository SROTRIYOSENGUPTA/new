import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class UserDataWritelite {
    private static final String DATABASE_URL = "jdbc:sqlite:my_database.db"; // SQLite connection URL
    // No need for separate username and password for SQLite, as it doesn't use authentication like H2

    public static void main(String[] args) {
        // Prompt user for input (username and password)
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        scanner.close();

        User newUser = new User(name, username, password);

        // Insert user data into SQLite database
        insertUserIntoDatabase(newUser);
    }

    private static void insertUserIntoDatabase(User user) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL)) {
            String insertQuery = "INSERT INTO users (name, username, password) VALUES (?, ?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
                statement.setString(1, user.getName());
                statement.setString(2, user.getUsername());
                statement.setString(3, user.getPassword());

                statement.executeUpdate();

                System.out.println("User data has been inserted into the SQLite database");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
