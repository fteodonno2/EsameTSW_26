package controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);

        // Se la sessione esiste, invalida la sessione
        if (session != null) {
            session.invalidate(); // Invalida la sessione
        }
        // Redirigi l'utente alla pagina principale
        response.sendRedirect("index");
    }
}

