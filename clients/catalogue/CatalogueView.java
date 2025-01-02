package clients.catalogue;
import Utils.Positioning;
import catalogueInterface.Catalogue;
import catalogueInterface.CatalogueImpl;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CatalogueView extends Component {

    public CatalogueView(){
        //creating the window
        JFrame window = new JFrame("Catalogue");
        window.setSize(260, 500);
        window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        //layout and components
        Catalogue catalogue = null;
        ArrayList<Product> products = null;
        try {
            catalogue = new CatalogueImpl();
            products = new ArrayList<>(catalogue.getAllProducts());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        JTextArea productList = new JTextArea(30, 30);
        productList.setEditable(false);

        //formatting text display
        productList.setLineWrap(true);
        productList.setWrapStyleWord(true);

        // adding a scrollPane
        JScrollPane catScroll = new JScrollPane();
        catScroll.setBounds(0, 0 ,370, 670 );// Scrolling pane
        //setting the text for the window
        String text = getProductDetails(products);
        productList.setText(text); // Set product details

        System.out.println("Setting text: " + "\n" + text); // Debug output
        //making the window scrollable
        catScroll.getViewport().add(productList);

        //calling the position method
        Positioning pos = new Positioning();
        pos.SetMonitorLocCatalogue(window);

        window.add(productList);
        window.setVisible(true);
    }

    public String getProductDetails(ArrayList<Product> products){
        StringBuilder sb = new StringBuilder(); //using a StringBuilder to format the text
        for (Product product : products) {
            //Append the product details to one string
            sb.append("Product NO: ").append(product.getProductNum()).append("\n");
            sb.append("Description: ").append(product.getDescription()).append("\n");
            sb.append("Price: ").append(product.getPrice()).append("\n");
            sb.append("-----------------------------------------------------\n");  // Separator for each product
        }
        return sb.toString();
    }
}
