package smartdesk.ui;

import smartdesk.dao.AttendanceDAO;
import smartdesk.service.TaskService;

import javax.swing.*;
import java.awt.*;

public class EmployeeFrame extends JFrame {

    private final TaskService taskService = new TaskService();
    private final AttendanceDAO attendanceDAO = new AttendanceDAO();

    public EmployeeFrame() {
        setTitle("Employee Panel");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JTextField empIdField = new JTextField();
        JTextField statusField = new JTextField();
        JTextField taskIdField = new JTextField();

        JButton viewTasksBtn = new JButton("View Tasks");
        JButton updateStatusBtn = new JButton("Update Task Status");
        JButton markAttendanceBtn = new JButton("Mark Attendance");

        panel.add(new JLabel("Employee ID:"));
        panel.add(empIdField);
        panel.add(new JLabel("Task ID:"));
        panel.add(taskIdField);
        panel.add(new JLabel("Status (DONE/PENDING):"));
        panel.add(statusField);
        panel.add(viewTasksBtn);
        panel.add(updateStatusBtn);
        panel.add(new JLabel());
        panel.add(markAttendanceBtn);

        add(panel);

        viewTasksBtn.addActionListener(e -> {
            try {
                int empId = Integer.parseInt(empIdField.getText());
                taskService.viewTasks(empId).forEach(t ->
                        JOptionPane.showMessageDialog(this,
                                t.getTaskId() + " | " + t.getTitle() + " | " + t.getStatus())
                );
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        updateStatusBtn.addActionListener(e -> {
            try {
                int taskId = Integer.parseInt(taskIdField.getText());
                String status = statusField.getText();
                taskService.updateStatus(taskId, status);
                JOptionPane.showMessageDialog(this, "Status updated");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });

        markAttendanceBtn.addActionListener(e -> {
            try {
                int empId = Integer.parseInt(empIdField.getText());
                attendanceDAO.markAttendance(empId, "PRESENT");
                JOptionPane.showMessageDialog(this, "Attendance marked");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        });
    }
}
