package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.beans.Order;

public class OrderDAO {

    public static int create(Order order) {
        String sql = "INSERT INTO orders (user_id, totale, stato, data_ordine) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, order.getUserId());
            pstmt.setDouble(2, order.getTotalPrice()); // totale
            pstmt.setString(3, order.getStatus());     // stato
            pstmt.setTimestamp(4, order.getOrderDate()); // data_ordine

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected == 0) {
                System.err.println("Nessuna riga inserita in orders.");
                return -1;
            }

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); // ID dell'ordine creato
            } else {
                System.err.println("Nessuna chiave generata restituita.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }


    public static List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM Orders";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Order o = new Order();
                o.setId(rs.getInt("id"));
                o.setUserId(rs.getInt("user_id"));
                o.setTotalPrice(rs.getDouble("totale"));
                o.setStatus(rs.getString("stato"));
                o.setOrderDate(rs.getTimestamp("data_ordine"));
                orders.add(o);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

}
