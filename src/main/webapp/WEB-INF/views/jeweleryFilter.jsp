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
    <title>AMM - wyszukaj bizuterie</title>
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

            <h2 class="my-3">Wyszukaj biżuterie</h2>

            <form action="<c:url value="/jewelery/jewelerySearchResults"/>">

                <div class="input-group col-sm-8 mx-auto mt-3">
                    <div class="input-group-prepend">
                        <div class="input-group-text bg-darkblue border-0 text-light">Cena</div>
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
                        <div class="input-group-text bg-darkblue border-0 text-light">Typ biżuterii</div>
                    </div>
                    <select name="jeweleryType" class="custom-select">
                        <option value="any">wszystkie</option>
                        <c:forEach items="${jeweleryTypes}" var="type">
                            <option value="${type}">${type.typeName}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="input-group col-sm-8 mx-auto mt-3">
                    <div class="input-group-prepend">
                        <div class="input-group-text bg-darkblue border-0 text-light">Tworzywo</div>
                    </div>
                    <select name="substance" class="custom-select">
                        <option value="any">wszystkie</option>
                        <c:forEach items="${jewelerySubstances}" var="substance">
                            <option value="${substance}">${substance.substanceName}</option>
                        </c:forEach>
                    </select>
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