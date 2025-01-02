package clients.packing.Login;

import clients.packing.Login.PackLoginController;
import clients.packing.Login.PackLoginView;
import middle.MiddleFactory;

public class PackLoginClient {
    private PackLoginView view;
    private MiddleFactory mlf;
    private PackLoginModel model;

    public void StartLoginClient (String[] args) {
        // Create the necessary components
        PackLoginView PackLoginView = new PackLoginView();
        PackLoginModel PackLoginModel = new PackLoginModel();

        // Create the controller and pass the view, model, and MiddleFactory
        PackLoginController PackLoginController = new PackLoginController (view, model, mlf);

        // Set the controller for the view
        PackLoginView.setController(PackLoginController);
        // Show the login view
        PackLoginView.setVisible(true);
    }
}
