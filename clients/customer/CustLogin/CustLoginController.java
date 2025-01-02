package clients.customer.CustLogin;

import clients.Register.RegisterController;
import clients.Register.RegisterModel;
import clients.Register.RegisterView;
import clients.cashier.CashierView;
import clients.customer.CustLogin.CustLoginModel;
import clients.customer.CustLogin.CustLoginView;
import middle.MiddleFactory;

import java.sql.SQLException;


public class CustLoginController {
    private CashierView cashierView;
    private CustLoginView view;
    private CustLoginModel model;
    private MiddleFactory mlf;

    public CustLoginController(CustLoginView view, CustLoginModel model, MiddleFactory mlf) {
        this.view = view;
        this.model = model;
        this.mlf = mlf;
    }

    public void startGetAddUser(String username, String password, CustLoginView view) throws SQLException {
        CustLoginModel model = new CustLoginModel();
        model.getAUser(username, password, view);
    }

    public void handleRegisteration() {
        this.view.getRegisterButton().addActionListener(e -> {
            RegisterModel model = new RegisterModel();
            RegisterView view = new RegisterView();
            RegisterController controller = new RegisterController(model, view);
            controller.startGUI();
        });
    }

}