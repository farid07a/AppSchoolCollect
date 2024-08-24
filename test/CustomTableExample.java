import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class CustomTableExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Custom JTable Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);

            // Sample data
            String[] columnNames = {"Column 1", "Column 2", "Column 3"};
            Object[][] data = {
                {"Row1-Col1", "Row1-Col2", "Row1-Col3"},
                {"Row2-Col1", "Row2-Col2", "Row2-Col3"}
            };

            DefaultTableModel model = new DefaultTableModel(data, columnNames);
            JTable table = new JTable(model) {
                @Override
                public void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2d = (Graphics2D) g;
                    TableColumnModel columnModel = getColumnModel();
                    int columnCount = columnModel.getColumnCount();
                    
                    // Draw custom grid lines
                    for (int i = 0; i < columnCount - 1; i++) {
                        int x = getColumnModel().getColumn(i).getWidth();
                        int y = 0;
                        int height = getHeight();
                        
                        // Draw the line between columns 0 and 1 but skip drawing between 1 and 2
                        if (i != 0) {
                            g2d.setColor(Color.LIGHT_GRAY); // Or any color you prefer
                            g2d.drawLine(x, y, x, height);
                        }
                    }
                }
            };

            // Custom cell renderer to align text to center
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment(JLabel.CENTER);
            for (int i = 0; i < model.getColumnCount(); i++) {
                table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
            }

            // Optionally, customize the header renderer
            JTableHeader header = table.getTableHeader();
            header.setDefaultRenderer(new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, 
                                                               boolean isSelected, boolean hasFocus, 
                                                               int row, int column) {
                    Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    // Customize header renderer if needed
                    return c;
                }
            });

            frame.add(new JScrollPane(table));
            frame.setVisible(true);
        });
    }
}
