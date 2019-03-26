<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    response.setCharacterEncoding("UTF-8");
    request.setCharacterEncoding("UTF-8");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>AMM - produkt <c:out value="${product.id}"/></title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<c:url value="/static/css/bootstrap.css" />" rel="stylesheet"/>
    <link href="<c:url value="/static/css/asdasd.css" />" rel="stylesheet"/>
</head>
<body>

<div class="wrapper">

    <jsp:include page="navbar.jsp"/>

    <div class="container">
        <div class="top-border"></div>
        <h2 id="product-page-header">Produkt -
            <c:out value="${product.id}"/>
            <c:if test="${product.availability == 'AVAILABLE'}">
                <span id="available">&emsp;<c:out value="${fn:toUpperCase(product.availability.availabilityStatus)}"/> </span>
            </c:if>
            <c:if test="${product.availability == 'SOLD'}">
                <span id="sold">&emsp;<c:out value="${fn:toUpperCase(product.availability.availabilityStatus)}"/> </span>
            </c:if>
        </h2>

        <div id="product-label">
            <div class="product-list-image">
                <c:if test="${not empty product.imageData}">
                    <img src="<c:url value="/image/displayImage?id=${product.id}"/>" height="465" width="300"/>
                </c:if>
                <c:if test="${empty product.imageData}">
                    <img src="${pageContext.request.contextPath}/static/images/artMotywLogo.jpg" height="465" width="300">
                </c:if>
            </div>
        </div>

        <div class="product-page-content">

            <c:if test="${product['class'].name.equals('motyw.art.artMotywManager.domain.Clothing')}">
                <table>
                    <tr>
                        <th>Kategoria</th>
                        <th>Cena</th>
                        <th>Rozmiar</th>
                        <th>Rodzaj</th>
                        <th>Motyw</th>
                        <th>Model kroju</th>
                    </tr>
                    <tr>
                        <td>ubranie</td>
                        <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${product.price}"/> zł</td>
                        <td><c:out value="${product.size.sizeName}"/></td>
                        <td><c:out value="${product.clothingType.typeName}"/></td>
                        <td><c:out value="${product.theme.themeName}"/></td>
                        <td><c:out value="${product.cutType}"/></td>
                    </tr>
                </table>
            </c:if>

            <c:if test="${product['class'].name.equals('motyw.art.artMotywManager.domain.Jewelery')}">
                <table>
                    <tr>
                        <th>Kategoria</th>
                        <th>Cena</th>
                        <th>Rodzaj</th>
                        <th>Tworzywo</th>
                    </tr>
                    <tr>
                        <td>biżuteria</td>
                        <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${product.price}"/> zł</td>
                        <td><c:out value="${product.jeweleryType.typeName}"/></td>
                        <td><c:out value="${product.substance.substanceName}"/></td>
                    </tr>
                </table>
            </c:if>

            <fieldset id="description-box">
                <legend> Opis produktu :</legend>
                <c:out value="${product.description}"/>
            </fieldset>

            <form action="<c:url value="/product/markAsSold/${product.id}"/>" method="post">
                <c:if test="${product.availability == 'AVAILABLE'}">
                    <button type="submit" class="sold-button"
                            onclick="return confirm('Uwaga! Po oznaczeniu produktu jako sprzedany nie można już zmienić jego statusu na dostępny.');">
                        Oznacz jako sprzedany
                    </button>
                </c:if>
                <c:if test="${product.availability == 'SOLD'}">
                    <button type="button" disabled id="sold-button-disabled">Oznacz jako sprzedany</button>
                </c:if>
            </form>
            <c:if test="${product['class'].name.equals('motyw.art.artMotywManager.domain.Jewelery')}">
                <form action="<c:url value="/jewelery/editJewelery/${product.id}"/>" method="get">
                    <button type="submit" class="edit-button">Edytuj produkt</button>
                </form>
            </c:if>
            <c:if test="${product['class'].name.equals('motyw.art.artMotywManager.domain.Clothing')}">
                <form action="<c:url value="/clothing/editClothing/${product.id}"/>" method="get">
                    <button type="submit" class="edit-button">Edytuj produkt</button>
                </form>
            </c:if>
            <form method="post" action="<c:url value="/product/deleteProduct/${product.id}"/>">
                <button type="submit" class="delete-button" onclick="return confirm('Czy na pewno chcesz usunąć produkt?');">Usuń produkt</button>
            </form>
        </div>
        <div style="clear: both;"></div>
    </div>
</div>
</body>
</html>

