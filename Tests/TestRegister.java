package Tests;

import clients.Register.RegisterModel;
import clients.Register.RegisterView;
import clients.menu.Setup;
import dbAccess.DBAccess;
import dbAccess.DBAccessFactory;
import org.junit.Test;

import java.sql.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestRegister {

    @Test
    public void testAddUser() throws Exception {
        System.setProperty("disableJOptionPane", "true");  // Prevent showing the dialog during the test
        RegisterModel model = new RegisterModel();
        RegisterView view = new RegisterView();
        String username = "test";
        String password = "test";

        Connection connection = null;

            DBAccess dbDriver = (new DBAccessFactory()).getNewDBAccess();
            dbDriver.loadDriver();
            connection = DriverManager.getConnection(
                    dbDriver.urlOfDatabase(),
                    dbDriver.username(),
                    dbDriver.password()
            );

            // Disable autocommit to control the transaction manually
            connection.setAutoCommit(false);

            // Add the user using the model
            model.addUser(username, password, view);

            // Commit the transaction so changes are visible to other queries
            connection.commit();

            // Query the database to verify the user is inserted
            PreparedStatement query = connection.prepareStatement("SELECT * FROM EmplTable WHERE username = ?");
            query.setString(1, username);
            ResultSet rs = query.executeQuery();

            // Check if the user exists
            assertEquals("The username should match the inserted value.", rs.getString("username").trim(), username);
    }


}
