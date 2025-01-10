package Tests;

import Utils.Styling;
import org.junit.Test;
import javax.swing.*;
import java.awt.*;
import java.util.function.Predicate;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class StylingTest {

    @Test
    public void testStyling() {
        JPanel parent = new JPanel();
        JButton button = new JButton("Button");


        parent.add(button);


        Color background = Color.BLACK;
        Color foreground = Color.RED;

        Predicate<Component> condition = component -> component instanceof JButton;

        Styling styling = new Styling();
        styling.styling(parent, background, foreground, condition);

        //testing the colours for the button
        assertEquals(background, button.getBackground());
        assertEquals(foreground, button.getForeground());
    }

    @Test
    public void nonAssignedComponents() {
            JPanel parent = new JPanel();
            JButton button = new JButton("Button");
            JLabel label = new JLabel("Label");
            JTextField textField = new JTextField("TextField");

            parent.add(button);
            parent.add(label);
            parent.add(textField);

            Color background = Color.BLACK;
            Color foreground = Color.RED;

            Predicate<Component> condition = component -> component instanceof JButton;

            Styling styling = new Styling();
            styling.styling(parent, background, foreground, condition);

            //testing the colours for the button
            assertEquals(background, button.getBackground());
            assertEquals(foreground, button.getForeground());

            //testing if the colours for the non styles JComponents have not been changed
            Color defText = textField.getBackground();
            Color defColor = textField.getForeground();

            Color defLabel = label.getBackground();
            Color defLabelColor = label.getForeground();

            assertEquals(defText, textField.getBackground());
            assertEquals(defColor, textField.getForeground());

            assertEquals(defLabel, label.getBackground());
            assertEquals(defLabelColor, label.getForeground());
    }

    @Test
    public void testStylingRecursivleley () {
        JPanel parent = new JPanel();
        JPanel childPanel = new JPanel();
        JButton childButton = new JButton("Button");
        JLabel childLabel = new JLabel("Label");

        childPanel.add(childButton);
        childPanel.add(childLabel);
        parent.add(childPanel);

        Color background = Color.BLACK;
        Color foreground = Color.RED;

        Predicate<Component> condition = component -> component instanceof JComponent;

        Styling styling = new Styling();
        styling.styling(parent, background, foreground, condition);

        assertEquals(background, childButton.getBackground());
        assertEquals(foreground, childButton.getForeground());

        assertEquals(background, childLabel.getBackground());
        assertEquals(foreground, childLabel.getForeground());
    }
}
