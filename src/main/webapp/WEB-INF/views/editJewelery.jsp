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
    <title>AMM - Edycja produktu</title>
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

        <form:form method="post" modelAttribute="jewelery" enctype="multipart/form-data">
            <h2>EDYCJA PRODUKTU - <c:out value="${jewelery.id}"/></h2><br/>
            <c:if test="${not empty jewelery.imageData}">
                <form:hidden path="imageData"/>
            </c:if>
            <form:hidden path="availability"/>
            <form:hidden path="saleDate"/>
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
            <label for="file-upload4" class="custom-file-upload">Zmień zdjęcie</label>
            <form:input path="imageFile" type="file" id="file-upload4"/>
            <form:errors path="imageFile" cssClass="error"/><br/>
            <button type="submit">Edytuj</button>
        </form:form>
    </div>

</body>
</html>