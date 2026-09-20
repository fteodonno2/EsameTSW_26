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

@WebServlet("/product-list")
public class ProductListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ProductDAO productDAO = new ProductDAO();
        List<Product> prodotti = productDAO.getAllProducts();
        request.setAttribute("prodotti", prodotti);

        RequestDispatcher dispatcher = request.getRequestDispatcher("product-list.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Parametro ricerca
        String query = request.getParameter("q");
        ProductDAO productDAO = new ProductDAO();
        List<Product> prodotti2;

        if (query != null && !query.trim().isEmpty()) {
            prodotti2 = productDAO.search(query);
            // Per mostrare cosa è stato cercato
            request.setAttribute("query", query);
        } else {
            // Tutti i prodotti se nessuna ricerca
            prodotti2 = productDAO.getAllProducts();
        }

        request.setAttribute("prodotti", prodotti2);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product-list.jsp");
        dispatcher.forward(request, response);
    }
}



