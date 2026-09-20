package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.beans.Cart;

import java.io.IOException;

@WebServlet("/cart")
public class ViewCartServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        request.setAttribute("cart", cart);
        request.getRequestDispatcher("cart.jsp").forward(request, response);
    }
}
