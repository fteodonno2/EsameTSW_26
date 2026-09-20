package controller;

import dao.PasswordUtils;
import dao.UserDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.beans.User;
import java.sql.Timestamp;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");

        // Validazione server-side
        if (nome == null || cognome == null || email == null || password == null || nome.isEmpty() || cognome.isEmpty() || email.isEmpty() || password.isEmpty()) {
            response.sendRedirect("register.jsp?error=empty_fields");
            return;
        }
        if (!password.equals(confirmPassword)) {
            response.sendRedirect("register.jsp?error=password_mismatch");
            return;
        }

        // Creazione utente
        User newUser = new User();
        newUser.setNome(nome);
        newUser.setCognome(cognome);
        newUser.setEmail(email);
        newUser.setPasswordHash(PasswordUtils.hashPassword(password));
        newUser.setIsAdmin(false);  // Di default l'utente non è un admin

        if (UserDAO.register(newUser)) {
            response.sendRedirect("login.jsp?success=registration");
        } else {
            response.sendRedirect("register.jsp?error=db_error");
        }
    }
}
