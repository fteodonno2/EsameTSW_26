<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.beans.User" %>

<nav class="header-nav">
  <h1 class="header-title">Il Portale Delle Pagine</h1>

  <ul class="header-menu">
    <li><a href="index">Home</a></li>

    <!-- Dropdown Categorie -->
    <li class="header-dropdown">
      <button id="categorieDropdownBtn" type="button" class="dropdown-button">
        Categorie
        <span class="material-icons dropdown-icon">expand_more</span>
      </button>

      <ul id="categorieDropdown" class="dropdown-menu">
        <li><button type="button" onclick="submitCategoria('Thriller')">Thriller</button></li>
        <li><button type="button" onclick="submitCategoria('Avventura')">Avventura</button></li>
        <li><button type="button" onclick="submitCategoria('Horror')">Horror</button></li>
        <li><button type="button" onclick="submitCategoria('Fantascienza')">Fantascienza</button></li>
        <li><button type="button" onclick="submitCategoria('Fantasy')">Fantasy</button></li>
        <li><button type="button" onclick="submitCategoria('Animazione')">Animazione</button></li>
        <li><button type="button" onclick="submitCategoria('Romanzo')">Romanzo</button></li>
        <li><button type="button" onclick="submitCategoria('Drammatico')">Drammatico</button></li>
      </ul>
    </li>

    <li><a href="novita">Novità</a></li>
    <li><a href="contatti.jsp">Contatti</a></li>
  </ul>

  <div class="header-actions">
    <form action="product-list" method="post" class="search-form">
      <input name="q" type="text" placeholder="Cerca un libro..." class="search-input" />
      <button type="submit" class="search-button">Cerca</button>
    </form>

    <%
      session = request.getSession(false);
      User user = (session != null) ? (User) session.getAttribute("user") : null;
    %>

    <% if (user != null) { %>
    <div class="user-box">
      <span class="user-greeting">Benvenuto, <%= user.getNome() %>!</span>
      <a href="logout" class="auth-button">Logout</a>
    </div>
    <% } else { %>
    <a href="login.jsp" class="auth-button">Login</a>
    <% } %>

    <a href="cart" class="cart-icon">
      <span class="material-icons">shopping_cart</span>
    </a>
  </div>

  <form id="categoriaForm" action="product-list" method="post" class="hidden-form">
    <input type="hidden" name="q" id="categoriaInput" />
  </form>
</nav>
