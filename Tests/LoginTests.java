package Tests;

import at.favre.lib.crypto.bcrypt.BCrypt;
import clients.cashier.CashierView;
import login.LoginModel;
import login.LoginView;
import middle.LocalMiddleFactory;
import middle.MiddleFactory;
import org.junit.Test;
import javax.swing.*;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTests {

    //opens and closes a window, only because that's what the method does when its called
    @Test
    public void checkPassword_ValidPassword_OpensCashierView() throws SQLException {
        // Arrange: Set up the necessary objects for the test
        MiddleFactory mlf = new LocalMiddleFactory();
        String username = "username";
        String password = "password";
        String hashString = BCrypt.withDefaults().hashToString(12, password.toCharArray());

        // Subclassing JFrame to track visibility
        class TestJFrame extends JFrame {
            boolean visibilityCalled = false;

            @Override
            public void setVisible(boolean b) {
                //stating that when setVisible is called the value of true or false will be assigned to be
                super.setVisible(b);
                //this is assigning the value of b to a variable, so that we can use it in the test
                visibilityCalled = b;  // Track visibility change
            }
        }

        // Create an instance of TestJFrame, so that the subClass of JFrame will be used not the original one.
        TestJFrame testWindow = new TestJFrame();

        //making a local CashierView that uses TestJframe instead of JFrame so that i can check the visibility using the subclasses new setVisible method
        CashierView testView = new CashierView(testWindow, mlf, 0, 0);

        LoginModel model = new LoginModel();

        LoginView view = new LoginView(LoginModel.TargetView.CASHIER_VIEW);
        model.checkPassword(hashString, password, view, LoginModel.TargetView.CASHIER_VIEW, username);

        assertTrue(testWindow.visibilityCalled, "Cashier view should be visible after valid password.");
    }

}