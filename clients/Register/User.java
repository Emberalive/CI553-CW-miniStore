package clients.Register;

public class User {
    private String theUsername;
    private String thePassword;
    private int TheHashedPassword;

    public User(String aUsername, String aPassword, int aHashedPassword) {
        theUsername = aUsername;
        thePassword = aPassword;
        TheHashedPassword = aHashedPassword;
    }
    public String getUsername() {
        return theUsername;
    }
    public String getPassword() {
        return thePassword;
    }
    public int getHashedPassword() {
        return TheHashedPassword;
    }
}
