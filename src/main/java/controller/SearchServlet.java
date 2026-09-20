package controller;

import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.beans.Product;

import java.io.IOException;
import java.util.List;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String query = request.getParameter("q");
        System.out.println("Parametro ricerca: " + query);

        if (query == null || query.trim().isEmpty()) {
            request.setAttribute("errore", "Inserisci una parola chiave");
            request.getRequestDispatcher("search.jsp").forward(request, response);
            return;
        }

        List<Product> risultati = ProductDAO.search(query);
        request.setAttribute("risultati", risultati);
        request.setAttribute("query", query);
        request.getRequestDispatcher("search.jsp").forward(request, response);
    }
}