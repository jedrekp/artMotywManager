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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<c:url value="/static/css/bootstrap.css" />" rel="stylesheet"/>
    <link href="<c:url value="/static/css/motyw.css" />" rel="stylesheet"/>
</head>
<body>

<jsp:include page="navbar.jsp"/>

<div class="container text-center">

    <div class="row bg-greylike py-2 mx-auto">

        <div class="col-md-9 col-lg-6 bg-midyellow border-darkblue mt-5 mx-auto">

            <h2 class="my-3">Wyszukaj ubrania</h2>

            <form action="<c:url value="/clothing/clothingSearchResults"/>">

                <div class="input-group col-sm-8 mx-auto mt-3">
                    <div class="input-group-prepend">
                        <div class="input-group-text bg-darkblue border-0">Cena</div>
                    </div>
                    <input type="text" name="priceMin" class="form-control" pattern="[+]?[0-9]*[.,]?[0-9]+" placeholder="min"
                           oninvalid="this.setCustomValidity('nieprawidłowy format ceny')" oninput="this.setCustomValidity('')">
                    <div class="input-group-prepend">
                        <div class="input-group-text bg-darkblue border-0"> -</div>
                    </div>
                    <input type="text" name="priceMax" class="form-control" pattern="[+]?[0-9]*[.,]?[0-9]+" placeholder="max"
                           oninvalid="this.setCustomValidity('nieprawidłowy format ceny')" oninput="this.setCustomValidity('')">
                </div>

                <div class="input-group col-sm-8 mx-auto mt-3">
                    <div class="input-group-prepend">
                        <div class="input-group-text bg-darkblue border-0 text-light">Typ ubrania</div>
                    </div>
                    <select name="clothingType" class="custom-select">
                        <option value="any">wszystkie</option>
                        <c:forEach items="${clothingTypes}" var="type">
                            <option value="${type}">${type.typeName}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="input-group col-sm-8 mx-auto mt-3">
                    <div class="input-group-prepend">
                        <div class="input-group-text bg-darkblue border-0 text-light">Rozmiar</div>
                    </div>
                    <select name="size" class="custom-select">
                        <option value="any">wszystkie</option>
                        <c:forEach items="${clothingSizes}" var="size">
                            <option value="${size}">${size.sizeName}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="input-group col-sm-8 mx-auto mt-3">
                    <div class="input-group-prepend">
                        <div class="input-group-text bg-darkblue border-0 text-light">Motyw</div>
                    </div>
                    <select name="theme" class="custom-select">
                        <option value="any">wszystkie</option>
                        <c:forEach items="${clothingThemes}" var="theme">
                            <option value="${theme}">${theme.themeName}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="input-group col-sm-8 mx-auto mt-3">
                    <div class="input-group-prepend">
                        <div class="input-group-text bg-darkblue border-0 text-light">Model kroju</div>
                    </div>
                    <input type="text" name="cutType" class="form-control">
                </div>

                <div class="input-group col-sm-8 mx-auto mt-3">
                    <div class="input-group-prepend">
                        <div class="input-group-text bg-darkblue border-0 text-light">Dostępność</div>
                    </div>
                    <select name="availability" class="custom-select">
                        <option value="any">wszystkie</option>
                        <c:forEach items="${productAvailability}" var="availiability">
                            <option value="${availiability}">${availiability.availabilityStatus}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="input-group col-sm-8 mx-auto my-4">
                    <button class="btn-lg btn-warning border-darkblue bg-lightyellow font-weight-bold mb-2 mx-auto" type="submit">Szukaj</button>
                </div>

            </form>

        </div>
    </div>
</div>
</body>
</html>