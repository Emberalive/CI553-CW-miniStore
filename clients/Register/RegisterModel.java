package clients.Register;

import Utils.Positioning;
import at.favre.lib.crypto.bcrypt.BCrypt;
import dbAccess.DBAccess;
import dbAccess.DBAccessFactory;

import java.sql.*;

public class RegisterModel {
    private static Connection connection;

 public void displayRegisterGUI(){
     RegisterModel model = new RegisterModel();
     RegisterView view = new RegisterView();
     RegisterController controller = new RegisterController(model, view);

     Positioning pos = new Positioning();

     pos.SetMonLocCashier(view);
     view.setVisible(true);
}


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

    public void addUser(String username, String password, RegisterView view) throws SQLException {
        LoginDBAccess();
        String hashedPass;
        hashedPass = (String) HashPass(password);
        PreparedStatement query;
        query = connection.prepareStatement("INSERT INTO UserTable (userName, Password, HashedPass) VALUES (? , ?, ?)");
        query.setString(1, username);
        query.setString(2, password);
        query.setString(3, hashedPass);
        query.executeUpdate();
        if (query.getUpdateCount() == 1) {
            System.out.println("User " + username + " added to the database.");
            view.setVisible(false);
        } else {
            System.out.println("User was not added to the database.");
        }
    }


    public Object HashPass (String password){
        String HashString = BCrypt.withDefaults().hashToString(12, password.toCharArray());
        return HashString;
    }
}
