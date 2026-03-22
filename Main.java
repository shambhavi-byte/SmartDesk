package smartdesk;

import smartdesk.model.Task;
import smartdesk.service.AuthService;
import smartdesk.service.TaskService;
import smartdesk.dao.AttendanceDAO;
import smartdesk.ui.LoginFrame;
import javax.swing.SwingUtilities;

import java.util.Scanner;

public class Main {

    private static final Scanner sc = new Scanner(System.in);
    private static final AuthService authService = new AuthService();
    private static final TaskService taskService = new TaskService();
    private static final AttendanceDAO attendanceDAO = new AttendanceDAO();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
        try {
            System.out.println("===== SmartDesk Login =====");
            System.out.print("Username: ");
            String username = sc.nextLine();

            System.out.print("Password: ");
            String password = sc.nextLine();

            String role = authService.login(username, password);
            System.out.println("Login successful as " + role);

            if ("ADMIN".equalsIgnoreCase(role)) {
                adminMenu();
            } else {
                employeeMenu();
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ---------------- ADMIN MENU ----------------
    private static void adminMenu() {
        while (true) {
            System.out.println("\n--- ADMIN MENU ---");
            System.out.println("1. Assign Task");
            System.out.println("2. Logout");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            try {
                if (choice == 1) {
                    System.out.print("Employee ID: ");
                    int empId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Task Title: ");
                    String title = sc.nextLine();

                    System.out.print("Priority (HIGH/MEDIUM/LOW): ");
                    String priority = sc.nextLine();

                    Task task = new Task(0, empId, title, priority, "PENDING");
                    taskService.assignTask(task);

                    System.out.println("Task assigned successfully.");

                } else if (choice == 2) {
                    System.out.println("Logged out.");
                    break;
                } else {
                    System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // ---------------- EMPLOYEE MENU ----------------
    private static void employeeMenu() {

        System.out.print("Enter your Employee ID: ");
        int empId = sc.nextInt();
        sc.nextLine();

        while (true) {
            System.out.println("\n--- EMPLOYEE MENU ---");
            System.out.println("1. View Tasks");
            System.out.println("2. View Tasks Sorted by Priority");
            System.out.println("3. Filter Tasks by Status");
            System.out.println("4. Update Task Status");
            System.out.println("5. Logout");
            System.out.println("6. Mark Attendance");
            System.out.println("7. View Attendance");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            try {
                if (choice == 1) {
                    taskService.viewTasks(empId).forEach(Main::printTask);

                } else if (choice == 2) {
                    taskService.getTasksSortedByPriority(empId).forEach(Main::printTask);

                } else if (choice == 3) {
                    System.out.print("Enter Status (PENDING/DONE): ");
                    String status = sc.nextLine();
                    taskService.getTasksByStatus(empId, status).forEach(Main::printTask);

                } else if (choice == 4) {
                    System.out.print("Task ID: ");
                    int taskId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("New Status: ");
                    String status = sc.nextLine();

                    taskService.updateStatus(taskId, status);
                    System.out.println("Task status updated.");

                } else if (choice == 5) {
                    System.out.println("Logged out.");
                    break;

                } else if (choice == 6) {
                    System.out.print("Enter Status (PRESENT/ABSENT): ");
                    String status = sc.nextLine();
                    attendanceDAO.markAttendance(empId, status);
                    System.out.println("Attendance marked.");

                } else if (choice == 7) {
                    attendanceDAO.viewAttendance(empId);

                } else {
                    System.out.println("Invalid option.");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // -------- Helper Method --------
    private static void printTask(Task t) {
        System.out.println(
                t.getTaskId() + " | " +
                        t.getTitle() + " | " +
                        t.getPriority() + " | " +
                        t.getStatus()



        );
    }
}
