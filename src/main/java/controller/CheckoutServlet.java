package controller;

import dao.OrderDAO;
import dao.OrderItemDAO;
import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.beans.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Map;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        User user = (User) session.getAttribute("user");

        if (cart == null || cart.isEmpty()) {
            response.sendRedirect("cart.jsp");
            return;
        }

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Creazione ordine
        Order order = new Order();
        order.setUserId(user.getId());
        order.setTotalPrice(cart.getTotalPrice());
        order.setStatus("In elaborazione"); // usa lo stesso valore del default DB
        order.setOrderDate(new Timestamp(System.currentTimeMillis()));

        // Salvataggio ordine nel DB
        int orderId = OrderDAO.create(order);
        if (orderId == -1) {
            response.sendRedirect("error.jsp");
            return;
        }

        // Salvataggio dei singoli articoli
        for (Map.Entry<Integer, Integer> entry : cart.getItems().entrySet()) {
            Product product = ProductDAO.getById(entry.getKey());
            if (product != null) {
                OrderItem orderItem = new OrderItem();
                orderItem.setOrderId(orderId);
                orderItem.setProductId(product.getId());
                orderItem.setQuantity(entry.getValue());
                orderItem.setPriceAtTime(product.getPrezzo());
                OrderItemDAO.create(orderItem);
            }
        }

        // Pulisci il carrello
        session.removeAttribute("cart");

        // Reindirizza alla conferma
        response.sendRedirect("order-confirmation.jsp?orderId=" + orderId);
    }
}
