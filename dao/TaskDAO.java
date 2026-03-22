package smartdesk.dao;

import smartdesk.config.DBConnection;
import smartdesk.model.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TaskDAO {

    public void addTask(Task task) throws Exception {
        String sql = "INSERT INTO task (emp_id, title, priority, status) VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, task.getEmpId());
            ps.setString(2, task.getTitle());
            ps.setString(3, task.getPriority());
            ps.setString(4, task.getStatus());
            ps.executeUpdate();
        }
    }

    public List<Task> getTasksByEmployee(int empId) throws Exception {
        String sql = "SELECT * FROM task WHERE emp_id=?";
        List<Task> tasks = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, empId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Task task = new Task(
                        rs.getInt("task_id"),
                        rs.getInt("emp_id"),
                        rs.getString("title"),
                        rs.getString("priority"),
                        rs.getString("status")
                );
                tasks.add(task);
            }
        }
        return tasks;
    }

    public void updateTaskStatus(int taskId, String status) throws Exception {
        String sql = "UPDATE task SET status=? WHERE task_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, taskId);
            ps.executeUpdate();
        }
    }
}
