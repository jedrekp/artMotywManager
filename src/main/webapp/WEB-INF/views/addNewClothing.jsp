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
    <title>AMM - Nowy produkt</title>
    <link href="<c:url value="/static/css/amm.css" />" rel="stylesheet"/>
</head>
<body>


<div class="wrapper">

    <jsp:include page="navbar.jsp"/>

    <div class="container add-form-container">
        <div class="top-border"></div>
        <form:form method="POST" modelAttribute="clothing" enctype="multipart/form-data">
            <h2>Nowe ubranie</h2>
            <form:hidden path="availability" value="${availability}"/>
            Identyfikator : <form:input path="id"/>
            <form:errors path="id" cssClass="error"/><br/>
            Cena : <form:input path="price"/>
            <form:errors path="price" cssClass="error"/><br/>
            Rodzaj : <form:select path="clothingType">
            <c:forEach items="${clothingTypes}" var="type">
                <form:option value="${type}">${type.typeName}</form:option>
            </c:forEach>
        </form:select><br/>
            Rozmiar : <form:select path="size">
            <c:forEach items="${clothingSizes}" var="size">
                <form:option value="${size}">${size.sizeName}</form:option>
            </c:forEach>
        </form:select><br/>
            Motyw : <form:select path="theme">
            <c:forEach items="${clothingThemes}" var="theme">
                <form:option value="${theme}">${theme.themeName}</form:option>
            </c:forEach>
        </form:select><br/>
            Model kroju : <form:input path="cutType"/>
            <form:errors path="cutType" cssClass="error"/><br/>
            <form:textarea path="description" rows="4" cols="25" placeholder="Opis produktu - opcjonalne"/>
            <form:errors path="description" cssClass="error"/><br/>
            <label for="file-upload1" class="custom-file-upload">Dodaj zdjęcie</label>
            <form:input path="imageFile" type="file" id="file-upload1"/>
            <form:errors path="imageFile" cssClass="error"/><br/>
            <button type="submit">Dodaj</button>
        </form:form>
    </div>
</div>
</body>
</html>
