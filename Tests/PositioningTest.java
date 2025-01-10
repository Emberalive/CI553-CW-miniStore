package Tests;

import Utils.Positioning;
import org.junit.Test;
import java.awt.*;
import java.util.function.Predicate;
import javax.swing.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PositioningTest {

    @Test
    public void testMonLocation(){
        Positioning pos = new Positioning();
        JFrame window = new JFrame();
        window.setSize(100, 100);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.getScreenDevices();
        GraphicsDevice[] screens = ge.getScreenDevices();

        int monitorIndex = 1;
        GraphicsDevice selectedMonitor = screens[monitorIndex];
        Rectangle bounds = selectedMonitor.getDefaultConfiguration().getBounds();

        int xPos = bounds.x + (bounds.width - window.getWidth()) / 2;
        int yPos = bounds.y + (bounds.height - window.getHeight()) / 2;

        pos.SetMonitorStartUpMenu(window);

        assertEquals(xPos, window.getLocation().x);
        assertEquals(yPos, window.getLocation().y);
    }

}
