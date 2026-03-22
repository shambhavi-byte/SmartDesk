package smartdesk.model;

public class Task {
    private int taskId;
    private int empId;
    private String title;
    private String priority;
    private String status;

    public Task() {}

    public Task(int taskId, int empId, String title,
                String priority, String status) {
        this.taskId = taskId;
        this.empId = empId;
        this.title = title;
        this.priority = priority;
        this.status = status;
    }

    public int getTaskId() { return taskId; }
    public void setTaskId(int taskId) { this.taskId = taskId; }

    public int getEmpId() { return empId; }
    public void setEmpId(int empId) { this.empId = empId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
