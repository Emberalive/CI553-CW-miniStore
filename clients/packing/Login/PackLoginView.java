package clients.packing.Login;


import Utils.Styling;
import clients.packing.Login.PackLoginModel;
import middle.LocalMiddleFactory;
import middle.MiddleFactory;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.function.Predicate;

public class PackLoginView extends JFrame {
    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final JButton loginButton;
    private final JButton registerButton;
    MiddleFactory mlf = new LocalMiddleFactory();
    PackLoginModel model = new PackLoginModel();
    PackLoginController controller = new PackLoginController(this, model, mlf);

    public PackLoginView() {
        setTitle("Packing Login");
        setSize(400, 250);
        setDefaultCloseOperation(HIDE_ON_CLOSE);


        //layout and components
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.LIGHT_GRAY);

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
        mainPanel.add(usernameLabel);
        mainPanel.add(usernameField);
        mainPanel.add(passwordLabel);
        mainPanel.add(passwordField);
        mainPanel.add(loginButton);
        mainPanel.add(registerButton);

        add(mainPanel);

        Styling styling = new Styling();

        Predicate<Component> condition = component -> component instanceof JPanel || component instanceof JButton || component instanceof JLabel || component instanceof JTextField || component instanceof JPasswordField;
        styling.styling(mainPanel, Color.GRAY, Color.BLACK, condition);
    }
    public JButton getRegisterButton() {
        return registerButton;
    }

    // Setter for the controller
    public void setController(PackLoginController controller) {
        this.controller = controller;
    }

}
