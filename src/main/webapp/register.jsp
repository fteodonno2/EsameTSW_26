<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Registrazione - Libreria</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet"/>
    <link rel="stylesheet" href="css/register.css"/>
    <link rel="stylesheet" href="css/header.css"/>
</head>
<body>

<!-- Header -->
<header>
    <%@ include file="header.jsp" %>
</header>

<main class="register-wrapper">
    <div class="register-box">
        <h2 class="register-title">Crea un nuovo account</h2>

        <form action="register" method="post" class="register-form">
            <div class="form-group">
                <label for="nome">Nome</label>
                <input type="text" id="nome" name="nome" required/>
            </div>

            <div class="form-group">
                <label for="cognome">Cognome</label>
                <input type="text" id="cognome" name="cognome" required/>
            </div>

            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" id="email" name="email" required/>
            </div>

            <div class="form-group">
                <label for="password">Password</label>
                <input type="password" id="password" name="password" required/>
            </div>

            <div class="form-group">
                <label for="confirm_password">Conferma Password</label>
                <input type="password" id="confirm_password" name="confirm_password" required/>
            </div>

            <button type="submit" class="btn-submit green">Registrati</button>
        </form>

        <p class="register-text">
            Hai già un account?
            <a href="login.jsp">Accedi</a>
        </p>
    </div>
</main>

<footer class="footer">
    <%@ include file="footer.jsp" %>
</footer>

<script src="js/dropdown.js"></script>

</body>
</html>
