import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UserDataReadlite {
    private static final String DATABASE_URL = "jdbc:sqlite:my_database.db"; // SQLite connection URL
    // No need for separate username and password for SQLite, as it doesn't use authentication like H2

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL)) {
            System.out.println("Connection to SQLite database successful.");
        } catch (SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username to search: ");
        String searchUsername = scanner.nextLine();
        scanner.close();

        // Search for the password based on the username input by the user
        String foundPassword = searchForPassword(searchUsername);

        // Display the result
        if (foundPassword != null) {
            System.out.println("Password for username '" + searchUsername + "' is: " + foundPassword);
        } else {
            System.out.println("Username not found.");
        }
    }

    private static String searchForPassword(String searchUsername) {
        try (Connection connection = DriverManager.getConnection(DATABASE_URL)) {
            String selectQuery = "SELECT password FROM users WHERE username = ?";

            try (PreparedStatement statement = connection.prepareStatement(selectQuery)) {
                statement.setString(1, searchUsername);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getString("password");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Username not found
    }
}
