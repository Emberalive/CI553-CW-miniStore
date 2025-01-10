package Tests;


import dbAccess.DBAccess;
import dbAccess.DBAccessFactory;
import login.LoginModel;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestMyDBAccess {
    private Connection connection;


    //testing that my DBAccess methods actually access the database. I inserted values into the table as there is none in there, and tested if there is a result set based on the select query
    @Test
    public void checkDBAccess() throws SQLException {
        LoginModel model = new LoginModel();

        //setting the database connection
        try {
            DBAccess dbDriver = (new DBAccessFactory()).getNewDBAccess(); //creates a new databaseAccess
            dbDriver.loadDriver(); //loads the dbdrivers
            connection = java.sql.DriverManager.getConnection( //gets a connection to the sql through the drivers
                    dbDriver.urlOfDatabase(), //allows access to where the db is stored
                    dbDriver.username(),  //gets the username for bdAccess
                    dbDriver.password()   //gets the password for dbAccess
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to connect to the database.", e);
        }
        //adding an employee to the database so i can run a select query
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO EmplTable (username, HashedPass) VALUES (?, ?), (?, ?)");
        preparedStatement.setString(1, "Sam");
        preparedStatement.setString(2, "Password");
        preparedStatement.executeUpdate();

        //running the select query
        String username = "Sam";
        PreparedStatement query = connection.prepareStatement("SELECT * FROM EmplTable WHERE username = ?");
        query.setString(1, username);

        ResultSet rs = query.executeQuery();
        //testing if there is a result set
        assertTrue(rs.next(), "connection should be valid to make a query");
    }
}
