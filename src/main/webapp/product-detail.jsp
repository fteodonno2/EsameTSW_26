<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <title>${book.nome} - Dettagli Libro</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />
  <link rel="stylesheet" href="css/product-detail.css" />
  <link rel="stylesheet" href="css/header.css" />
</head>
<body>

<!-- Header -->
<header>
  <%@ include file="header.jsp" %>
</header>

<!-- Book Detail Section -->
<div class="container">
  <div class="card">
    <div class="image-section">
      <img src="${book.immagine}" alt="${book.nome}" />
    </div>
    <div class="info-section">
      <h1>${book.nome}</h1>
      <p class="description">${book.descrizione}</p>
      <p class="price">Prezzo: € ${book.prezzo}</p>
      <p class="category">Categoria: ${book.categoria}</p>

      <form action="addToCart" method="post">
        <input type="hidden" name="productId" value="${book.id}" />
        <button type="submit" class="add-to-cart-btn">Aggiungi al carrello</button>
      </form>
    </div>
  </div>
</div>

<!-- Footer -->
<footer class="site-footer">
  <p>©️ 2025 Il Portale Delle Pagine - Tutti i diritti riservati</p>
  <p class="contact">Contattaci: info@ilportaledellepagine.it | +39 123 456 7890</p>
</footer>

<script src="js/dropdown.js"></script>

</body>
</html>
