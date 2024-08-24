import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeIntervalTableExample {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create frame
            JFrame frame = new JFrame("Time Interval JTable Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);

            // Define columns and data
            String[] columnNames = {"Time Interval"};
            Object[][] data = {
                {formatTimeInterval("08:00", "09:00")},
                {formatTimeInterval("09:00", "10:00")}
            };

            // Create table model and table
            DefaultTableModel model = new DefaultTableModel(data, columnNames);
            JTable table = new JTable(model);

            // Custom cell renderer for time intervals
            table.getColumnModel().getColumn(0).setCellRenderer(new TimeIntervalRenderer());

            // Custom cell editor for time intervals
            table.getColumnModel().getColumn(0).setCellEditor(new TimeIntervalEditor());

            // Add table to frame
            frame.add(new JScrollPane(table));
            frame.setVisible(true);
        });
    }

    // Format a time interval as "HH:mm-HH:mm"
    private static String formatTimeInterval(String startTime, String endTime) {
        return startTime + "-" + endTime;
    }

    // Custom cell renderer for time intervals
    static class TimeIntervalRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if (value != null) {
                setText(value.toString());
            }
            return cell;
        }
    }

    // Custom cell editor for time intervals
    static class TimeIntervalEditor extends AbstractCellEditor implements TableCellEditor {
        private final JTextField textField;
        private final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

        public TimeIntervalEditor() {
            textField = new JTextField();
            textField.addActionListener(e -> stopCellEditing());
        }

        @Override
        public Object getCellEditorValue() {
            String text = textField.getText();
            try {
                String[] parts = text.split("-");
                if (parts.length == 2) {
                    String start = parts[0].trim();
                    String end = parts[1].trim();
                    // Validate time format
                    timeFormat.parse(start);
                    timeFormat.parse(end);
                    return formatTimeInterval(start, end);
                }
            } catch (ParseException e) {
                // Handle parse exception
            }
            return text;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            textField.setText(value != null ? value.toString() : "");
            return textField;
        }

        @Override
        public boolean stopCellEditing() {
            fireEditingStopped();
            return super.stopCellEditing();
        }
    }
}
