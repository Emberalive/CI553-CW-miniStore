package clients.menu;

import Utils.Positioning;
import login.LoginModel;
import login.LoginView;
import clients.catalogue.CatalogueView;
import middle.MiddleFactory;


public class MenuController {
    MenuView view;

    public MenuController(MenuView view, MiddleFactory mlf) {
        this.view = view;
    }
    // Add action listeners for each button
    public void runLoginView(MiddleFactory mlf) {
        view.getCashierButton().addActionListener(e -> openLoginView(LoginModel.TargetView.CASHIER_VIEW));
        view.getBackDoorButton().addActionListener(e -> openLoginView(LoginModel.TargetView.STOCK_VIEW));
        view.getPackingButton().addActionListener(e -> openLoginView(LoginModel.TargetView.PACKING_VIEW));
    }

    private void openLoginView(LoginModel.TargetView targetView) {
        LoginView loginView = new LoginView(targetView);
        Positioning pos = new Positioning();
        pos.SetMonitorStartUpMenu(loginView);
        switch (targetView) {
            case CASHIER_VIEW:
                loginView.setTitle("Cashier Login");
                loginView.setVisible(true);
                break;
        case CUSTOMER_VIEW:
            loginView.setTitle("Customer Login");
            loginView.setVisible(true);
            break;
        case STOCK_VIEW:
            loginView.setTitle("Stock Login");
            loginView.setVisible(true);
            break;
        case PACKING_VIEW:
            loginView.setTitle("Packing Login");
            loginView.setVisible(true);
            break;
            default:
        }
    }

    public void runExit(){
        view.getExitButton().addActionListener(e -> System.exit(0));
    }

    public void runCatalogue(){
        view.getCatalogueButton().addActionListener(e -> {
            new CatalogueView().setVisible(true);
        });
    }
}