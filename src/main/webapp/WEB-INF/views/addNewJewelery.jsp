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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<c:url value="/static/css/bootstrap.css" />" rel="stylesheet"/>
    <link href="<c:url value="/static/css/asdasd.css" />" rel="stylesheet"/>
</head>
<body>


<div class="wrapper">

    <jsp:include page="navbar.jsp"/>

    <div class="container add-form-container">
        <div class="top-border"></div>
        <form:form method="POST" modelAttribute="jewelery" enctype="multipart/form-data">
            <h2>Nowa biżuteria</h2>
            <form:hidden path="availability" value="${availability}"/>
            Identyfikator : <form:input path="id"/>
            <form:errors path="id" cssClass="error"/><br/>
            Cena : <form:input path="price"/>
            <form:errors path="price" cssClass="error"/><br/>
            Rodzaj : <form:select path="jeweleryType">
            <c:forEach items="${jeweleryTypes}" var="type">
                <form:option value="${type}">${type.typeName}</form:option>
            </c:forEach>
        </form:select><br/>
            Tworzywo : <form:select path="substance">
            <c:forEach items="${jewelerySubstances}" var="substance">
                <form:option value="${substance}">${substance.substanceName}</form:option>
            </c:forEach>
        </form:select><br/>
            <form:textarea path="description" rows="4" cols="25" placeholder="Opis produktu - opcjonalne"/>
            <form:errors path="description" cssClass="error"/><br/>
            <label for="file-upload2" class="custom-file-upload">Dodaj zdjęcie</label>
            <form:input path="imageFile" type="file" id="file-upload2"/>
            <form:errors path="imageFile" cssClass="error"/><br/>
            <button type="submit">Dodaj</button>
        </form:form>
    </div>

</div>
</body>
</html>
