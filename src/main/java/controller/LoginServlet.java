package controller;

import dao.CartDAO;
import dao.PasswordUtils;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.beans.Cart;
import model.beans.User;

import java.io.IOException;
import java.util.Map;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            response.sendRedirect("login.jsp?error=empty_fields");
            return;
        }

        User user = UserDAO.getByEmail(email);

        if (user != null && PasswordUtils.checkPassword(password, user.getPasswordHash())) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);
            session.setAttribute("userId", user.getId());

            // Inizio sincronizzazione carrello
            Cart sessionCart = (Cart) session.getAttribute("cart");
            if (sessionCart != null && !sessionCart.isEmpty()) {
                for (Map.Entry<Integer, Integer> entry : sessionCart.getItems().entrySet()) {
                    int productId = entry.getKey();
                    CartDAO.addToCart(user.getId(), productId);
                }
            }

            // Redireziona in base al ruolo
            if (Boolean.TRUE.equals(user.getIsAdmin())) {
                response.sendRedirect("admin"); // admin
            } else {
                response.sendRedirect("index"); // utente normale
            }

        } else {
            response.sendRedirect("login.jsp?error=invalid_credentials");
        }
    }
}

