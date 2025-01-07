package clients.Register;

import login.ViewManager;

import java.sql.SQLException;

public class RegisterController {
    private RegisterModel model;
    private RegisterView view;
    public RegisterController(RegisterModel model, RegisterView view) {
        this.model = model;
        this.view = view;
        }

    public void startAddUser(String username, String password) throws SQLException {
        model.addUser(username, password, view);
    }
}
