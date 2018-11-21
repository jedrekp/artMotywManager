<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    response.setCharacterEncoding("UTF-8");
    request.setCharacterEncoding("UTF-8");
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>AMM - wyszukaj ubrania</title>
    <link href="<c:url value="/static/css/amm.css" />" rel="stylesheet"/>
</head>
<body>


<div class="wrapper">

    <jsp:include page="navbar.jsp"/>

    <div class="container form-container">
        <div class="top-border"></div>
        <form action="<c:url value="/clothing/clothingSearchResults"/>">
            <h3>Wyszukaj ubrania </h3>
            Cena : <input type="text" name="priceMin" style="width: 25%" pattern="[+]?[0-9]*[.,]?[0-9]+" placeholder="min"
                          oninvalid="this.setCustomValidity('nieprawidłowy format ceny')" oninput="this.setCustomValidity('')">
            <input type="text" name="priceMax" style="width: 25%" pattern="[+]?[0-9]*[.,]?[0-9]+" placeholder="max"
                   oninvalid="this.setCustomValidity('nieprawidłowy format ceny')" oninput="this.setCustomValidity('')"><br/>
            Rodzaj : <select name="clothingType">
            <option value="any">wszystkie</option>
            <c:forEach items="${clothingTypes}" var="type">
                <option value="${type}">${type.typeName}</option>
            </c:forEach>
        </select><br/>
            Rozmiar : <select name="size">
            <option value="any">wszystkie</option>
            <c:forEach items="${clothingSizes}" var="size">
                <option value="${size}">${size.sizeName}</option>
            </c:forEach>
        </select><br/>
            Motyw : <select name="theme">
            <option value="any">wszystkie</option>
            <c:forEach items="${clothingThemes}" var="theme">
                <option value="${theme}">${theme.themeName}</option>
            </c:forEach>
        </select><br/>
            Model kroju : <input type="text" name="cutType"><br/>
            Dostępność : <select name="availability">
            <option value="any">wszystkie</option>
            <c:forEach items="${productAvailability}" var="availiability">
                <option value="${availiability}">${availiability.availabilityStatus}</option>
            </c:forEach>
        </select><br/>
            <button type="submit">Szukaj</button>
        </form>
    </div>

</div>
</body>
</html>
