<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <title>Lascia un feedback</title>

  <link rel="stylesheet" href="css/feedback.css" />

</head>
<body>

<form class="feedback-form" id="feedbackForm">
  <h2>Lascia il tuo Feedback</h2>

  <label for="name">Nome</label>
  <input type="text" id="name" name="name" required placeholder="Il tuo nome" />

  <label for="email">Email</label>
  <input type="email" id="email" name="email" required placeholder="La tua email" />

  <label for="rating">Valutazione</label>
  <select id="rating" name="rating" required>
    <option value="">-- Seleziona --</option>
    <option value="1">1 - Molto insoddisfatto</option>
    <option value="2">2 - Insoddisfatto</option>
    <option value="3">3 - Neutro</option>
    <option value="4">4 - Soddisfatto</option>
    <option value="5">5 - Molto soddisfatto</option>
  </select>

  <label for="comments">Commenti</label>
  <textarea id="comments" name="comments" placeholder="Scrivi il tuo feedback qui..." required></textarea>

  <button type="submit">Invia Feedback</button>

  <div class="feedback-message" id="feedbackMessage"></div>
</form>



<form action="index" method="get" style="text-align: right; margin-bottom: 20px;"  class="home-button-form" >
  <button type="submit"   class="home-button">
    Torna alla Home
  </button>
</form>

<script src="js/async.js"></script>

</body>
</html>