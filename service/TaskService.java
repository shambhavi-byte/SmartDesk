package smartdesk.service;

import smartdesk.dao.TaskDAO;
import smartdesk.model.Task;
import smartdesk.util.LoggerUtil;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TaskService {

    private final TaskDAO taskDAO = new TaskDAO();

    public void assignTask(Task task) throws Exception {
        LoggerUtil.log("Task assigned to employee ID: " + task.getEmpId());
        taskDAO.addTask(task);
    }

    public List<Task> viewTasks(int empId) throws Exception {
        LoggerUtil.log("Viewing tasks for employee ID: " + empId);
        return taskDAO.getTasksByEmployee(empId);
    }

    // 🔹 SORT BY PRIORITY (HIGH > MEDIUM > LOW)
    public List<Task> getTasksSortedByPriority(int empId) throws Exception {
        LoggerUtil.log("Sorting tasks by priority for employee ID: " + empId);
        return taskDAO.getTasksByEmployee(empId)
                .stream()
                .sorted(Comparator.comparingInt(this::priorityOrder))
                .collect(Collectors.toList());
    }

    // 🔹 FILTER BY STATUS
    public List<Task> getTasksByStatus(int empId, String status) throws Exception {
        LoggerUtil.log("Filtering tasks for employee ID: " + empId + " with status: " + status);
        return taskDAO.getTasksByEmployee(empId)
                .stream()
                .filter(t -> t.getStatus().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }

    private int priorityOrder(Task task) {
        return switch (task.getPriority().toUpperCase()) {
            case "HIGH" -> 1;
            case "MEDIUM" -> 2;
            default -> 3;
        };
    }

    public void updateStatus(int taskId, String status) throws Exception {
        LoggerUtil.log("Updating task ID: " + taskId + " to status: " + status);
        taskDAO.updateTaskStatus(taskId, status);
    }
}
