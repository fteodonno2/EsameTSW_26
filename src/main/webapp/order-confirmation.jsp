<%@ page import="java.util.*, model.beans.*, dao.*" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
  <meta charset="UTF-8">
  <title>Conferma Ordine</title>

  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />
  <link rel="stylesheet" href="css/order-confirmation.css" />
  <link rel="stylesheet" href="css/header.css" />
</head>
<body>

<!-- Header -->
<header>
  <%@ include file="header.jsp" %>
</header>

<%
  String orderIdStr = request.getParameter("orderId");
  if (orderIdStr != null) {
    int orderId = Integer.parseInt(orderIdStr);
    List<OrderItem> items = OrderItemDAO.getItemsByOrderId(orderId);
%>

<div class="order-container">
  <h1 class="order-title">✅ Grazie per il tuo ordine!</h1>
  <p class="order-message">Il tuo ordine numero <strong><%= orderId %></strong> è stato ricevuto con successo. Ecco i dettagli:</p>

  <h2 class="summary-title">Riepilogo Ordine</h2>

  <table class="order-table">
    <thead>
    <tr>
      <th>Prodotto</th>
      <th>Quantità</th>
      <th>Prezzo unitario</th>
      <th>Totale</th>
    </tr>
    </thead>
    <tbody>
    <%
      BigDecimal totale = BigDecimal.ZERO;
      for (OrderItem item : items) {
        Product p = ProductDAO.getById(item.getProductId());
        BigDecimal itemTotal = item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
        totale = totale.add(itemTotal);
    %>
    <tr>
      <td><%= (p != null) ? p.getNome() : "Prodotto non disponibile" %></td>
      <td><%= item.getQuantity() %></td>
      <td><%= String.format("€ %.2f", item.getPrice()) %></td>
      <td><%= String.format("€ %.2f", itemTotal) %></td>
    </tr>
    <% } %>
    </tbody>
  </table>

  <div class="order-total">
    <span class="label">Totale Ordine:</span>
    <span class="amount"><%= String.format("€ %.2f", totale) %></span>
  </div>

  <a href="index" class="home-button">Torna alla Home</a>
</div>

<div class="feedback-container">
  <p style="text-align: center;">
    <a href="feedback.jsp"> Hai un minuto? Lascia un feedback sulla tua esperienza:</a>
  </p>
</div>

<!-- Footer -->
<footer class="site-footer">
  <%@ include file="footer.jsp" %>
</footer>

<% } else { %>
<p class="error">Errore: ID ordine mancante.</p>
<% } %>

<script src="js/dropdown.js"></script>
</body>
</html>
