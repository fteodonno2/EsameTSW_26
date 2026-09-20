package dao;

import java.sql.*;
import model.beans.User;

public class UserDAO {

    // Metodo per recuperare l'utente per email
    public static User getByEmail(String email) {
        String sql = "SELECT * FROM Users WHERE email = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setNome(rs.getString("nome"));
                user.setCognome(rs.getString("cognome"));
                user.setEmail(rs.getString("email"));
                user.setPasswordHash(rs.getString("password_hash"));
                user.setIsAdmin(rs.getBoolean("is_admin"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Metodo per registrare un nuovo utente
    public static boolean register(User user) {
        String sql = "INSERT INTO Users (nome, cognome, email, password_hash, is_admin) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getNome());
            pstmt.setString(2, user.getCognome());
            pstmt.setString(3, user.getEmail());
            pstmt.setString(4, user.getPasswordHash());
            pstmt.setBoolean(5, user.getIsAdmin());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
