package controller;

import dao.CartDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.beans.Cart;

import java.io.IOException;

@WebServlet("/addToCart")
public class AddToCartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int productId = Integer.parseInt(request.getParameter("productId"));
            HttpSession session = request.getSession();

            // Recupera utente loggato (se esiste)
            model.beans.User user = (model.beans.User) session.getAttribute("user");

            // Carrello dalla sessione o nuovo
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart == null) {
                cart = new Cart();
            }

            // Aggiunge prodotto
            cart.addProduct(productId);
            session.setAttribute("cart", cart); // Salva in sessione

            // Se l'utente è loggato, salva anche nel DB
            if (user != null) {
                CartDAO.addToCart(user.getId(), productId);
            }

            // Redirect alla pagina del carrello
            response.sendRedirect("cart.jsp");

        } catch (NumberFormatException e) {
            response.sendRedirect("error.jsp?msg=ID+prodotto+non+valido");
        }
    }
}
