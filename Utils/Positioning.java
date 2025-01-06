package Utils;

import java.awt.*;

public class Positioning {
    public void SetMonitorStartUpMenu(Component c) {

        GraphicsDevice[] screens = GetDeviceEnvironment();

        //selecting which monitor to open on
        int monitorIndex = 0; // Change to the index of your desired monitor
        if (monitorIndex < screens.length) {
            GraphicsDevice selectedMonitor = screens[monitorIndex];
            System.out.println("\nMenu Opened On \n   Monitor: " + selectedMonitor);
            // Center the frame on the selected monitor
            Rectangle monitorBounds = selectedMonitor.getDefaultConfiguration().getBounds();

            // Calculate position for centering the component on the selected monitor
            int xPos = monitorBounds.x + (monitorBounds.width - c.getWidth()) / 2;
            int yPos = monitorBounds.y + (monitorBounds.height - c.getHeight()) / 2;

            c.setLocation(xPos, yPos);
        } else {
            System.out.println("Monitor index out of bounds. Defaulting to primary monitor.");
            if (c instanceof Window) {
                Rectangle monitorBounds = screens[0].getDefaultConfiguration().getBounds();
                int xPos = monitorBounds.x + (monitorBounds.width - c.getWidth()) / 2;
                int yPos = monitorBounds.y + (monitorBounds.height - c.getHeight()) / 2;
                c.setLocation(xPos, yPos);
            } else {
                c.setLocation(0, 0); // Default to center on primary monitor

            }
        }
    }

    public void SetMonLocCashier(Component c) {

        GraphicsDevice[] screens = GetDeviceEnvironment();

        //selecting which monitor to open on
        int monitorIndex = 1; // Change to the index of your desired monitor
        if (monitorIndex < screens.length) {
            GraphicsDevice selectedMonitor = screens[monitorIndex];
            System.out.println("\nCashier Interface Opened ON \n    Monitor: " + selectedMonitor);
            // Center the frame on the selected monitor
            Rectangle monitorBounds = selectedMonitor.getDefaultConfiguration().getBounds();

            // Calculate position for centering the component on the selected monitor
            int xPos = monitorBounds.x + (monitorBounds.width - c.getWidth()) / 5;
            int yPos = monitorBounds.y + (monitorBounds.height - c.getHeight()) / 5;

            c.setLocation(xPos, yPos);
        } else {
            System.out.println("Monitor index out of bounds. Defaulting to primary monitor.");
            if (c instanceof Window) {
                Rectangle monitorBounds = screens[0].getDefaultConfiguration().getBounds();
                int xPos = monitorBounds.x + (monitorBounds.width - c.getWidth()) / 5;
                int yPos = monitorBounds.y + (monitorBounds.height - c.getHeight()) / 5;
                c.setLocation(xPos, yPos);
            } else {
                c.setLocation(0, 0); // Default to center on primary monitor
            }
        }
    }

    public void SetMonLocCustomer(Component c) {

        GraphicsDevice[] screens = GetDeviceEnvironment();


        //selecting which monitor to open on
        int monitorIndex = 1; // Change to the index of your desired monitor
        if (monitorIndex < screens.length) {
            GraphicsDevice selectedMonitor = screens[monitorIndex];
            System.out.println("\nCustomer Interface Opened ON \n   Monitor: " + selectedMonitor);
            // Center the frame on the selected monitor
            Rectangle monitorBounds = selectedMonitor.getDefaultConfiguration().getBounds();

            // Calculate position for centering the component on the selected monitor
            int xPos = monitorBounds.x + (monitorBounds.width - c.getWidth()) / 5;
            int yPos = monitorBounds.y + (monitorBounds.height * 3 - c.getHeight()) / 5;

            c.setLocation(xPos, yPos);
        } else {
            System.out.println("Monitor index out of bounds. Defaulting to primary monitor.");
            if (c instanceof Window) {
                Rectangle monitorBounds = screens[0].getDefaultConfiguration().getBounds();
                int xPos = monitorBounds.x + (monitorBounds.width - c.getWidth()) / 5;
                int yPos = monitorBounds.y + (monitorBounds.height * 3 - c.getHeight()) / 5;
                c.setLocation(xPos, yPos);
            } else {
                c.setLocation(0, 0); // Default to center on primary monitor

            }
        }
    }

