package Tests;

import catalogueInterface.Catalogue;
import catalogueInterface.CatalogueImpl;
import clients.catalogue.Product;
import clients.menu.Setup;
import dbAccess.DBAccess;
import dbAccess.DBAccessFactory;
import dbAccess.StockR;
import middle.StockException;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class CatalogueTest {
    @Test
    public void testCatalogue() throws StockException, SQLException {
        StockR stock = new StockR();
        Setup setup = new Setup();
        Setup.runSetup();

        Connection connection = null;

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

        connection.setAutoCommit(false);

        //accessing the catalogue method
        Catalogue catalogue = null;
        ArrayList<Product> products = null;
        try {
            catalogue = new CatalogueImpl();
            products = new ArrayList<>(catalogue.getAllProducts());
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
            e.printStackTrace();
        }

        connection.commit();

        // Check first product (40 inch LED HD TV)
        assertEquals("0001", products.get(0).getProductNum());
        assertEquals("40 inch LED HD TV", products.get(0).getDescription());
        assertEquals("images/pic0001.jpg", stock.getImageString(products.get(0).getProductNum()));
        assertEquals(269.00, products.get(0).getPrice(), 0.01); //needed a delta that tells the method how much tolerance is needed when comparing the two numbers
        assertEquals("Television", products.get(0).getProdName());

// Check second product (DAB Radio)
        assertEquals("0002", products.get(1).getProductNum());
        assertEquals("DAB Radio", products.get(1).getDescription());
        assertEquals("images/pic0002.jpg", stock.getImageString(products.get(1).getProductNum()));
        assertEquals(29.99, products.get(1).getPrice(), 0.01);
        assertEquals("Radio", products.get(1).getProdName());

// Check third product (4 Slot Toaster)
        assertEquals("0003", products.get(2).getProductNum());
        assertEquals("4 Slot Toaster", products.get(2).getDescription());
        assertEquals("images/pic0003.jpg", stock.getImageString(products.get(2).getProductNum()));
        assertEquals(19.99, products.get(2).getPrice(), 0.01);
        assertEquals("Toaster", products.get(2).getProdName());

// Check fourth product (Silver Mechanical Watch)
        assertEquals("0004", products.get(3).getProductNum());
        assertEquals("Silver Mechanical Watch", products.get(3).getDescription());
        assertEquals("images/pic0004.jpg", stock.getImageString(products.get(3).getProductNum()));
        assertEquals(29.99, products.get(3).getPrice(), 0.01);
        assertEquals("Watch", products.get(3).getProdName());

// Check fifth product (Sony Digital Camera)
        assertEquals("0005", products.get(4).getProductNum());
        assertEquals("Sony Digital Camera", products.get(4).getDescription());
        assertEquals("images/pic0005.jpg", stock.getImageString(products.get(4).getProductNum()));
        assertEquals(89.99, products.get(4).getPrice(), 0.01);
        assertEquals("DSLR", products.get(4).getProdName());

// Check sixth product (Sony MP3 player)
        assertEquals("0006", products.get(5).getProductNum());
        assertEquals("Sony MP3 player", products.get(5).getDescription());
        assertEquals("images/pic0006.jpg", stock.getImageString(products.get(5).getProductNum()));
        assertEquals(7.99, products.get(5).getPrice(), 0.01);
        assertEquals("MP3", products.get(5).getProdName());

// Check seventh product (32Gb USB2 drive)
        assertEquals("0007", products.get(6).getProductNum());
        assertEquals("32Gb USB2 drive", products.get(6).getDescription());
        assertEquals("images/pic0007.jpg", stock.getImageString(products.get(6).getProductNum()));
        assertEquals(6.99, products.get(6).getPrice(), 0.01);
        assertEquals("USB", products.get(6).getProdName());
    }
}
