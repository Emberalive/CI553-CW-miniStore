package clients.customer.CustLogin;


import at.favre.lib.crypto.bcrypt.BCrypt;
import clients.cashier.CashierController;
import clients.cashier.CashierModel;
import clients.cashier.CashierView;
import clients.customer.CustomerController;
import clients.customer.CustomerModel;
import clients.customer.CustomerView;
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


public class CustLoginModel extends Component {
    private static Connection connection;
    MiddleFactory mlf = new LocalMiddleFactory();
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

    public void getAUser(String user, String password, CustLoginView view) throws SQLException {
        LoginDBAccess();
        String hashedPass;
        CustLoginModel model = new CustLoginModel();

        PreparedStatement query = connection.prepareStatement("SELECT HashedPass, username FROM EmplTable WHERE username = ?");
        query.setString(1, user);
        ResultSet rs = query.executeQuery();
        if (rs.next()) {
            hashedPass = rs.getString("HashedPass");
            model.checkPassword(hashedPass, password, view);
        } else {
            showErrorMessage(view);
        }
    }

    private void checkPassword (String bcryptHashString, String password, CustLoginView view) {
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
            view.setVisible(false);
            startCustomerGUI_MVC(mlf);
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

    public void startCustomerGUI_MVC(MiddleFactory mlf )
    {
        //setting the window for the GUI
        JFrame window = new JFrame();
        window.setTitle( "Customer Client");
        window.setDefaultCloseOperation( JFrame.HIDE_ON_CLOSE);

        //creating the correct parameters for the CustomerView
        CustomerModel model      = new CustomerModel(mlf);
        CustomerView view        = new CustomerView( window, mlf, 400, 300 );
        CustomerController cont  = new CustomerController( model, view );
        view.setController( cont );

        //calling the position model for the window
        Positioning pos = new Positioning();

        pos.SetMonLocCustomer(window);
        model.addObserver( view );
        window.setVisible(true);
    }
}