    public void SetMonitorLocPacking(Component c) {
        GraphicsDevice[] screens = GetDeviceEnvironment();

        //selecting which monitor to open on
        int monitorIndex = 1; // Change to the index of your desired monitor
        if (monitorIndex < screens.length) {
            GraphicsDevice selectedMonitor = screens[monitorIndex];
            System.out.println("\nPacking Interface opened ON \n    Monitor: " + selectedMonitor);
            // Center the frame on the selected monitor
            Rectangle monitorBounds = selectedMonitor.getDefaultConfiguration().getBounds();

            // Calculate position for centering the component on the selected monitor
            int xPos = monitorBounds.x + (monitorBounds.width * 4 - c.getWidth()) / 5;
            int yPos = monitorBounds.y + (monitorBounds.height * 3- c.getHeight()) / 5;

            c.setLocation(xPos, yPos);
        } else {
            System.out.println("Monitor index out of bounds. Defaulting to primary monitor.");
            if (c instanceof Window) {
                Rectangle monitorBounds = screens[0].getDefaultConfiguration().getBounds();
                int xPos = monitorBounds.x + (monitorBounds.width * 4- c.getWidth()) / 5;
                int yPos = monitorBounds.y + (monitorBounds.height * 3 - c.getHeight()) / 5;
                c.setLocation(xPos, yPos);
            } else {
                c.setLocation(0, 0); // Default to center on primary monitor
            }
        }
    }

    public void SetMonitorLocCatalogue(Component c) {
        GraphicsDevice[] screens = GetDeviceEnvironment();

        //selecting which monitor to open on
        int monitorIndex = 1; // Change to the index of your desired monitor
        if (monitorIndex < screens.length) {
            GraphicsDevice selectedMonitor = screens[monitorIndex];
            System.out.println("\nCatalogue Interface opened ON \n  Monitor: " + selectedMonitor);
            // Center the frame on the selected monitor
            Rectangle monitorBounds = selectedMonitor.getDefaultConfiguration().getBounds();

            // Calculate position for centering the component on the selected monitor
            int xPos1 = monitorBounds.x + (monitorBounds.width * 3 - c.getWidth()) / 5;

            int xPos2 = monitorBounds.x + (monitorBounds.width *2 - c.getWidth()) /3;

            int yPos = monitorBounds.y + (monitorBounds.height - c.getHeight()) / 2;
            int xPos = (xPos1 + xPos2 + 40) / 2;

            c.setLocation(xPos, yPos);
        } else {
            System.out.println("\nMonitor index out of bounds. Defaulting to primary monitor.");
            if (c instanceof Window) {
                Rectangle monitorBounds = screens[0].getDefaultConfiguration().getBounds();
                int xPos1 = monitorBounds.x + (monitorBounds.width * 3 - c.getWidth()) / 5;
                int xPos2 = monitorBounds.x + (monitorBounds.width *2 - c.getWidth()) /3;
                int yPos = monitorBounds.y + (monitorBounds.height - c.getHeight()) / 2;
                int xPos = (xPos1 + xPos2 + 40) / 2;
                c.setLocation(xPos, yPos);
            } else {
                c.setLocation(0, 0); // Default to center on primary monitor
            }
        }
    }

    public void SetMonitorLocStock(Component c) {
        GraphicsDevice[] screens = GetDeviceEnvironment();

        //selecting which monitor to open on
        int monitorIndex = 0; // Change to the index of your desired monitor
        if (monitorIndex < screens.length) {
            GraphicsDevice selectedMonitor = screens[monitorIndex];
            System.out.println("\nPacking Interface opened ON \n    Monitor: " + selectedMonitor);
            // Center the frame on the selected monitor
            Rectangle monitorBounds = selectedMonitor.getDefaultConfiguration().getBounds();

            // Calculate position for centering the component on the selected monitor
            int xPos = monitorBounds.x + (monitorBounds.width * 4 - c.getWidth()) / 5;
            int yPos = monitorBounds.y + (monitorBounds.height - c.getHeight()) / 4;

            c.setLocation(xPos, yPos);
        } else {
            System.out.println("Monitor index out of bounds. Defaulting to primary monitor.");
            if (c instanceof Window) {
                Rectangle monitorBounds = screens[0].getDefaultConfiguration().getBounds();
                int xPos = monitorBounds.x + (monitorBounds.width - c.getWidth()) / 5;
                int yPos = monitorBounds.y + (monitorBounds.height - c.getHeight()) / 4;
                c.setLocation(xPos, yPos);
//                ((Window) c).setLocationRelativeTo(null); // Centers relative to the primary screen
            } else {
                c.setLocation(0, 0); // Default to center on primary monitor

            }
        }
    }


    //gets the Device environment, list of the different monitors
    public GraphicsDevice[] GetDeviceEnvironment(){
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        return ge.getScreenDevices();
    }
}


