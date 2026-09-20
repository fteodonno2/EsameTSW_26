<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Il Portale Delle Pagine - Il tuo nido di lettura</title>

    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet"/>
    <link rel="stylesheet" href="css/index.css"/>
    <link rel="stylesheet" href="css/header.css"/>
</head>
<body>

<!-- Header -->
<header>
    <%@ include file="header.jsp" %>
</header>


<div class="wrapper">
    <!-- Hero Section -->
    <section class="hero">
        <div class="hero-content">
            <h1>Scopri migliaia di libri</h1>
            <p>Romanzi, manuali, bestseller e molto altro con spedizione gratuita</p>
            <a href="product-list" class="cta-button">Sfoglia il catalogo</a>
        </div>
    </section>

    <!-- Bestseller Section -->
    <section class="bestseller">
        <h2>🌟 Bestseller del momento</h2>
        <div class="book-grid">
            <c:forEach var="libro" items="${bestseller}" varStatus="status">
                <c:if test="${status.index < 3}">
                    <div class="book-card">
                        <img src="${libro.immagine}" alt="${libro.nome}">
                        <div class="book-info">
                            <h3>${libro.nome}</h3>
                            <p class="descrizione">${libro.descrizione}</p>
                            <p class="categoria">Categoria: ${libro.categoria}</p>
                            <p class="prezzo">€ ${libro.prezzo}</p>
                        </div>
                        <div class="book-footer">
                            <a href="product-detail?id=${libro.id}" class="details-button">Dettagli</a>
                        </div>
                    </div>
                </c:if>
            </c:forEach>
        </div>
    </section>


</div>

<!-- Footer -->
<footer class="site-footer">
    <%@ include file="footer.jsp" %>
</footer>


<script src="js/dropdown.js"></script>

</body>
</html>
