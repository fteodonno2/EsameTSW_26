package controller;

import dao.ProductDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.beans.User;

import java.io.IOException;

@WebServlet("/deleteProduct")
public class DeleteProductServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null || !Boolean.TRUE.equals(user.getIsAdmin())) {
            response.sendRedirect("index.jsp?error=unauthorized");
            return;
        }

        try {
            int id = Integer.parseInt(request.getParameter("productId"));
            boolean deleted = ProductDAO.delete(id);

            if (deleted) {
                response.sendRedirect("admin?success=Prodotto_eliminato");
            } else {
                response.sendRedirect("admin?error=delete_failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("admin?error=invalid_id");
        }
    }
}
