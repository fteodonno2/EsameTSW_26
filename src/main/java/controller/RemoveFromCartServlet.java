package controller;

import dao.CartDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.beans.Cart;

import java.io.IOException;

@WebServlet("/removeFromCart")
public class RemoveFromCartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int productId = Integer.parseInt(request.getParameter("productId"));
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("cart");

            if (cart != null) {
                // Rimuovi completamente il prodotto dal carrello nella sessione
                cart.removeItem(productId);

                // Se utente loggato, rimuovi anche dal DB
                model.beans.User user = (model.beans.User) session.getAttribute("user");
                if (user != null) {
                    CartDAO.removeFromCart(user.getId(), productId);
                }

                session.setAttribute("cart", cart);
            }

            response.sendRedirect("cart.jsp");
        } catch (NumberFormatException e) {
            response.sendRedirect("error.jsp?msg=ID+prodotto+non+valido");
        }
    }
}


