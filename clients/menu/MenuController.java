package clients.menu;

import Utils.Positioning;
import clients.stockManager.Login.StockLoginController;
import clients.stockManager.Login.StockLoginModel;
import clients.stockManager.Login.StockLoginView;
import clients.cashier.login.LoginController;
import clients.cashier.login.LoginModel;
import clients.cashier.login.LoginView;
import clients.catalogue.CatalogueView;
import clients.customer.CustLogin.CustLoginController;
import clients.customer.CustLogin.CustLoginModel;
import clients.customer.CustLogin.CustLoginView;
import clients.packing.Login.PackLoginController;
import clients.packing.Login.PackLoginModel;
import clients.packing.Login.PackLoginView;
import middle.MiddleFactory;


public class MenuController {
    private final MenuView view;

    public MenuController(MenuView view, MiddleFactory mlf) {
        this.view = view;
    }
    // Add action listeners for each button
    public void runCashier(MiddleFactory mlf) {
        view.getCashierButton().addActionListener(e -> {
            LoginView lv = new LoginView();
            LoginModel lm = new LoginModel();
            LoginController lc = new LoginController(lv, lm, mlf);
            lv.setController(lc);
            Positioning pos = new Positioning();
            pos.SetMonLocCashier(lv);
            lv.setVisible(true);
        });
    }

    public void runCustomer(MiddleFactory mlf) {
        view.getCustomerButton().addActionListener(e ->{
            CustLoginView cv = new CustLoginView();
            CustLoginModel cm = new CustLoginModel();
            CustLoginController Cc = new CustLoginController(cv, cm, mlf);
            cv.setController(Cc);
            Positioning pos = new Positioning();
            pos.SetMonLocCustomer(cv);
            cv.setVisible(true);});
    }

    public void runPacking(MiddleFactory mlf) {
        view.getPackingButton().addActionListener(e ->{
            PackLoginView pv = new PackLoginView();
            PackLoginModel pm = new PackLoginModel();
            PackLoginController pc = new PackLoginController(pv, pm, mlf);
            pv.setController(pc);
            Positioning pos = new Positioning();
            pos.SetMonitorLocPacking(pv);
            pv.setVisible(true);
        });
    }

    public void runStockClient(MiddleFactory mlf) {
        view.getBackDoorButton().addActionListener(e -> {
            StockLoginView sv = new StockLoginView();
            StockLoginModel sm = new StockLoginModel();
            StockLoginController sc = new StockLoginController(sv, sm, mlf);
            sv.setController(sc);
            Positioning pos = new Positioning();
            pos.SetMonitorLocStock(sv);
            sv.setVisible(true);
        });
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