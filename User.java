// this is the beginning of the User class code

import java.io.Serializable; //this makes storing and sending objects easy

public class User implements Serializable {

    private static final long serialVersionUID = 1L; // i am using this for version control

    private String name; // this is the name of the user

    private String username; // this is the username the user has been assigned

    private String password; // this is the password with respect to the specific user account
    
    public User(String name, String username, String password) 
    {

        this.name = name;

        this.username = username;

        this.password = password;
    }
    public String getName() {

        return name; 
    }

    public String getUsername() {

        return username;
    }

    public String getPassword() {
        
        return password;
    }
}
// this class overall is a simple data structure used to represent a user with a name, username and a password
