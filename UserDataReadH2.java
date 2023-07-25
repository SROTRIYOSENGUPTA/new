// this is the beginning of the UserDataReadH2.java code
import java.sql.Connection;

import java.sql.DriverManager;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.util.Scanner;

public class UserDataReadH2 {
    private static final String DATABASE_URL = "jdbc:h2:~/test";

    private static final String DATABASE_USER = "sa"; // this is the default username in the UserDataReadH2.java file path

    private static final String DATABASE_PASSWORD = "";

    public static void main(String[] args) {

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {

            System.out.println("Connection to H2 database successful.");

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

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD)) {

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
// this is the end of the UserDataReadH2.java code

// i am trying to connect the UserDataRead.java to the H2 database and make sure they are connected such that usernames can be read and searched from it

