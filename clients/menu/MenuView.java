package clients.menu;

import Utils.Styling;
import middle.LocalMiddleFactory;
import middle.MiddleFactory;

import javax.swing.*;
import java.awt.*;
import java.util.function.Predicate;

public class MenuView extends JFrame {
    private final JButton cashier;
    private final JButton customer;
    private final JButton packing;
    private final JButton catalogue;
    private final JButton exit;
    private final JButton StockClient;

    //set the parameters for the run GUI methods in controller
    MiddleFactory mlf = new LocalMiddleFactory();
    MenuController controller = new MenuController(this, mlf);


    public MenuView() {
        Styling styling = new Styling();
        //window set up
        setTitle("Menu");
        setSize(400, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 10)); // Dark gray border

        //layout and components
        JPanel ButtonPanel = new JPanel();
        ButtonPanel.setLayout(new GridLayout(3, 2, 10, 10));
        cashier = new JButton("Cashier Interface");
        customer = new JButton("Customer Interface");
        packing = new JButton("Packing Interface");
        exit = new JButton("Exit");
        catalogue = new JButton("Catalogue");
        StockClient = new JButton("Stock Client");

        //add the buttons
        ButtonPanel.add(cashier);
        ButtonPanel.add(customer);
        ButtonPanel.add(packing);
        ButtonPanel.add(catalogue);
        ButtonPanel.add(StockClient);
        ButtonPanel.add(exit);

        JPanel footerPanel = new JPanel();
        JLabel footerLabel = new JLabel("Version 2.4 Bargos");
        footerPanel.add(footerLabel);
        ButtonPanel.add(footerPanel);

        mainPanel.add(ButtonPanel, BorderLayout.CENTER);
        mainPanel.add(footerPanel, BorderLayout.SOUTH);

        //Styling
        Predicate<Component>  button = b -> b instanceof JButton;
        styling.styling(ButtonPanel, Color.GRAY, Color.BLACK, button);
        ButtonPanel.setBackground(Color.DARK_GRAY);

        add(mainPanel);

        //sort the starting location of the Menu in the centre of the screen
        Point p = this.getLocation();
        int xPos = (int) p.getX() - (this.getWidth() / 2);
        int yPos = (int) p.getY() - (this.getHeight() / 2);
        this.setLocation(xPos, yPos);

        //call the ActionListeners
        this.controller.runCashier(mlf);
        this.controller.runCustomer(mlf);
        this.controller.runPacking(mlf);
        this.controller.runExit();
        this.controller.runStockClient(mlf);
        this.controller.runCatalogue();
    }

    //set access to the buttons for the controller
    public JButton getCashierButton() {
        return cashier;
    }

    public JButton getCustomerButton() {
        return customer;
    }

    public JButton getPackingButton() {
        return packing;
    }

    public JButton getExitButton() {
        return exit;
    }

    public JButton getCatalogueButton() {
        return catalogue;
    }

    public JButton getBackDoorButton() {
        return StockClient;
    }

}
