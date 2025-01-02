package clients.cashier.Login;


import middle.LocalMiddleFactory;
import middle.MiddleFactory;

import javax.swing.*;
import java.sql.SQLException;

public class LoginView extends JFrame {
    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final JButton loginButton;
    private final JButton registerButton;
    MiddleFactory mlf = new LocalMiddleFactory();
    LoginModel model = new LoginModel();
    LoginController controller = new LoginController(this, model, mlf);

    public LoginView() {
        //setting up the window
        setTitle("Cashier Login");
        setSize(400, 250);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        //layout and components
        setLayout(null);

        //adding the username fields
        JLabel usernameLabel = new JLabel("Username");
        usernameField = new JTextField();

        //adding the password fields
        JLabel passwordLabel = new JLabel("Password");
        passwordField = new JPasswordField();

        //adding the buttons
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");

        //setting the placement of all the fields
        usernameLabel.setBounds(50, 50, 100, 30); // x, y, width, height
        usernameField.setBounds(150, 50, 200, 30); // x, y, width, height

        passwordLabel.setBounds(50, 100, 100, 30); // x, y, width, height
        passwordField.setBounds(150, 100, 200, 30); // x, y, width, height

        loginButton.setBounds(100, 150, 100, 30);
        registerButton.setBounds(225, 150, 100, 30);

        //login button action listener, not in the controller because I need to get the username and password values
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword()); // Ensure you use the correct method to get password
            try {
                controller.startGetAddUser(username, password, this);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        //calling the button methods in the Controller
        this.controller.handleRegisteration();

        //adding the components
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);
        add(registerButton);
    }

    public JButton getRegisterButton() {
        return registerButton;
    }

    // Setter for the controller
    public void setController(LoginController controller) {
        this.controller = controller;
    }
}
