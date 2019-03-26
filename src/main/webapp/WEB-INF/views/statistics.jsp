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
    <title>AMM - statystyki sprzedaży</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<c:url value="/static/css/bootstrap.css" />" rel="stylesheet"/>
    <link href="<c:url value="/static/css/asdasd.css" />" rel="stylesheet"/>
</head>
<body>

    <jsp:include page="navbar.jsp"/>

    <div class="container container-border text-center py-5 px-md-5">

        <h2 class="mb-5 font-weight-bold"><c:out value="${title}"/></h2>

        <h4 class="mt-5 bg-darkblue p-3 text-light w-75 mx-auto">Statystyki ogólne </h4>
        <table class="table striped border-darkblue text-center w-75 mx-auto mb-4">
            <thead class="bg-midyellow">
            <tr>
                <th class="width-one-third">Kategoria</th>
                <th class="width-one-third">Ilość</th>
                <th class="width-one-third">Przychód</th>
            </tr>
            </thead>
            <tr>
                <td>ubrania</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${statistics.CLOTHING_SALES}"/> szt</td>
                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${statistics.CLOTHING_INCOME}"/> zł
                </td>
            </tr>
            <tr class="bg-white">
                <td>biżuteria</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${statistics.JEWELERY_SALES}"/> szt</td>
                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${statistics.JEWELERY_INCOME}"/> zł</td>
            </tr>
            <tr>
                <td>ogółem</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${statistics.TOTAL_SALES}"/> szt</td>
                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${statistics.TOTAL_INCOME}"/> zł</td>
            </tr>
        </table>

        <h4 class="mt-5 bg-darkblue p-3 text-light w-75 mx-auto">Ubrania - statystyki szczegółowe</h4>
        <table class="table striped border-darkblue text-center  w-75 mx-auto mb-4">
            <thead class="bg-midyellow">
            <tr>
                <th class="width-one-third">Rodzaj</th>
                <th class="width-one-third">Ilość</th>
                <th class="width-one-third">Przychód</th>
            </tr>
            </thead>
            <tr>
                <td>sukienki</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${statistics.DRESS_TYPE_SALES}"/> szt</td>
                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${statistics.DRESS_TYPE_INCOME}"/> zł</td>
            </tr>
            <tr class="bg-white">
                <td>spódnice</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${statistics.SKIRT_TYPE_SALES}"/> szt</td>
                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${statistics.SKIRT_TYPE_INCOME}"/> zł</td>
            </tr>
            <tr>
                <td>spodnie</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${statistics.PANTS_TYPE_SALES}"/> szt</td>
                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${statistics.PANTS_TYPE_INCOME}"/> zł</td>
            </tr>
            <tr class="bg-white">
                <td>bluzki</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${statistics.SHIRT_TYPE_SALES}"/> szt</td>
                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${statistics.SHIRT_TYPE_INCOME}"/> zł</td>
            </tr>
            <tr>
                <td>bluzy</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${statistics.SWEATSHIRT_TYPE_SALES}"/> szt</td>
                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${statistics.SWEATSHIRT_TYPE_INCOME}"/> zł</td>
            </tr>
            <tr class="bg-white">
                <td>czapki</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${statistics.HAT_TYPE_SALES}"/> szt</td>
                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${statistics.HAT_TYPE_INCOME}"/> zł</td>
            </tr>
            <tr>
                <td>kurtki</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${statistics.JACKET_TYPE_SALES}"/> szt</td>
                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${statistics.JACKET_TYPE_INCOME}"/> zł</td>
            </tr>
            <tr class="bg-white">
                <td>żakiety</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${statistics.SUIT_TYPE_SALES}"/> szt</td>
                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${statistics.SUIT_TYPE_INCOME}"/> zł</td>
            </tr>
            <tr>
                <td>inne</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${statistics.DIFFERENT_CLOTHING_TYPE_SALES}"/> szt</td>
                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${statistics.DIFFERENT_CLOTHING_TYPE_INCOME}"/> zł</td>
            </tr>
        </table>

        <h4 class="mt-5 bg-darkblue p-3 text-light w-75 mx-auto">Ubrania - statystyki wg rozmiarów</h4>
        <table class="table striped border-darkblue text-center w-75 mx-auto mb-4">
            <thead class="bg-midyellow">
            <tr>
                <th class="width-one-third">Rozmiar</th>
                <th class="width-one-third">Ilość</th>
                <th class="width-one-third">Przychód</th>
            </tr>
            </thead>
            <tr class="bg-white">
                <td>XS</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${statistics.XS_SIZE_SALES}"/> szt</td>
                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${statistics.XS_SIZE_INCOME}"/> zł</td>
            </tr>
            <tr>
                <td>S</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${statistics.S_SIZE_SALES}"/> szt</td>
                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${statistics.S_SIZE_INCOME}"/>zł</td>
            </tr>
            <tr class="bg-white">
                <td>M</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${statistics.M_SIZE_SALES}"/> szt</td>
                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="  ${statistics.M_SIZE_INCOME}"/> zł</td>
            </tr>
            <tr>
                <td>L</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${statistics.L_SIZE_SALES}"/> szt</td>
                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${statistics.L_SIZE_INCOME}"/>zł</td>
            </tr>
            <tr class="bg-white">
                <td>XL</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${statistics.XL_SIZE_SALES}"/> szt</td>
                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${statistics.XL_SIZE_INCOME}"/> zł</td>
            </tr>
            <tr>
                <td>XXL</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${statistics.XXL_SIZE_SALES}"/> szt</td>
                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${statistics.XXL_SIZE_INCOME}"/> zł</td>
            </tr>
            <tr class="bg-white">
                <td>uniwersalny</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${statistics.UNIVERSAL_SIZE_SALES}"/> szt</td>
                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${statistics.UNIVERSAL_SIZE_INCOME}"/> zł</td>
            </tr>
        </table>

        <h4 class="mt-5 bg-darkblue p-3 text-light w-75 mx-auto">Ubrania - statystyki wg motywów</h4>
        <table class="table striped border-darkblue text-center w-75 mx-auto mb-4">
            <thead class="bg-midyellow">
            <tr>
                <th class="width-one-third">Motyw</th>
                <th class="width-one-third">Ilość</th>
                <th class="width-one-third">Przychód</th>
            </tr>
            </thead>
            <tr>
                <td>zwierzęcy</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${statistics.ANIMAL_THEME_SALES}"/> szt</td>
                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${statistics.ANIMAL_THEME_INCOME}"/> zł</td>
            </tr>
            <tr class="bg-white">
                <td>roślinny</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${statistics.FLORAL_THEME_SALES}"/> szt</td>
                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${statistics.FLORAL_THEME_INCOME}"/> zł</td>
            </tr>
            <tr>
                <td>abstrakcja</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${statistics.ABSTRACT_THEME_SALES}"/> szt</td>
                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${statistics.ABSTRACT_THEME_INCOME}"/> zł</td>
            </tr>
            <tr class="bg-white">
                <td>inny</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${statistics.DIFFERENT_THEME_SALES}"/> szt</td>
                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${statistics.DIFFERENT_THEME_INCOME}"/> zł</td>
            </tr>
            <tr>
                <td>brak</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${statistics.NO_THEME_SALES}"/> szt</td>
                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${statistics.NO_THEME_INCOME}"/> zł</td>
            </tr>
        </table>

        <h4 class="mt-5 bg-darkblue p-3 text-light w-75 mx-auto">Biżuteria - statystyki szczegółowe</h4>
        <table class="table striped border-darkblue text-center w-75 mx-auto mb-4">
            <thead class="bg-midyellow">
            <tr>
                <th class="width-one-third">Rodzaj</th>
                <th class="width-one-third">Ilość</th>
                <th class="width-one-third">Przychód</th>
            </tr>
            </thead>
            <tr class="bg-white">
                <td>naszyjniki</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${statistics.NECKLACE_TYPE_SALES}"/> szt</td>
                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${statistics.NECKLACE_TYPE_INCOME}"/> zł</td>
            </tr>
            <tr>
                <td>kolczyki</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${statistics.EARINGS_TYPE_SALES}"/> szt</td>
                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${statistics.EARINGS_TYPE_INCOME}"/> zł</td>
            </tr>
            <tr class="bg-white">
                <td>bransoletki</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${statistics.BRACELET_TYPE_SALES}"/> szt</td>
                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${statistics.BRACELET_TYPE_INCOME}"/> zł</td>
            </tr>
            <tr>
                <td>inne</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${statistics.DIFFERENT_JEWELERY_TYPE_SALES}"/> szt</td>
                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${statistics.DIFFERENT_JEWELERY_TYPE_INCOME}"/> zł</td>
            </tr>
        </table>

        <h4 class="mt-5 bg-darkblue p-3 text-light w-75 mx-auto"> Ubrania - statystyki szczegółowe</h4>
        <table class="table striped border-darkblue text-center w-75 mx-auto mb-4">
            <thead class="bg-midyellow">
            <tr>
                <th class="width-one-third">Tworzywo</th>
                <th class="width-one-third">Ilość</th>
                <th class="width-one-third">Przychód</th>
            </tr>
            </thead>
            <tr>
                <td>metal</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${statistics.METAL_SUBSTANCE_SALES}"/> szt</td>
                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${statistics.METAL_SUBSTANCE_INCOME}"/> zł</td>
            </tr>
            <tr class="bg-white">
                <td>metal szlachetny</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${statistics.PRECIOUS_METAL_SUBSTANCE_SALES}"/> szt</td>
                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${statistics.PRECIOUS_METAL_SUBSTANCE_INCOME}"/> zł
                </td>
            </tr>
            <tr>
                <td>koraliki</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${statistics.BEADING_SUBSTANCE_SALES}"/> szt</td>
                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${statistics.BEADING_SUBSTANCE_INCOME}"/> zł
            </tr>
        </table>
    </div>
</body>
</html>
