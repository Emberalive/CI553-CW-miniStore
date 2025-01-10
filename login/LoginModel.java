package login;

import at.favre.lib.crypto.bcrypt.BCrypt;

import dbAccess.DBAccess;
import dbAccess.DBAccessFactory;
import middle.*;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel extends Component {
    private static Connection connection;
    MiddleFactory mlf = new LocalMiddleFactory();


    public enum TargetView {
        CASHIER_VIEW,
        CUSTOMER_VIEW,
        STOCK_VIEW,
        PACKING_VIEW;
    }

    public static void LoginDBAccess() { //gives access to the database
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

    public void getAUser(String user, String password, LoginView view, TargetView targetView) throws SQLException {
        LoginDBAccess();
        String hashedPass;
        LoginModel model = new LoginModel();

        PreparedStatement query = connection.prepareStatement("SELECT HashedPass, username FROM EmplTable WHERE username = ?");
        query.setString(1, user);
        ResultSet rs = query.executeQuery();
        if (rs.next()) {
            hashedPass = rs.getString("HashedPass");
            model.checkPassword(hashedPass, password, view, targetView, user);
        } else {
            JOptionPane.showMessageDialog(view, "This user does not exist");
            JOptionPane.showMessageDialog(view,
                    "There is no employee with these credentials");
            System.out.println("This User does not exist.");
        }
    }

    public void checkPassword(String bcryptHashString, String password, LoginView view, TargetView targetView, String username) throws SQLException {
        if (bcryptHashString == null) {
            if (view == null) {
                System.out.println("View is null!");  // Debugging
                return;
            }
            showErrorMessage(view);
            return;
        }
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), bcryptHashString);
        if (result.verified) {
            System.out.println("Password verified!");
            openTargetView(targetView, view, username);
        }
    }

    private void openTargetView(TargetView enumVal, LoginView view, String username) {
        ViewManager manager = new ViewManager();
        switch (enumVal) {
            case CASHIER_VIEW:
                 manager.displayCashierGUI(mlf, username);
                break;
            case CUSTOMER_VIEW:
                manager.startCustomerGUI_MVC(mlf, username);
                break;
            case PACKING_VIEW:
                manager.startPackingGUI_MVC(mlf, username);
                break;
            case STOCK_VIEW:
                manager.startBackDoorGUI_MVC(mlf, username);
                break;
        }
        view.dispose(); // Close the login view
    }

    public static void showErrorMessage(JFrame parentFrame) {
        // Display an error message in a pop-up
        JOptionPane.showMessageDialog(parentFrame,
                "An error has occurred.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }
}
