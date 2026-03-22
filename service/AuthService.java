package smartdesk.service;

import smartdesk.config.DBConnection;
import smartdesk.exception.SmartDeskException;
import smartdesk.util.LoggerUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AuthService {

    public String login(String username, String password) throws SmartDeskException {

        // 🔹 LOG LOGIN ATTEMPT
        LoggerUtil.log("Login attempt for user: " + username);

        String sql = "SELECT role FROM employee WHERE username=? AND password=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String role = rs.getString("role");

                // 🔹 LOG SUCCESS
                LoggerUtil.log("Login successful for user: " + username + " | Role: " + role);

                return role;
            } else {
                // 🔹 LOG FAILURE
                LoggerUtil.log("Login failed for user: " + username);
                throw new SmartDeskException("Invalid username or password");
            }

        } catch (Exception e) {
            LoggerUtil.log("Login error for user: " + username + " | Reason: " + e.getMessage());
            throw new SmartDeskException("Login failed", e);
        }
    }
}
