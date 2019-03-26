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
    <title>AMM - wyszukaj biżuterię</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<c:url value="/static/css/bootstrap.css" />" rel="stylesheet"/>
    <link href="<c:url value="/static/css/asdasd.css" />" rel="stylesheet"/>
</head>
<body>


<div class="wrapper">

    <jsp:include page="navbar.jsp"/>

    <div class="container form-container">
        <div class="top-border"></div>
        <form action="<c:url value="/jewelery/jewelerySearchResults"/>">
            <h3>Wyszukaj biżuterię</h3>
            Cena : <input type="text" style="width: 25%" name="priceMin" placeholder="min" pattern="[+]?[0-9]*[.,]?[0-9]+"
                          oninvalid="this.setCustomValidity('nieprawidłowy format ceny')" oninput="this.setCustomValidity('')">
            <input type="text" style="width: 25%" name="priceMax" placeholder="max" pattern="[+]?[0-9]*[.,]?[0-9]+"
                   oninvalid="this.setCustomValidity('nieprawidłowy format ceny')" oninput="this.setCustomValidity('')"><br/>
            Rodzaj : <select name="jeweleryType">
            <option value="any">wszystkie</option>
            <c:forEach items="${jeweleryTypes}" var="type">
                <option value="${type}">${type.typeName}</option>
            </c:forEach>
        </select><br/>
            Dostępność : <select name="availability">
            <option value="any">wszystkie</option>
            <c:forEach items="${productAvailability}" var="availiability">
                <option value="${availiability}">${availiability.availabilityStatus}</option>
            </c:forEach>
        </select><br/>
            Tworzywo : <select name="substance">
            <option value="any">wszystkie</option>
            <c:forEach items="${jewelerySubstances}" var="substance">
                <option value="${substance}">${substance.substanceName}</option>
            </c:forEach>
        </select><br/>
            <button type="submit">Szukaj</button>
        </form>
    </div>
</div>
</body>
</html>
