package controller;

import dao.FeedbackDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.beans.Feedback;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/feedback")
public class FeedbackServlet extends HttpServlet {

    private final FeedbackDAO feedbackDAO = new FeedbackDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Legge il corpo della richiesta JSON
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }
        String jsonInput = sb.toString();

        // Parsing manuale del JSON
        String name = estraiValore(jsonInput, "name");
        String email = estraiValore(jsonInput, "email");
        String ratingStr = estraiValore(jsonInput, "rating");
        String comments = estraiValore(jsonInput, "comments");

        int rating = 0;
        try {
            rating = Integer.parseInt(ratingStr);
        } catch (NumberFormatException e) {
            rating = 0; // valore di default in caso di errore
        }

        // Crea oggetto Feedback
        Feedback feedback = new Feedback(name, email, rating, comments);

        // Salva il feedback nel database
        feedbackDAO.saveFeedback(feedback);

        // Risposta JSON al client
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        out.print("{\"status\":\"success\", \"message\":\"Feedback salvato con successo!\"}");
        out.flush();
    }


    private String estraiValore(String json, String campo) {
        String cerca = "\"" + campo + "\":";
        int start = json.indexOf(cerca);
        if (start == -1) return "";

        start += cerca.length();

        if (json.charAt(start) == '"') {
            // Valore stringa
            start += 1;
            int end = json.indexOf("\"", start);
            if (end == -1) return "";
            return json.substring(start, end);
        } else {
            // Valore numerico
            int end = json.indexOf(",", start);
            if (end == -1) {
                end = json.indexOf("}", start);
            }
            if (end == -1) return "";
            return json.substring(start, end).trim();
        }
    }
}
