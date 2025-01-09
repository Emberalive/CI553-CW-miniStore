//package Tests;
//
//import clients.Register.RegisterModel;
//import clients.Register.RegisterView;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//import javax.swing.*;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//import static org.mockito.Mockito.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class RegisterModelTests {
//
//    @Test
//    public void addUser_SQLExceptionHandling() throws SQLException {
//        // Mock dependencies
//        private Connection mockConnection;
//        PreparedStatement mockPreparedStatement = null;
//        RegisterView mockView = null;
//
//        // Mock the behavior of prepareStatement to return the mock PreparedStatement
//
////        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
//
//        // Simulate SQLException when executeUpdate is called on PreparedStatement
////        doThrow(new SQLException("Database error")).when(mockPreparedStatement).executeUpdate();
//
//        // Create an instance of RegisterModel
//        RegisterModel model = new RegisterModel();
//
//        // Inject the mocked connection into the model's LoginDBAccess method
//        model.LoginDBAccess(mockConnection);
//
//        String username = "username";
//        String password = "password";
//
//        // Call the method under test
//        model.addUser(username, password, mockView);
//
//        // Verify that setVisible(false) was called on the view after the exception
//        verify(mockView).setVisible(false);
//
//        // Optionally, you can verify that the error message was displayed using JOptionPane (if needed)
//        // verify(mockView).setVisible(false);
//        // verify(mockView).showMessageDialog(eq(mockView), anyString()); // Or a similar mock for message dialog
//    }
//
//
//}
