package clients.Register;

import Utils.Styling;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.function.Predicate;

public class RegisterView extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    RegisterModel model = new RegisterModel();
    RegisterController controller;

    public RegisterView() {
        setTitle("Register Account");
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
        JButton register = new JButton("Create Account");


        usernameLabel.setBounds(50, 50, 100, 30); // x, y, width, height
        usernameField.setBounds(150, 50, 200, 30); // x, y, width, height

        passwordLabel.setBounds(50, 100, 100, 30); // x, y, width, height
        passwordField.setBounds(150, 100, 200, 30); // x, y, width, height

        register.setBounds(150, 150, 150, 30);

        //adding the components
        mainPanel.add(usernameLabel);
        mainPanel.add(usernameField);
        mainPanel.add(passwordLabel);
        mainPanel.add(passwordField);
        mainPanel.add(register);

        add(mainPanel);

        controller = new RegisterController(model, this);

        register.addActionListener(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            try {
                controller.startAddUser(username, password);
            } catch (SQLException ex) {
                System.out.println("Error adding User: " + ex.getMessage());
                ex.printStackTrace();
            }
        });

        Styling styling = new Styling();

        Predicate<Component> condition = component -> component instanceof JPanel || component instanceof JButton || component instanceof JLabel || component instanceof JTextField || component instanceof JPasswordField;
        styling.styling(mainPanel, Color.GRAY, Color.BLACK, condition);

    }
}
