package clients.catalogue;

import javax.swing.*;

public class CatalogueClient {
    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //create the view
                CatalogueView CatalogueView = new CatalogueView();
                CatalogueView.setVisible(true);
            }
        });

    }
}
