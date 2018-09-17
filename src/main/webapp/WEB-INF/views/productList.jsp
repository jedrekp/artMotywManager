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
    <title>AMM - Lista produktów</title>
    <link href="<c:url value="/static/css/amm.css" />" rel="stylesheet"/>
</head>
<body>

<div class="wrapper">

    <jsp:include page="navbar.jsp"/>

    <div class="container">
        <div class="top-border"></div>

        <h2 id="list-header">Liczba produktów spełniających kryteria - ${fn:length(productList)} </h2>

        <c:forEach items="${productList}" var="product">
            <div class="list-item">

                <div class="list-image">
                    <c:if test="${not empty product.imageData}">
                        <img src="<c:url value="/image/displayImage?id=${product.id}"/>" height="150" width="98"/>
                    </c:if>
                    <c:if test="${empty product.imageData}">
                        <img src="${pageContext.request.contextPath}/static/images/artMotywLogo.jpg" height="150" width="98">
                    </c:if>
                </div>

                <c:if test="${product['class'].name.equals('motyw.art.artMotywManager.domain.Jewelery')}">
                    <table>
                        <tr>
                            <th>Kategoria</th>
                            <th>Identyfikator</th>
                            <th>Cena</th>
                            <th>Rodzaj</th>
                            <th>Tworzywo</th>
                            <th>Dostępność</th>
                        </tr>
                        <tr>
                            <td>biżuteria</td>
                            <td><c:out value="${product.id}"/></td>
                            <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${product.price}"/> zł</td>
                            <td><c:out value="${product.jeweleryType.typeName}"/></td>
                            <td><c:out value="${product.substance.substanceName}"/></td>
                            <td><c:out value="${product.availability.availabilityStatus}"/></td>
                        </tr>
                    </table>
                </c:if>

                <c:if test="${product['class'].name.equals('motyw.art.artMotywManager.domain.Clothing')}">
                    <table>
                        <tr>
                            <th>Kategoria</th>
                            <th>Identyfikator</th>
                            <th>Cena</th>
                            <th>Rozmiar</th>
                            <th>Rodzaj</th>
                            <th>Motyw</th>
                            <th>Model</th>
                            <th>Dostępność</th>
                        </tr>
                        <tr>
                            <td>ubranie</td>
                            <td><c:out value="${product.id}"/></td>
                            <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${product.price}"/> zł</td>
                            <td><c:out value="${product.size.sizeName}"/></td>
                            <td><c:out value="${product.clothingType.typeName}"/></td>
                            <td><c:out value="${product.theme.themeName}"/></td>
                            <td><c:out value="${product.cutType}"/></td>
                            <td><c:out value="${product.availability.availabilityStatus}"/></td>
                        </tr>
                    </table>
                </c:if>

                <div class="list-buttons">
                    <form action="<c:url value="/product/${product.id}"/>">
                        <button type="submit" class="enter-button">Strona produktu</button>
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
                        <button type="submit" class="delete-button"onclick="return confirm('Czy na pewno chcesz usunąć produkt?');">Usuń produkt</button>
                    </form>
                </div>

            </div>
        </c:forEach>
    </div>
</div>

</body>
</html>