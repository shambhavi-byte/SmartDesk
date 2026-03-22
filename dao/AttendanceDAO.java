package smartdesk.dao;

import smartdesk.config.DBConnection;
import smartdesk.util.LoggerUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AttendanceDAO {

    public void markAttendance(int empId, String status) throws Exception {
        String sql = "INSERT INTO attendance (emp_id, date, status) VALUES (?, CURDATE(), ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, empId);
            ps.setString(2, status);
            ps.executeUpdate();
            LoggerUtil.log("Attendance marked for employee ID: " + empId);
        }
    }

    public void viewAttendance(int empId) throws Exception {
        String sql = "SELECT date, status FROM attendance WHERE emp_id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, empId);
            ResultSet rs = ps.executeQuery();

            System.out.println("Date | Status");
            while (rs.next()) {
                System.out.println(
                        rs.getDate("date") + " | " +
                                rs.getString("status")
                );
            }
        }
    }
}
