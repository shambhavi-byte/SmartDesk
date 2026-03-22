package smartdesk.model;

import java.time.LocalDate;
public class Attendance {
    private int empId;
    private LocalDate date;
    private String status;

    public Attendance() {}

    public Attendance(int empId, LocalDate date, String status) {
        this.empId = empId;
        this.date = date;
        this.status = status;
    }

    public int getEmpId() { return empId; }
    public void setEmpId(int empId) { this.empId = empId; }

    public LocalDate getDate() { return date;
}}
