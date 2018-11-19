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
    <link href="<c:url value="/static/css/amm.css" />" rel="stylesheet"/>
</head>
<body>


<div class="wrapper">

    <jsp:include page="navbar.jsp"/>

    <div class="container statistics-container">
        <div class="top-border"></div>

        <h2 id="statistics-page-header"><c:out value="${title}"/></h2>

        <table>
            <caption>Statystyki ogólne</caption>
            <tr>
                <th>Kategoria produktu</th>
                <th>Ilość sprzedanych</th>
                <th>Wartość sprzedaży</th>
            </tr>
            <tr>
                <td>ubrania</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${statistics.CLOTHING_SALES}"/> szt</td>
                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${statistics.CLOTHING_INCOME}"/> zł
                </td>
            </tr>
            <tr>
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

        <table>
            <caption>Ubrania - statystyki szczegółowe </caption>
            <tr>
                <th>Rodzaj</th>
                <th>Ilość sprzedanych</th>
                <th>Wartość sprzedaży</th>
            </tr>
            <tr>
                <td>sukienki</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${statistics.DRESS_TYPE_SALES}"/> szt</td>
                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${statistics.DRESS_TYPE_INCOME}"/> zł</td>
            </tr>
            <tr>
                <td>spódnice</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${statistics.SKIRT_TYPE_SALES}"/> szt</td>
                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${statistics.SKIRT_TYPE_INCOME}"/> zł</td>
            </tr>
            <tr>
                <td>spodnie</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${statistics.PANTS_TYPE_SALES}"/> szt</td>
                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${statistics.PANTS_TYPE_INCOME}"/> zł</td>
            </tr>
            <tr>
                <td>bluzki</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${statistics.SHIRT_TYPE_SALES}"/> szt</td>
                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${statistics.SHIRT_TYPE_INCOME}"/> zł</td>
            </tr>
            <tr>
                <td>bluzy</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${statistics.SWEATSHIRT_TYPE_SALES}"/> szt</td>
                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${statistics.SWEATSHIRT_TYPE_INCOME}"/> zł</td>
            </tr>
            <tr>
                <td>czapki</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${statistics.HAT_TYPE_SALES}"/> szt</td>
                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${statistics.HAT_TYPE_INCOME}"/> zł</td>
            </tr>
            <tr>
                <td>kurtki</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${statistics.JACKET_TYPE_SALES}"/> szt</td>
                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${statistics.JACKET_TYPE_INCOME}"/> zł</td>
            </tr>
            <tr>
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

        <table>
            <caption>Ubrania - statystyki wg rozmiarów</caption>
            <tr>
                <th>Rozmiar</th>
                <th>Ilość sprzedanych</th>
                <th>Wartość sprzedaży</th>
            </tr>
            <tr>
                <td>XS</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${statistics.XS_SIZE_SALES}"/> szt</td>
                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${statistics.XS_SIZE_INCOME}"/> zł</td>
            </tr>
            <tr>
                <td>S</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${statistics.S_SIZE_SALES}"/> szt</td>
                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${statistics.S_SIZE_INCOME}"/>zł</td>
            </tr>
            <tr>
                <td>M</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${statistics.M_SIZE_SALES}"/> szt</td>
                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="  ${statistics.M_SIZE_INCOME}"/> zł</td>
            </tr>
            <tr>
                <td>L</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${statistics.L_SIZE_SALES}"/> szt</td>
                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${statistics.L_SIZE_INCOME}"/>zł</td>
            </tr>
            <tr>
                <td>XL</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${statistics.XL_SIZE_SALES}"/> szt</td>
                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${statistics.XL_SIZE_INCOME}"/> zł</td>
            </tr>
            <tr>
                <td>XXL</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${statistics.XXL_SIZE_SALES}"/> szt</td>
                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${statistics.XXL_SIZE_INCOME}"/> zł</td>
            </tr>
            <tr>
                <td>uniwersalny</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${statistics.UNIVERSAL_SIZE_SALES}"/> szt</td>
                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${statistics.UNIVERSAL_SIZE_INCOME}"/> zł</td>
            </tr>
        </table>

        <table>
            <caption>Ubrania - statystyki wg motywów</caption>
            <tr>
                <th>Motyw</th>
                <th>Ilość sprzedanych</th>
                <th>Wartość sprzedaży</th>
            </tr>
            <tr>
                <td>zwierzęcy</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${statistics.ANIMAL_THEME_SALES}"/> szt</td>
                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${statistics.ANIMAL_THEME_INCOME}"/> zł</td>
            </tr>
            <tr>
                <td>roślinny</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${statistics.FLORAL_THEME_SALES}"/> szt</td>
                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${statistics.FLORAL_THEME_INCOME}"/> zł</td>
            </tr>
            <tr>
                <td>abstrakcja</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${statistics.ABSTRACT_THEME_SALES}"/> szt</td>
                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${statistics.ABSTRACT_THEME_INCOME}"/> zł</td>
            </tr>
            <tr>
                <td>inny</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${statistics.OTHER_THEME_SALES}"/> szt</td>
                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${statistics.OTHER_THEME_INCOME}"/> zł</td>
            </tr>
            <tr>
                <td>brak</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${statistics.NO_THEME_SALES}"/> szt</td>
                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${statistics.NO_THEME_INCOME}"/> zł</td>
            </tr>
        </table>

        <table>
            <caption>Biżuteria - statystyki szczegółowe</caption>
            <tr>
                <th>Rodzaj</th>
                <th>Ilość sprzedanych</th>
                <th>Wartość sprzedaży</th>
            </tr>
            <tr>
                <td>naszyjniki</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${statistics.NECKLACE_TYPE_SALES}"/> szt</td>
                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${statistics.NECKLACE_TYPE_INCOME}"/> zł</td>
            </tr>
            <tr>
                <td>kolczyki</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${statistics.EARINGS_TYPE_SALES}"/> szt</td>
                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${statistics.EARINGS_TYPE_INCOME}"/> zł</td>
            </tr>
            <tr>
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

        <table>
            <caption>Biżuteria - statystyki wg tworzywa</caption>
            <tr>
                <th>Tworzywo</th>
                <th>Ilość sprzedanych</th>
                <th>Wartość sprzedaży</th>
            </tr>
            <tr>
                <td>metal</td>
                <td><fmt:formatNumber type="number" maxFractionDigits="0" value="${statistics.METAL_SUBSTANCE_SALES}"/> szt</td>
                <td><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${statistics.METAL_SUBSTANCE_INCOME}"/> zł</td>
            </tr>
            <tr>
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
</div>
</body>
</html>
