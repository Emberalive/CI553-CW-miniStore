package login;

import Utils.Positioning;
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
import middle.MiddleFactory;
import javax.swing.*;

public class ViewManager {

    void displayCashierGUI(MiddleFactory mf, String username) {
        JFrame window = new JFrame();

        window.setTitle("Cashier Client: " + username);
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

    public void startCustomerGUI_MVC(MiddleFactory mlf, String username)
    {
        //setting the window for the GUI
        JFrame window = new JFrame();
        window.setTitle( "Customer Client: " + username );
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

    public void startPackingGUI_MVC(MiddleFactory mlf, String username)
    {
        //setting the window for the GUI
        JFrame  window = new JFrame();
        window.setTitle( "Packing Client: " + username);
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

    public void startBackDoorGUI_MVC(MiddleFactory mlf, String username)
    {
        //setting the window for the GUI
        JFrame  window = new JFrame();
        window.setTitle( "Stock Manager: " + username);
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
