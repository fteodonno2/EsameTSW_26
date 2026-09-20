<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>Contatti</title>

  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />
  <link rel="stylesheet" href="css/contatti.css" />
  <link rel="stylesheet" href="css/header.css" />
</head>

<body>
<header>
  <%@ include file="header.jsp" %>
</header>

<section>
  <h2>Chi siamo</h2>
  <p>
    <strong>Il Portale Delle Pagine</strong> è una libreria online dedicata a chi ama perdersi tra le righe.
    Offriamo una vasta selezione di romanzi, saggi, manuali e molto altro, con spedizioni rapide e supporto clienti sempre disponibile.
  </p>

  <h3>📍 Sede</h3>
  <p class="light">Via della Cultura 42, 00100 Roma (RM)</p>

  <h3>📞 Contatti</h3>
  <p class="light">Telefono: +39 06 123 4567</p>
  <p class="light">Email: info@ilportaledellepagine.it</p>

  <h3>🕒 Orari</h3>
  <p class="light">Lunedì - Venerdì: 09:00 - 18:00</p>
  <p>Sabato: 09:00 - 13:00</p>
</section>

<footer class="site-footer">
  <%@ include file="footer.jsp" %>
</footer>

<script src="js/dropdown.js"></script>

</body>
</html>
