package catalogueInterface;

import clients.catalogue.Product;

import java.sql.*;
import java.util.List;

import dbAccess.DBAccess;
import dbAccess.DBAccessFactory;

import java.util.ArrayList;

public class CatalogueImpl implements Catalogue {
    private final Connection connection;

    public CatalogueImpl() { //gives access to the database
        try {
            DBAccess dbDriver = (new DBAccessFactory()).getNewDBAccess(); //creates a new databaseAccess
            dbDriver.loadDriver(); //loads the drivers
            connection = java.sql.DriverManager.getConnection( //gets a connection to the sql through the drivers
                    dbDriver.urlOfDatabase(), //allows access to where the db is stored
                    dbDriver.username(),  //gets the username for bdAccess
                    dbDriver.password()   //gets the password for dbAccess
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to connect to the database.", e);
        }
    }

    @Override
    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();   //stores all the products selected through the select query
        //setting and executing the query
        PreparedStatement query;
        query = connection.prepareStatement("SELECT p.productNo, p.description, p.price, s.stockLevel " +    //using PreparedStatements as having a String leaves
                "FROM ProductTable p " +
                "LEFT JOIN StockTable s ON p.productNo = s.productNo");
        query.execute();
        ResultSet rs = query.executeQuery();
        while (rs.next()) {
            //initialising where all the product info will be stored before adding it to the Array
            String productNo = rs.getString("productNo");
            String description = rs.getString("description");
            double price = rs.getDouble("price");
            int stockLevel = rs.getInt("stockLevel");

            products.add(new Product(productNo, description, price, stockLevel)); //adding the product info to the Array
        }
        return products;
    }
}