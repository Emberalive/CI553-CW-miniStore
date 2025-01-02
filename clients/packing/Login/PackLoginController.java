package clients.packing.Login;

import clients.Register.RegisterController;
import clients.Register.RegisterModel;
import clients.Register.RegisterView;
import middle.MiddleFactory;

import java.sql.SQLException;


public class PackLoginController {
    private PackLoginView view;
    private PackLoginController controller;
    private PackLoginModel model;
    private MiddleFactory mlf;

    public PackLoginController(PackLoginView view, PackLoginModel model, MiddleFactory mlf) {
        this.view = view;
        this.model = model;
        this.mlf = mlf;
    }

    public void startGetAddUser(String username, String password, PackLoginView view) throws SQLException {
        PackLoginModel model = new PackLoginModel();
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