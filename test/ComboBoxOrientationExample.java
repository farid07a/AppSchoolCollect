import javax.swing.*;
import java.awt.*;

public class ComboBoxOrientationExample {
    public static void main(String[] args) {
        // Create a JFrame
        JFrame frame = new JFrame("JComboBox Orientation Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);

        // Create a JComboBox
        JComboBox<String> comboBox = new JComboBox<>(new String[] {"Item 1", "Item 2", "Item 3"});

        // Set the orientation to right-to-left
        comboBox.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);

        // Add JComboBox to the frame
        frame.getContentPane().add(comboBox, BorderLayout.CENTER);

        // Show the frame
        frame.setVisible(true);
    }
}
