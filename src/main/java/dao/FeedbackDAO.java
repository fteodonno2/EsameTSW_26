package dao;

import model.beans.Feedback;

import java.sql.*;

public class FeedbackDAO {

    public static int saveFeedback(Feedback feedback) {
        String sql = "INSERT INTO feedback (name, email, rating, comments) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, feedback.getName());
            stmt.setString(2, feedback.getEmail());
            stmt.setInt(3, feedback.getRating());
            stmt.setString(4, feedback.getComments());

            return stmt.executeUpdate(); // ritorna >0 se inserimento avvenuto

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
