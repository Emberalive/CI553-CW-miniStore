package clients.customer.CustLogin;


import clients.customer.CustLogin.CustLoginController;
import middle.LocalMiddleFactory;
import middle.MiddleFactory;

import javax.swing.*;
import java.sql.SQLException;

public class CustLoginView extends JFrame {
    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final JButton loginButton;
    private final JButton registerButton;
    MiddleFactory mlf = new LocalMiddleFactory();
    CustLoginModel model = new CustLoginModel();
    CustLoginController controller = new CustLoginController(this, model, mlf);

    public CustLoginView() {
        setTitle("Customer Login");
        setSize(400, 250);
        setDefaultCloseOperation(HIDE_ON_CLOSE);


        //layout and components
        setLayout(null);

        //adding the username label
        JLabel usernameLabel = new JLabel("Username");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password");
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");

        usernameLabel.setBounds(50, 50, 100, 30); // x, y, width, height
        usernameField.setBounds(150, 50, 200, 30); // x, y, width, height

        passwordLabel.setBounds(50, 100, 100, 30); // x, y, width, height
        passwordField.setBounds(150, 100, 200, 30); // x, y, width, height

        loginButton.setBounds(100, 150, 100, 30);
        registerButton.setBounds(225, 150, 100, 30);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword()); // Ensure you use the correct method to get password
            try {
                controller.startGetAddUser(username, password, this);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println(username + " " + password);
        });

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
    public void setController(CustLoginController controller) {
        this.controller = controller;
    }

}
