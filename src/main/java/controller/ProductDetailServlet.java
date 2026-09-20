package controller;

import dao.ProductDAO;
import model.beans.Product;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/product-detail")
public class ProductDetailServlet extends HttpServlet {
    private ProductDAO productDAO;

    @Override
    public void init() {
        productDAO = new ProductDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idParam = request.getParameter("id");
        if (idParam != null) {
            try {
                int id = Integer.parseInt(idParam);
                Product book = productDAO.getById(id);
                if (book != null) {
                    request.setAttribute("book", book);
                    request.getRequestDispatcher("/product-detail.jsp").forward(request, response);
                } else {
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Prodotto non trovato");
                }
            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID non valido");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID mancante");
        }
    }
}
