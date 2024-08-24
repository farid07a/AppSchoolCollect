import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DynamicComboBoxes {

    private JComboBox<String> levelComboBox;
    private JComboBox<String> classComboBox;
    private JComboBox<String> subjectComboBox;
    private JComboBox<String> teacherComboBox;

    private final String[] levels = {"Primary", "Secondary"};
    private final String[] primaryClasses = {"1", "2"};
    private final String[] secondaryClasses = {"3", "4"};
    private final String[] mathSubjects = {"Math"};
    private final String[] frenchSubjects = {"French"};
    private final String[] englishSubjects = {"English"};
    private final String[] physicsSubjects = {"Physics"};
    private final String[] teachers = {"Basma", "Farid", "Amar", "Rochdi"};

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DynamicComboBoxes().createAndShowGUI());
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Dynamic ComboBoxes");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 2));

        levelComboBox = new JComboBox<>(levels);
        classComboBox = new JComboBox<>();
        subjectComboBox = new JComboBox<>();
        teacherComboBox = new JComboBox<>(teachers);

        levelComboBox.addActionListener(new LevelComboBoxListener());
        classComboBox.addActionListener(new ClassComboBoxListener());

        frame.add(new JLabel("Level:"));
        frame.add(levelComboBox);
        frame.add(new JLabel("Class:"));
        frame.add(classComboBox);
        frame.add(new JLabel("Subject:"));
        frame.add(subjectComboBox);
        frame.add(new JLabel("Teacher:"));
        frame.add(teacherComboBox);

        // Initialize the classComboBox and subjectComboBox
        updateClassComboBox();
        updateSubjectComboBox();

        frame.pack();
        frame.setVisible(true);
    }

    private void updateClassComboBox() {
        classComboBox.removeAllItems();
        String selectedLevel = (String) levelComboBox.getSelectedItem();
        if ("Primary".equals(selectedLevel)) {
            for (String cls : primaryClasses) {
                classComboBox.addItem(cls);
            }
        } else if ("Secondary".equals(selectedLevel)) {
            for (String cls : secondaryClasses) {
                classComboBox.addItem(cls);
            }
        }
        updateSubjectComboBox();  // Update subjects based on new class selection
    }

    private void updateSubjectComboBox() {
        subjectComboBox.removeAllItems();
        String selectedClass = (String) classComboBox.getSelectedItem();
        if ("1".equals(selectedClass)) {
            for (String subject : mathSubjects) {
                subjectComboBox.addItem(subject);
            }
        } else if ("2".equals(selectedClass)) {
            for (String subject : frenchSubjects) {
                subjectComboBox.addItem(subject);
            }
        } else if ("3".equals(selectedClass)) {
            for (String subject : englishSubjects) {
                subjectComboBox.addItem(subject);
            }
        } else if ("4".equals(selectedClass)) {
            for (String subject : physicsSubjects) {
                subjectComboBox.addItem(subject);
            }
        }
        updateTeacherComboBox();  // Update teachers based on new subject selection
    }

    private void updateTeacherComboBox() {
        // This example does not filter teachers based on subjects,
        // but you can implement such logic if needed.
        teacherComboBox.removeAllItems();
        for (String teacher : teachers) {
            teacherComboBox.addItem(teacher);
        }
    }

    private class LevelComboBoxListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            updateClassComboBox();
        }
    }

    private class ClassComboBoxListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            updateSubjectComboBox();
        }
    }
}
