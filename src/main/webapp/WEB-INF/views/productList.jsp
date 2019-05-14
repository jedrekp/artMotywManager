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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<c:url value="/static/css/bootstrap.css" />" rel="stylesheet"/>
    <link href="<c:url value="/static/css/motyw.css" />" rel="stylesheet"/>
</head>
<body>


<jsp:include page="navbar.jsp"/>

<div class="container text-center py-5">

    <h2 class="font-weight-bold mb-5">Liczba produktów spełniających kryteria - ${fn:length(productList)} </h2>

    <c:forEach items="${productList}" var="product">


        <div class="row w-75 mx-auto mb-5 pt-3 pb-4 bg-midyellow border-darkblue">

            <c:if test="${product['class'].name.equals('motyw.art.artMotywManager.domain.Clothing')}">
                <h3 class="py-0 pb-0 my-0 w-75 mx-auto">Ubranie - <c:out value="${product.id}"/></h3>
            </c:if>

            <c:if test="${product['class'].name.equals('motyw.art.artMotywManager.domain.Jewelery')}">
                <h3 class="py-0 my-0 w-75 mx-auto">Biżuteria - <c:out value="${product.id}"/></h3>
            </c:if>

            <div class="col-lg-4 mt-3 mt-lg-0">

                <c:if test="${not empty product.imageData}">
                    <img src="<c:url value="/image/displayImage?id=${product.id}"/>" height="280" width="180" class="img-thumbnail mb-4 mt-2 mb-lg-2"/>
                </c:if>

                <c:if test="${empty product.imageData}">
                    <img src="${pageContext.request.contextPath}/static/images/artMotywLogo.jpg" height="280" width="180" class="img-thumbnail mb-4 mt-2 mb-lg-2"/>
                </c:if>

            </div>

            <div class="col-lg-4 my-auto">

                <c:if test="${product['class'].name.equals('motyw.art.artMotywManager.domain.Jewelery')}">

                    <table class="table table-striped table-borderless table-light bg-midyellow text-center mt-3">
                        <tr>
                            <td>Dostępność</td>
                            <td><c:out value="${product.availability.availabilityStatus}"/></td>
                        </tr>
                        <tr>
                            <td>Rodzaj</td>
                            <td><c:out value="${product.jeweleryType.typeName}"/></td>
                        </tr>
                        <tr>
                            <td>Tworzywo</td>
                            <td><c:out value="${product.substance.substanceName}"/></td>
                        </tr>
                        <tr>
                            <td>Cena</td>
                            <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${product.price}"/> zł</td>
                        </tr>
                    </table>

                </c:if>

                <c:if test="${product['class'].name.equals('motyw.art.artMotywManager.domain.Clothing')}">

                    <table class="table table-striped table-borderless table-light bg-midyellow text-center mt-3">
                        <tr>
                            <td>Dostępność</td>
                            <td><c:out value="${product.availability.availabilityStatus}"/></td>
                        </tr>
                        <tr>
                            <td>Typ</td>
                            <td><c:out value="${product.clothingType.typeName}"/></td>
                        </tr>
                        <tr>
                            <td>Rozmiar</td>
                            <td><c:out value="${product.size.sizeName}"/></td>
                        </tr>
                        <tr>
                            <td>Motyw</td>
                            <td><c:out value="${product.theme.themeName}"/></td>
                        </tr>
                        <tr>
                            <td>Model</td>
                            <td><c:out value="${product.cutType}"/></td>
                        </tr>
                        <tr>
                            <td>Cena</td>
                            <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${product.price}"/> zł</td>
                        </tr>
                    </table>

                </c:if>

            </div>

            <div class="col-lg-4 my-auto">

                <form action="<c:url value="/product/${product.id}"/>">
                    <button type="submit" class="btn btn-warning border-darkblue bg-lightyellow font-weight-bold w-75 mb-1 mb-lg-3">Strona produktu
                    </button>
                </form>

                <c:if test="${product['class'].name.equals('motyw.art.artMotywManager.domain.Jewelery')}">
                    <form action="<c:url value="/jewelery/editJewelery/${product.id}"/>" method="get">
                        <button type="submit" class="btn btn-warning border-darkblue bg-lightyellow font-weight-bold w-75 my-1 my-lg-3">Edytuj produkt
                        </button>
                    </form>
                </c:if>

                <c:if test="${product['class'].name.equals('motyw.art.artMotywManager.domain.Clothing')}">
                    <form action="<c:url value="/clothing/editClothing/${product.id}"/>" method="get">
                        <button type="submit" class="btn btn-warning border-darkblue bg-lightyellow font-weight-bold w-75 my-1 my-lg-3">Edytuj produkt
                        </button>
                    </form>
                </c:if>

                <form method="post" action="<c:url value="/product/deleteProduct/${product.id}"/>">
                    <button type="submit" class="btn btn-warning border-darkblue bg-lightyellow font-weight-bold w-75 mt-1 mt-lg-3"
                            onclick="return confirm('Czy na pewno chcesz usunąć produkt?');">Usuń produkt
                    </button>
                </form>

            </div>
        </div>
    </c:forEach>
</div>

</body>
</html>