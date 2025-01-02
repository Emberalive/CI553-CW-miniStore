package clients.customer.CustLogin;


import middle.MiddleFactory;

public class CustLoginClient {
    private CustLoginView view;
    private MiddleFactory mlf;
    private CustLoginModel model;

    public void StartLoginClient (String[] args) {
        // Create the necessary components
        CustLoginView view = new CustLoginView();
        CustLoginModel model = new CustLoginModel();

        // Create the controller and pass the view, model, and MiddleFactory
        CustLoginController controller = new CustLoginController(view, model, mlf);

        // Set the controller for the view
        view.setController(controller);
        // Show the login view
        view.setVisible(true);
    }
}
