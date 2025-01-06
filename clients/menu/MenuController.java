package clients.menu;

import Utils.Positioning;
import clients.cashier.login.LoginModel;
import clients.cashier.login.LoginView;
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
        view.getCustomerButton().addActionListener(e -> openLoginView(LoginModel.TargetView.CUSTOMER_VIEW));
        view.getBackDoorButton().addActionListener(e -> openLoginView(LoginModel.TargetView.STOCK_VIEW));
        view.getPackingButton().addActionListener(e -> openLoginView(LoginModel.TargetView.PACKING_VIEW));
    }

    private void openLoginView(LoginModel.TargetView targetView) {
        LoginView loginView = new LoginView(targetView);
        Positioning pos = new Positioning();
        pos.SetMonitorStartUpMenu(loginView);
        loginView.setVisible(true);
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