<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
  <meta charset="UTF-8">
  <title>Login - Libreria</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />
  <link rel="stylesheet" href="css/login.css" />
  <link rel="stylesheet" href="css/header.css" />
</head>
<body>

<!-- Header -->
<header>
  <%@ include file="header.jsp" %>
</header>

<main class="login-wrapper">
  <div class="login-box">
    <h2 class="login-title">Accedi al tuo account</h2>
    <form action="login" method="post" class="login-form">

      <div class="form-group">
        <label for="email">Email o username</label>
        <input type="text" id="email" name="email" required />
      </div>

      <div class="form-group">
        <label for="password">Password</label>
        <input type="password" id="password" name="password" required />
      </div>

      <button type="submit" class="btn-submit">Accedi</button>
    </form>

    <p class="register-text">
      Non hai un account?
      <a href="register.jsp">Registrati</a>
    </p>
  </div>
</main>


<footer class="footer">
  <%@ include file="footer.jsp" %>
</footer>

<script src="js/dropdown.js"></script>

</body>
</html>
