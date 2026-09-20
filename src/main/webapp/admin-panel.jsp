<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="model.beans.Product" %>
<%@ page import="model.beans.Order" %>
<%@ page import="dao.ProductDAO" %>

<%
  List<Product> prodotti = ProductDAO.getAllProducts();
  List<Order> ordini = (List<Order>) request.getAttribute("orders");
  String success = request.getParameter("success");
  String error = request.getParameter("error");
%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Admin Panel - Prodotti</title>

  <link rel="stylesheet" type="text/css" href="css/admin-panel.css">
  <script src="js/popolaForm.js"></script>
</head>
<body>

<h1>Gestione Prodotti</h1>

<!-- Messaggi di feedback -->
<% if (success != null) { %>
<div class="message success">✅ <%= success.replace("_", " ") %></div>
<% } else if (error != null) { %>
<div class="message error">❌ <%= error.replace("_", " ") %></div>
<% } %>

<!-- Tabella prodotti -->
<table>
  <thead>
  <tr>
    <th>ID</th>
    <th>Nome</th>
    <th>Prezzo</th>
    <th>Categoria</th>
    <th>Azioni</th>
  </tr>
  </thead>
  <tbody>
  <% for (Product p : prodotti) { %>
  <tr>
    <td><%= p.getId() %></td>
    <td><%= p.getNome() %></td>
    <td><%= p.getPrezzo() %></td>
    <td><%= p.getCategoria() %></td>
    <td>
      <button onclick="popolaForm(
              '<%= p.getId() %>',
              '<%= p.getNome().replace("'", "\\'") %>',
              '<%= p.getDescrizione().replace("'", "\\'") %>',
              '<%= p.getPrezzo() %>',
              '<%= p.getCategoria().replace("'", "\\'") %>',
              '<%= p.getImmagine().replace("'", "\\'") %>'
              )">Modifica</button>

      <form action="deleteProduct" method="post" style="display:inline;">
        <input type="hidden" name="productId" value="<%= p.getId() %>">
        <button type="submit" onclick="return confirm('Sei sicuro di voler eliminare questo prodotto?')">Elimina</button>
      </form>
    </td>
  </tr>
  <% } %>
  </tbody>
</table>

<!-- Tabella ordini -->
<h1>Elenco Ordini</h1>
<table class="orders-table">
  <thead>
  <tr>
    <th>ID Ordine</th>
    <th>ID Utente</th>
    <th>Totale</th>
    <th>Stato</th>
    <th>Data</th>
  </tr>
  </thead>
  <tbody>
  <% for (Order o : ordini) { %>
  <tr>
    <td><%= o.getId() %></td>
    <td><%= o.getUserId() %></td>
    <td>€ <%= String.format("%.2f", o.getTotalPrice()) %></td>
    <td><%= o.getStatus() %></td>
    <td><%= o.getOrderDate() %></td>
  </tr>
  <% } %>
  </tbody>
</table>

<!-- Form di modifica prodotto -->
<div class="form-container">
  <h2>Modifica prodotto</h2>
  <form id="editForm" action="editProduct" method="post">
    <input type="hidden" name="id" id="id">
    <label for="nome">Nome</label>
    <input type="text" name="nome" id="nome" required>

    <label for="descrizione">Descrizione</label>
    <input type="text" name="descrizione" id="descrizione" required>

    <label for="prezzo">Prezzo</label>
    <input type="number" step="0.01" name="prezzo" id="prezzo" required>

    <label for="categoria">Categoria</label>
    <input type="text" name="categoria" id="categoria" required>

    <label for="immagine">URL Immagine</label>
    <input type="text" name="immagine" id="immagine" required>

    <button type="submit">Aggiorna prodotto</button>
  </form>
</div>


<form action="logout" method="get" style="text-align: right; margin-bottom: 20px;">
  <button type="submit" class="bg-primary text-white px-3 py-1 rounded text-sm hover:bg-secondary">
    ⬅ Torna alla Home & Logout
  </button>
</form>


</body>
</html>