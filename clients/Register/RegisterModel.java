package clients.Register;

import Utils.Positioning;
import at.favre.lib.crypto.bcrypt.BCrypt;
import dbAccess.DBAccess;
import dbAccess.DBAccessFactory;

import javax.sound.midi.SysexMessage;
import javax.swing.*;
import java.sql.*;

public class RegisterModel {
    private static Connection connection;

    public static void LoginDBAccess()  { //gives access to the database
        try {
            DBAccess dbDriver = (new DBAccessFactory()).getNewDBAccess(); //creates a new databaseAccess
            dbDriver.loadDriver(); //loads the dbdrivers
            connection = java.sql.DriverManager.getConnection( //gets a connection to the sql through the drivers
                    dbDriver.urlOfDatabase(), //allows access to where the db is stored
                    dbDriver.username(),  //gets the username for bdAccess
                    dbDriver.password()   //gets the password for dbAccess
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to connect to the database.", e);
        }
    }

    public void addUser(String username, String password, RegisterView view) {
        LoginDBAccess();
        String hashedPass;
        hashedPass = (String) HashPass(password);
        try (PreparedStatement query = connection.prepareStatement("INSERT INTO EmplTable (userName, HashedPass) VALUES (? , ?)")) {
            query.setString(1, username);
            query.setString(2, hashedPass);
            query.executeUpdate();
        }catch (SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
            e.printStackTrace();

            JOptionPane.showMessageDialog(view,
                    "Failed to add user: " + username);
        }
        if (System.getProperty("disableJOptionPane") == null) {
            JOptionPane.showMessageDialog(view,
                    "Successfully added user: " + username);
        }
        view.setVisible(false);
    }

    public Object HashPass (String password){
        String HashString = BCrypt.withDefaults().hashToString(12, password.toCharArray());
        return HashString;
    }
}
