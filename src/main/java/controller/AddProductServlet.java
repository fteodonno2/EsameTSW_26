package controller;

import dao.ProductDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.beans.Product;
import model.beans.User;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/addProduct")
public class AddProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !Boolean.TRUE.equals(user.getIsAdmin())) {
            response.sendRedirect("index.jsp?error=unauthorized");
            return;
        }

        try {
            Product product = new Product();
            product.setNome(request.getParameter("nome"));
            product.setDescrizione(request.getParameter("descrizione"));
            product.setPrezzo(new BigDecimal(request.getParameter("prezzo")));
            product.setCategoria(request.getParameter("categoria"));
            product.setImmagine(request.getParameter("immagine"));

            boolean success = ProductDAO.create(product);
            if (success) {
                response.sendRedirect("admin?success=product_added");
            } else {
                response.sendRedirect("admin?error=insert_failed");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("admin?error=invalid_data");
        }
    }
}
