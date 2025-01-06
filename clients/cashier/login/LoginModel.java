package clients.cashier.Login;


import at.favre.lib.crypto.bcrypt.BCrypt;
import clients.cashier.CashierController;
import clients.cashier.CashierModel;
import clients.cashier.CashierView;
import dbAccess.DBAccess;
import dbAccess.DBAccessFactory;
import middle.*;
import Utils.Positioning;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginModel extends Component {
    private static Connection connection;
    MiddleFactory mlf = new LocalMiddleFactory();

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

    public void getAUser(String user, String password, LoginView view) throws SQLException {
        LoginDBAccess();
        String hashedPass;
        LoginModel model = new LoginModel();

        PreparedStatement query = connection.prepareStatement("SELECT HashedPass, username FROM EmplTable WHERE username = ?");
        query.setString(1, user);
        ResultSet rs = query.executeQuery();
        if (rs.next()) {
            hashedPass = rs.getString("HashedPass");
            model.checkPassword(hashedPass, password, view);
        } else {
            showErrorMessage(view);
            System.out.println("This User does not exist.");
        }
    }

    private void checkPassword(String bcryptHashString, String password, LoginView view) {
        if (bcryptHashString == null) {
            if (view == null) {
                System.out.println("View is null!");  // Debugging
                return;
            }
            showErrorMessage(view);

        }
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), bcryptHashString);
        if (result.verified) {
            System.out.println("Password verified!");
            displayCashierGUI(mlf);
            view.setVisible(false);
        }
    }
//    private void checkPassword (String bcryptHashString, String password, LoginView view) {
//        if (bcryptHashString == null) {
//            if (view == null) {
//                System.out.println("View is null!");  // Debugging
//                return;
//            }
//            showErrorMessage(view);
//
//        }
//        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), bcryptHashString);
//        if (result.verified) {
//            System.out.println("Password verified!");
//            displayCashierGUI(mlf);
//            view.setVisible(false);
//        }
//    }


    public static void showErrorMessage(JFrame parentFrame) {
        // Display an error message in a pop-up
        JOptionPane.showMessageDialog(parentFrame,
                "An error has occurred.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    void displayCashierGUI(MiddleFactory mf) {
        JFrame window = new JFrame();

        window.setTitle("Cashier Client");
        window.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        CashierModel model = new CashierModel(mf);
        CashierView view = new CashierView(window, mf, 0, 0);
        CashierController cont = new CashierController(model, view);
        view.setController(cont);
        Positioning pos = new Positioning();

        pos.SetMonLocCashier(window);

        model.addObserver(view);       // Add observer to the model
        window.setVisible(true);         // Display Screen
        model.askForUpdate();
    }
}
