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
    <link href="<c:url value="/static/css/amm.css" />" rel="stylesheet"/>
</head>
<body>

<div class="wrapper">

    <jsp:include page="navbar.jsp"/>

    <c:if test="${not empty clothing}">
    <div class="container add-form-container">
        <div class="top-border"></div>

            <%--@elvariable id="clothing" type="motyw.art.artMotywManager.domain.Clothing"--%>
        <form:form method="POST" modelAttribute="clothing" enctype="multipart/form-data">
            <h2>EDYCJA PRODUKTU - <c:out value="${clothing.id}"/></h2><br/>
            <c:if test="${not empty clothing.imageData}">
                <form:hidden path="imageData"/>
            </c:if>
            <form:hidden path="availability"/>
            <form:hidden path="saleDate"/>
            Cena: <form:input path="price"/>
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
            <label for="file-upload3" class="custom-file-upload">Zmień zdjęcie</label>
            <form:input path="imageFile" type="file" id="file-upload3"/>
            <form:errors path="imageFile" cssClass="error"/><br/>
            <button type="submit">Edytuj</button>
        </form:form>
    </div>
    </c:if>

    <c:if test="${not empty jewelery}">
    <div class="container add-form-container">
        <div class="top-border"></div>

            <%--@elvariable id="jewelery" type="motyw.art.artMotywManager.domain.Jewelery"--%>
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
    </c:if>


</body>
</html>