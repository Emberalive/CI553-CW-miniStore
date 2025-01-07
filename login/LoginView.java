package login;


import Utils.Styling;
import clients.Picture;
import middle.LocalMiddleFactory;
import middle.MiddleFactory;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.function.Predicate;

public class LoginView extends JFrame {
    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final JButton loginButton;
    private final JButton registerButton;
    MiddleFactory mlf = new LocalMiddleFactory();
    LoginModel model = new LoginModel();
    LoginController controller = new LoginController(this, model, mlf);


    public LoginView(LoginModel.TargetView targetView) {
        //setting up the window
        setSize(400, 250);
        setDefaultCloseOperation(HIDE_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.LIGHT_GRAY);

        //adding the username fields
        JLabel usernameLabel = new JLabel("Username");
        usernameField = new JTextField();

        //adding the password fields
        JLabel passwordLabel = new JLabel("Password");
        passwordField = new JPasswordField();

        //adding the buttons
        loginButton = new JButton("login");
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
                controller.startGetAUser(username, password, targetView);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        //calling the button methods in the Controller
        this.controller.handleRegisteration();

        //Making sure the changes are visible
        mainPanel.revalidate();
        mainPanel.repaint();

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
    public void setController(LoginController controller) {
        this.controller = controller;
    }
}
