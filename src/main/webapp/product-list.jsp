<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <title>Catalogo Libri - Libreria</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />
  <link rel="stylesheet" href="css/product-list.css" />
  <link rel="stylesheet" href="css/header.css" />
</head>

<body>

<!-- Header -->
<header class="site-header">
  <%@ include file="header.jsp" %>
</header>

<div class="container">
  <h1>Catalogo Libri</h1>

  <div class="grid">
    <c:forEach var="book" items="${prodotti}">
      <div class="product-card">
        <img src="${book.immagine}" alt="${book.nome}" />
        <div class="product-info">
          <h2>${book.nome}</h2>
          <p class="description">${book.descrizione}</p>
          <p class="category">Categoria: ${book.categoria}</p>
          <p class="price">€ ${book.prezzo}</p>
        </div>
        <div class="product-footer">
          <a href="product-detail?id=${book.id}">Dettagli</a>
        </div>
      </div>
    </c:forEach>

    <c:if test="${empty prodotti}">
      <p class="no-products">⚠ Nessun prodotto trovato.</p>
    </c:if>
  </div>
</div>

<script src="js/dropdown.js"></script>

</body>

</html>
