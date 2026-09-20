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

@WebServlet("/editProduct")
public class EditProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !Boolean.TRUE.equals(user.getIsAdmin())) {
            response.sendRedirect("index.jsp?error=unauthorized");
            return;
        }

        try {
            Product product = new Product();
            product.setId(Integer.parseInt(request.getParameter("id")));
            product.setNome(request.getParameter("nome"));
            product.setDescrizione(request.getParameter("descrizione"));
            product.setPrezzo(new BigDecimal(request.getParameter("prezzo")));
            product.setCategoria(request.getParameter("categoria"));
            product.setImmagine(request.getParameter("immagine"));

            boolean updated = ProductDAO.update(product);

            if (updated) {
                response.sendRedirect("admin?success=Prodotto_aggiornato");
            } else {
                response.sendRedirect("admin?error=update_failed");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("admin?error=invalid_data");
        }
    }
}
