package login;

import clients.Register.RegisterController;
import clients.Register.RegisterModel;
import clients.Register.RegisterView;
import clients.cashier.CashierView;
import middle.MiddleFactory;

import java.sql.SQLException;


public class LoginController {
    private CashierView cashierView;
    private LoginView view;
    private LoginModel model;
    private MiddleFactory mlf;

    public LoginController(LoginView view, LoginModel model, MiddleFactory mlf) {
        this.view = view;
        this.model = model;
        this.mlf = mlf;
    }

    public void startGetAUser(String username, String password, LoginModel.TargetView enumVal) throws SQLException {
        LoginModel model = new LoginModel();
        model.getAUser(username, password, view, enumVal);
    }

    public void handleRegisteration() {
        this.view.getRegisterButton().addActionListener(e -> {
            ViewManager manager = new ViewManager();
            manager.startRegisterGUI();
        });
    }
}