package clients.menu;

import middle.LocalMiddleFactory;
import middle.MiddleFactory;
import Utils.Positioning;


public class MenuClient {

    public static void main(String[] args) {
        //run the setup
        Setup.runSetup();

        // Create the MainMenuView
        MenuView view = new MenuView();
        Positioning pos = new Positioning();
        MiddleFactory mlf = new LocalMiddleFactory();  // Direct access
        pos.SetMonitorStartUpMenu(view);
        // Create the MainMenuController and pass the view and args
        MenuController controller = new MenuController(view, mlf);

        // Display the main menu
        view.setVisible(true);
    }
}