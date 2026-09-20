package controller;

import dao.OrderDAO;
import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.beans.Order;
import model.beans.Product;
import model.beans.User;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin")
public class AdminPanelServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !Boolean.TRUE.equals(user.getIsAdmin())) {
            response.sendRedirect("index.jsp?error=unauthorized");
            return;
        }

        List<Product> products = ProductDAO.getAllProducts();
        List<Order> ordini = OrderDAO.getAll();

        request.setAttribute("products", products);
        request.setAttribute("orders", ordini);
        request.getRequestDispatcher("/admin-panel.jsp").forward(request, response);
    }
}
