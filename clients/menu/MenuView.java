package clients.menu;

import middle.LocalMiddleFactory;
import middle.MiddleFactory;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;


public class MenuView extends JFrame {
    private final JButton cashier;
    private final JButton customer;
    private final JButton packing;
    private final JButton catalogue;
    private final JButton exit;
    private final JButton StockClient;

    //set the parameters for the run GUI methods in controller
    MenuModel model = new MenuModel();
    MiddleFactory mlf = new LocalMiddleFactory();
    MenuController controller = new MenuController(this, mlf, model);


    public MenuView() {
        super();
        //window set up
        setTitle("Menu");
        setSize(400, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //layout and components
        setLayout(new GridLayout(4, 1));
        cashier = new JButton("Cashier Interface");
        customer = new JButton("Customer Interface");
        packing = new JButton("Packing Interface");
        exit = new JButton("Exit");
        catalogue = new JButton("Catalogue");
        StockClient = new JButton("Stock Client");

        //add the buttons
        add(cashier);
        add(customer);
        add(packing);
        add(catalogue);
        add(StockClient);
        add(exit);

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
