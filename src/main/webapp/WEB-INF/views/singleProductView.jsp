<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    response.setCharacterEncoding("UTF-8");
    request.setCharacterEncoding("UTF-8");
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>AMM - Nowy produkt</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<c:url value="/static/css/bootstrap.css" />" rel="stylesheet"/>
    <link href="<c:url value="/static/css/motyw.css" />" rel="stylesheet"/>
</head>
<body>

<jsp:include page="navbar.jsp"/>

<div class="container text-center">

    <div class="row w-75 mx-auto my-5 pt-3 pb-3 bg-midyellow border-darkblue">

        <h2 class="my-3 pb-3 w-75 mx-auto font-weight-bold">Produkt -
            <c:out value="${product.id} "/>


            <c:if test="${product.availability == 'AVAILABLE'}">
                <span class="text-success"><c:out value="${fn:toUpperCase(product.availability.availabilityStatus)}"/></span>
            </c:if>

            <c:if test="${product.availability == 'SOLD'}">
                <span class="text-danger"><c:out value="${fn:toUpperCase(product.availability.availabilityStatus)}"/> </span>
            </c:if>
        </h2>

        <div class="col-md-6">

            <c:if test="${not empty product.imageData}">
                <img src="<c:url value="/image/displayImage?id=${product.id}"/>" height="465" width="300" class="img-thumbnail"/>
            </c:if>

            <c:if test="${empty product.imageData}">
                <img src="${pageContext.request.contextPath}/static/images/artMotywLogo.jpg" height="465" width="300"
                     class="img-thumbnail"/>
            </c:if>

        </div>

        <c:if test="${product['class'].name.equals('motyw.art.artMotywManager.domain.Clothing')}">

            <div class="col mt-2 my-md-3 order-md-3">

                <div class="row no-gutters mx-md-5">

                    <div class="col-md-2 px-1">
                        <table class="table table-borderless border-darkblue table-striped table-light bg-midyellow text-center mt-3">
                            <tr>
                                <td>Kategoria</td>
                            </tr>
                            <tr>
                                <td>Ubranie</td>
                            </tr>
                        </table>
                    </div>

                    <div class="col-md-2 px-1">
                        <table class="table table-borderless border-darkblue table-striped table-light bg-midyellow text-center mt-3">
                            <tr>
                                <td>Cena</td>
                            </tr>
                            <tr>
                                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${product.price}"/> zł</td>
                            </tr>
                        </table>
                    </div>

                    <div class="col-md-2 px-1">
                        <table class="table table-borderless border-darkblue table-striped table-light bg-midyellow text-center mt-3">
                            <tr>
                                <td>Typ</td>
                            </tr>
                            <tr>
                                <td><c:out value="${product.clothingType.typeName}"/></td>
                            </tr>
                        </table>
                    </div>

                    <div class="col-md-2 px-1">
                        <table class="table table-borderless border-darkblue table-striped table-light bg-midyellow text-center mt-3">
                            <tr>
                                <td>Rozmiar</td>
                            </tr>
                            <tr>
                                <td><c:out value="${product.size.sizeName}"/></td>
                            </tr>
                        </table>
                    </div>

                    <div class="col-md-2 px-1">
                        <table class="table table-borderless border-darkblue table-striped table-light bg-midyellow text-center mt-3">
                            <tr>
                                <td>Motyw</td>
                            </tr>
                            <tr>
                                <td><c:out value="${product.theme.themeName}"/></td>
                            </tr>
                        </table>
                    </div>

                    <div class="col-md-2 px-1">
                        <table class="table table-borderless border-darkblue table-striped table-light bg-midyellow text-center mt-3">
                            <tr>
                                <td>Model</td>
                            </tr>
                            <tr>
                                <td><c:out value="${product.cutType}"/></td>
                            </tr>
                        </table>
                    </div>

                </div>
            </div>
        </c:if>

        <c:if test="${product['class'].name.equals('motyw.art.artMotywManager.domain.Jewelery')}">

            <div class="col mt-2 my-md-3 order-md-3">

                <div class="row no-gutters mx-md-5">

                    <div class="col-md-3 px-1">
                        <table class="table table-borderless border-darkblue table-striped table-light bg-midyellow text-center mt-3">
                            <tr>
                                <td>Kategoria</td>
                            </tr>
                            <tr>
                                <td>Biżuteria</td>
                            </tr>
                        </table>
                    </div>

                    <div class="col-md-3 px-1">
                        <table class="table table-borderless border-darkblue table-striped table-light bg-midyellow text-center mt-3">
                            <tr>
                                <td>Cena</td>
                            </tr>
                            <tr>
                                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${product.price}"/> zł</td>
                            </tr>
                        </table>
                    </div>

                    <div class="col-md-3 px-1">
                        <table class="table table-borderless border-darkblue table-striped table-light bg-midyellow text-center mt-3">
                            <tr>
                                <td>Typ</td>
                            </tr>
                            <tr>
                                <td><c:out value="${product.jeweleryType.typeName}"/></td>
                            </tr>
                        </table>
                    </div>

                    <div class="col-md-3 px-1">
                        <table class="table table-borderless border-darkblue table-striped table-light bg-midyellow text-center mt-3">
                            <tr>
                                <td>Tworzywo</td>
                            </tr>
                            <tr>
                                <td><c:out value="${product.substance.substanceName}"/></td>
                            </tr>
                        </table>
                    </div>

                </div>
            </div>
        </c:if>

        <div class="col-md-6 pr-md-5">

            <h4 class="mx-auto w-75 mt-3 mt-lg-0">Opis produktu :</h4>

            <div class="border-darkblue mx-auto w-100 p-3 bg-greylike text-left mb-4" style="min-height: 150px">
                <c:out value="${product.description}"/>
            </div>

            <form action="<c:url value="/product/markAsSold/${product.id}"/>" method="post">

                <c:if test="${product.availability == 'AVAILABLE'}">
                    <button type="submit" class="btn btn-warning border-darkblue bg-lightyellow font-weight-bold w-75 mt-1 mt-md-3"
                            onclick="return confirm
                                    ('Uwaga! Po oznaczeniu produktu jako sprzedany nie można już zmienić jego statusu na dostępny.');">
                        Oznacz jako sprzedany
                    </button>
                </c:if>

                <c:if test="${product.availability == 'SOLD'}">
                    <button type="button" disabled class="btn border-darkblue bg-lightyellow font-weight-bold w-75 mt-1 mt-md-3">
                        Oznacz jako sprzedany
                    </button>
                </c:if>
            </form>

            <c:if test="${product['class'].name.equals('motyw.art.artMotywManager.domain.Jewelery')}">
                <form action="<c:url value="/jewelery/editJewelery/${product.id}"/>" method="get">
                    <button type="submit" class="btn btn-warning border-darkblue bg-lightyellow font-weight-bold w-75 mt-1 mt-lg-3">Edytuj produkt
                    </button>
                </form>
            </c:if>

            <c:if test="${product['class'].name.equals('motyw.art.artMotywManager.domain.Clothing')}">
                <form action="<c:url value="/clothing/editClothing/${product.id}"/>" method="get">
                    <button type="submit" class="btn btn-warning border-darkblue bg-lightyellow font-weight-bold w-75 mt-1 mt-lg-3">Edytuj produkt
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
</div>

</body>
</html>

