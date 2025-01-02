package clients.backDoor.Login;

import clients.backDoor.Login.StockLoginView;
import clients.backDoor.Login.StockLoginController;
import clients.backDoor.Login.StockLoginModel;
import middle.MiddleFactory;

public class StockLoginClient {
    private StockLoginView view;
    private MiddleFactory mlf;
    private StockLoginModel model;

    public void StartStockLogin (String[] args) {
        // Create the necessary components
        StockLoginView view = new StockLoginView();
        StockLoginModel model = new StockLoginModel();

        // Create the controller and pass the view, model, and MiddleFactory
        StockLoginController stockLoginController = new StockLoginController(view, model, mlf);

        // Set the controller for the view
        StockLoginView.setController(stockLoginController);
        // Show the login view
        view.setVisible(true);
    }
}
