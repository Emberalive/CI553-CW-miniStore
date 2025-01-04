package clients.packing.Login;


import at.favre.lib.crypto.bcrypt.BCrypt;
import clients.cashier.CashierController;
import clients.cashier.CashierModel;
import clients.cashier.CashierView;
import clients.packing.PackingController;
import clients.packing.PackingModel;
import clients.packing.PackingView;
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


public class PackLoginModel extends Component {
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

    public void getAUser(String user, String password, PackLoginView view) throws SQLException {
        LoginDBAccess();
        String hashedPass;
        PackLoginModel model = new PackLoginModel();

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

    private void checkPassword (String bcryptHashString, String password, PackLoginView view) {
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
            startPackingGUI_MVC(mlf);
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

    public void startPackingGUI_MVC(MiddleFactory mlf)
    {
        //setting the window for the GUI
        JFrame  window = new JFrame();
        window.setTitle( "Packing Client");
        window.setDefaultCloseOperation( JFrame.HIDE_ON_CLOSE );

        //creating the correct parameters for the CustomerView
        PackingModel model      = new PackingModel(mlf);
        PackingView view        = new PackingView( window, mlf, 400, 300 );
        PackingController cont  = new PackingController( model, view );
        view.setController( cont );


        //calling the position model for the window
        Positioning pos = new Positioning();
        pos.SetMonitorLocPacking(window);

        model.addObserver( view );       // Add observer to the model
        window.setVisible(true);         // Make window visible
    }
}
