package dao;

import model.beans.OrderItem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDAO {

    public static boolean create(OrderItem orderItem) {
        String sql = "INSERT INTO order_items (order_id, product_id, quantity, prezzo_unitario) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, orderItem.getOrderId());
            pstmt.setInt(2, orderItem.getProductId());
            pstmt.setInt(3, orderItem.getQuantity());
            pstmt.setBigDecimal(4, orderItem.getPrice());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }



    public static List<OrderItem> getItemsByOrderId(int orderId) {
        List<OrderItem> items = new ArrayList<>();
        String sql = "SELECT * FROM order_items WHERE order_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, orderId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                OrderItem item = new OrderItem();
                item.setId(rs.getInt("id"));
                item.setOrderId(rs.getInt("order_id"));
                item.setProductId(rs.getInt("product_id"));
                item.setQuantity(rs.getInt("quantity"));
                item.setPriceAtTime(rs.getBigDecimal("prezzo_unitario"));
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

}
