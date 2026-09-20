<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Risultati Ricerca</title>
</head>
<body>
<h2>Risultati per: "<c:out value="${query}"/>"</h2>

<c:if test="${empty risultati}">
    <p>Nessun libro trovato.</p>
</c:if>

<c:forEach var="p" items="${risultati}">
    <div style="border: 1px solid #ccc; padding: 10px; margin-bottom: 10px;">
        <h3><c:out value="${p.nome}"/></h3>
        <p><c:out value="${p.descrizione}"/></p>
        <p>Categoria: <c:out value="${p.categoria}"/></p>
        <p>Prezzo: €<c:out value="${p.prezzo}"/></p>
    </div>
</c:forEach>
</body>
</html>
