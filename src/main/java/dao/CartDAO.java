package dao;

import java.sql.*;


public class CartDAO {

    public static boolean addToCart(int userId, int productId) {
        String checkSql = "SELECT quantity FROM cart_items WHERE user_id = ? AND product_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {
            checkStmt.setInt(1, userId);
            checkStmt.setInt(2, productId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                // Incrementa la quantità
                int currentQuantity = rs.getInt("quantity");
                String updateSql = "UPDATE cart_items SET quantity = ? WHERE user_id = ? AND product_id = ?";
                try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                    updateStmt.setInt(1, currentQuantity + 1);
                    updateStmt.setInt(2, userId);
                    updateStmt.setInt(3, productId);
                    return updateStmt.executeUpdate() > 0;
                }
            } else {
                // Inserisci nuovo prodotto
                String insertSql = "INSERT INTO cart_items (user_id, product_id, quantity) VALUES (?, ?, 1)";
                try (PreparedStatement insertStmt = conn.prepareStatement(insertSql)) {
                    insertStmt.setInt(1, userId);
                    insertStmt.setInt(2, productId);
                    return insertStmt.executeUpdate() > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static boolean removeFromCart(int userId, int productId) {
        String sql = "DELETE FROM cart_items WHERE user_id = ? AND product_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userId);
            pstmt.setInt(2, productId);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
