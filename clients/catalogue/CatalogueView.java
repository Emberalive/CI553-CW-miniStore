package clients.catalogue;
import Utils.Positioning;
import Utils.Styling;
import catalogueInterface.Catalogue;
import catalogueInterface.CatalogueImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.function.Predicate;

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

        //adding a layout based on a card system
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        for (Product product : products) {
            mainPanel.add(createProductCard(product));
        }

        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED); // Show vertical scrollbar when needed
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        window.add(scrollPane);

        //styling
        Styling styling = new Styling();
        Predicate<Component> condition = component -> component instanceof JPanel || component instanceof JLabel;
        styling.styling(mainPanel, Color.LIGHT_GRAY, Color.BLACK, condition);

        //adding the components to the window
        window.setLayout(new BorderLayout());
        window.add(scrollPane, BorderLayout.CENTER);

        //calling the position method
        Positioning pos = new Positioning();
        pos.SetMonitorLocCatalogue(window);

//        window.add(productList);
        window.setVisible(true);
    }
    private JPanel createProductCard(Product product) {

        JPanel cardPanel = new JPanel();
        cardPanel.setPreferredSize(new Dimension(260, 100)); // Fixed size
        cardPanel.setMinimumSize(new Dimension(260, 100));
        cardPanel.setMaximumSize(new Dimension(260, 100));
        cardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));


        cardPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        //adding the label and setting the layout
        JLabel productNo = new JLabel("Product: " + product.getProdName());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5); // Add 5px padding around the label
        cardPanel.add(productNo, gbc);

        //adding a button and the layout
        JButton viewDetails = new JButton("View Details");
        viewDetails.setPreferredSize(new Dimension(150, 30));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 5, 5, 5); // Add top space for the button
        cardPanel.add(viewDetails, gbc);
        viewDetails.setBackground(Color.GRAY);
        viewDetails.setForeground(Color.BLACK);

        //adding an actionListener to the button
        viewDetails.addActionListener(e -> {
            JOptionPane.showMessageDialog(cardPanel,
                    "Details for "+ product.getProdName() +"\nProductNo: " +
                            product.getProductNum() + "\n" +
                            product.getDescription() + "\n" +
                            product.getPrice());
        });

        //adding a mouse interaction
        cardPanel.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                cardPanel.setBackground(Color.WHITE);
            }
            public void mouseExited(MouseEvent e) {
                cardPanel.setBackground(Color.LIGHT_GRAY);
            }
        });
        return cardPanel;
    }
}