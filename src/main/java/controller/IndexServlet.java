package controller;

import dao.ProductDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.beans.Product;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDAO dao = new ProductDAO();
        try {
            List<Product> bestseller = dao.getBestseller();

            request.setAttribute("bestseller", bestseller);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }
}
