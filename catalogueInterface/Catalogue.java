package catalogueInterface;

import clients.catalogue.Product;
import java.sql.SQLException;
import java.util.List;

public interface Catalogue {
    List<Product> getAllProducts() throws SQLException;  // Retrieve all products
}