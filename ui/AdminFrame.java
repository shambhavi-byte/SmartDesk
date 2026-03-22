package smartdesk.ui;

import smartdesk.model.Task;
import smartdesk.service.TaskService;

import javax.swing.*;
import java.awt.*;

public class AdminFrame extends JFrame {

    private final TaskService taskService = new TaskService();

    public AdminFrame() {
        setTitle("Admin Panel");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField empIdField = new JTextField();
        JTextField titleField = new JTextField();
        JTextField priorityField = new JTextField();

        JButton assignBtn = new JButton("Assign Task");

        panel.add(new JLabel("Employee ID:"));
        panel.add(empIdField);
        panel.add(new JLabel("Task Title:"));
        panel.add(titleField);
        panel.add(new JLabel("Priority (HIGH/MEDIUM/LOW):"));
        panel.add(priorityField);
        panel.add(new JLabel());
        panel.add(assignBtn);

        add(panel);

        assignBtn.addActionListener(e -> {
            try {
                int empId = Integer.parseInt(empIdField.getText());
                String title = titleField.getText();
                String priority = priorityField.getText();

                Task task = new Task(0, empId, title, priority, "PENDING");
                taskService.assignTask(task);

                JOptionPane.showMessageDialog(this, "Task assigned successfully");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
