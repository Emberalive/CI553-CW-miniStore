package clients.stockManager.Login;

import clients.Register.RegisterController;
import clients.Register.RegisterModel;
import clients.Register.RegisterView;
import middle.MiddleFactory;

import java.sql.SQLException;


public class StockLoginController {
    private StockLoginView view;
    private StockLoginModel model;
    private MiddleFactory mlf;

    public StockLoginController(StockLoginView view, StockLoginModel model, MiddleFactory mlf) {
        this.view = view;
        this.model = model;
        this.mlf = mlf;
    }

    public void startGetAddUser(String username, String password, StockLoginView view) throws SQLException {
        StockLoginModel model = new StockLoginModel();
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