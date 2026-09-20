package controller;

import dao.ProductDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.beans.Product;

import java.io.IOException;
import java.util.List;

@WebServlet("/novita")
public class NovitaServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDAO productDAO = new ProductDAO();
        List<Product> novita = productDAO.getLatestProducts(7); // ultimi 10 prodotti
        request.setAttribute("novita", novita);

        RequestDispatcher dispatcher = request.getRequestDispatcher("novita.jsp");
        dispatcher.forward(request, response);
    }
}
