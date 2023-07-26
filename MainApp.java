import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Save username and password");
            System.out.println("2. Read all username-password pairs");
            System.out.println("3. Get password for a username");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.print("Enter username: ");
                String username = scanner.next();
                System.out.print("Enter password: ");
                String password = scanner.next();

                UserDataWriteH2 writer = new UserDataWriteH2();
                writer.saveUser(username, password);
            } else if (choice == 2) {
                UserDataReadH2 reader = new UserDataReadH2();
                reader.readData();
            } else if (choice == 3) {
                System.out.print("Enter username: ");
                String username = scanner.next();

                // Retrieve password for the given username and display it
                // (you can implement this based on your database structure)
                // For simplicity, we'll skip this part in the example.
            } else if (choice == 4) {
                System.out.println("Exiting the application.");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
