package clients.cashier.Login;

import middle.MiddleFactory;

public class LoginClient {
    private LoginView view;
    private MiddleFactory mlf;
    private LoginModel model;

    public void StartLoginClient (String[] args) {
        // Create the necessary components
        LoginView LoginView = new LoginView();
        LoginModel LoginModel = new LoginModel();

        // Create the controller and pass the view, model, and MiddleFactory
        LoginController LoginController = new LoginController(LoginView, LoginModel, mlf);

        // Set the controller for the view
        LoginView.setController(LoginController);
        // Show the login view
        LoginView.setVisible(true);
    }
}
