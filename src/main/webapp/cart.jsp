<%@ page import="java.util.*, model.beans.Product, model.beans.Cart" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  Cart cart = (Cart) session.getAttribute("cart");
  List<Product> prodotti = (cart != null) ? cart.getProducts() : new ArrayList<>();
%>

<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <title>Carrello - Libreria</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />
  <link rel="stylesheet" href="css/cart.css" />
  <link rel="stylesheet" href="css/header.css" />
</head>

<body>

<!-- Header -->
<header>
  <%@ include file="header.jsp" %>
</header>

<div class="container">
  <div class="cart-header">
    <a href="product-list" class="btn-back">← Ritorna al Catalogo</a>
    <h2>Il tuo Carrello</h2>
  </div>

  <%
    if (prodotti.isEmpty()) {
  %>
  <div class="empty-cart">
    Il tuo carrello è vuoto.
  </div>
  <%
  } else {
    double totale = 0.0;
  %>

  <table class="cart-table">
    <thead>
    <tr>
      <th>Titolo</th>
      <th>Prezzo</th>
      <th>Quantità</th>
      <th>Totale</th>
      <th>Azioni</th>
    </tr>
    </thead>
    <tbody>
    <%
      for (Product prodotto : prodotti) {
        int productId = prodotto.getId();
        int quantity = cart.getItems().get(productId);
        double prezzo = prodotto.getPrezzo().doubleValue();
        double totaleRiga = prezzo * quantity;
        totale += totaleRiga;
    %>
    <tr>
      <td><%= prodotto.getNome() %></td>
      <td>€ <%= String.format("%.2f", prezzo) %></td>
      <td><%= quantity %></td>
      <td>€ <%= String.format("%.2f", totaleRiga) %></td>
      <td>
        <form action="removeFromCart" method="post">
          <input type="hidden" name="productId" value="<%= prodotto.getId() %>" />
          <button type="submit" class="btn-remove">Rimuovi</button>
        </form>
      </td>
    </tr>
    <% } %>
    </tbody>
  </table>

  <div class="cart-total">
    <h3>Totale: € <%= String.format("%.2f", totale) %></h3>
    <form action="checkout" method="post" class="checkout-form">
      <button type="submit" class="btn-checkout">
        Procedi al pagamento
      </button>
    </form>
  </div>

  <%
    }
  %>

</div>

<!-- Footer -->
<footer class="footer">
  <%@ include file="footer.jsp" %>
</footer>

<script src="js/dropdown.js"></script>

</body>
</html>
