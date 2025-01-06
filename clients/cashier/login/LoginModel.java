package clients.cashier.login;

import at.favre.lib.crypto.bcrypt.BCrypt;
import clients.cashier.CashierController;
import clients.cashier.CashierModel;
import clients.cashier.CashierView;
import clients.customer.CustomerController;
import clients.customer.CustomerModel;
import clients.customer.CustomerView;
import clients.packing.PackingController;
import clients.packing.PackingModel;
import clients.packing.PackingView;
import clients.stockManager.StockManagerController;
import clients.stockManager.StockManagerModel;
import clients.stockManager.StockManagerView;
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
import java.util.Observer;

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
            model.checkPassword(hashedPass, password, view, targetView);
        } else {
            showErrorMessage(view);
            System.out.println("This User does not exist.");
        }
    }

    private void checkPassword(String bcryptHashString, String password, LoginView view, TargetView targetView) throws SQLException {
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
            openTargetView(targetView, view);
        }
    }

    private void openTargetView(TargetView enumVal, LoginView view) {
        switch (enumVal) {
            case CASHIER_VIEW:
                 displayCashierGUI(mlf);
                break;
            case CUSTOMER_VIEW:
                startCustomerGUI_MVC(mlf);
                break;
            case PACKING_VIEW:
                startPackingGUI_MVC(mlf);
                break;
            case STOCK_VIEW:
                startBackDoorGUI_MVC(mlf);
                break;
        }
        view.dispose(); // Close the login view
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

    public void startBackDoorGUI_MVC(MiddleFactory mlf)
    {
        //setting the window for the GUI
        JFrame  window = new JFrame();
        window.setTitle( "Stock Manager");
        window.setDefaultCloseOperation( JFrame.HIDE_ON_CLOSE );

        //creating the correct parameters for the CustomerView
        StockManagerModel model      = new StockManagerModel(mlf);
        StockManagerView view        = new StockManagerView( window, mlf, 400, 300 );
        StockManagerController cont  = new StockManagerController( model, view );
        view.setController( cont );


        //calling the position model for the window
        Positioning pos = new Positioning();
        pos.SetMonitorLocStock(window);

        model.addObserver( view );       // Add observer to the model
        window.setVisible(true);         // Make window visible
    }

}
