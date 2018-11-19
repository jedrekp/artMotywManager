<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    response.setCharacterEncoding("UTF-8");
    request.setCharacterEncoding("UTF-8");
%>

<nav class="menu-bar">
    <a href="<c:url value="/statistics"/>">
        <div class="logo">
            <img src="${pageContext.request.contextPath}/static/images/artMotywBaner.jpg">
        </div>
    </a>
    <ol>
        <li><a href="<c:url value="/statistics"/>">Statystyki</a></li>
        <li>Dodaj produkt
            <ul>
                <li><a href="<c:url value="/jewelery/addNewJewelery" />">Biżuteria</a></li>
                <li><a href="<c:url value="/clothing/addNewClothing" />">Ubranie</a></li>
            </ul>
        </li>
        <li>Wyszukaj
            <ul>
                <li><a href="<c:url value="/jewelery/filterJewelery" />">Biżuteria</a></li>
                <li><a href="<c:url value="/clothing/filterClothing" />">Ubrania</a></li>
                <li><a href="<c:url value="/product/showAllProducts" />">Pokaż wszystkie</a></li>
            </ul>
        </li>
    </ol>
    <form class="search-box" id="stat-search-box" action="<c:url value="/statisticsForMonth"/>">
        <label>Pokaż statystyki dla miesiąca : </label><br/>
        <select title="miesiąc" name="month">
            <option value="01">Styczeń</option>
            <option value="02">Luty</option>
            <option value="03">Marzec</option>
            <option value="04">Kwiecień</option>
            <option value="05">Maj</option>
            <option value="06">Czerwiec</option>
            <option value="07">Lipiec</option>
            <option value="08">Sierpień</option>
            <option value="09">Wrzesień</option>
            <option value="10">Październik</option>
            <option value="11">Listopad</option>
            <option value="12">Grudzień</option>
        </select><input id="year" type="text" name="year" placeholder="rok" pattern="(19|20)\d\d" required
                        oninvalid="this.setCustomValidity('nieprawidłowy rok')"
                        oninput="this.setCustomValidity('')"
    ><button type="submit">&#8981;</button>
    </form>

    <form class="search-box" action="<c:url value="/product/find"/>">
        <label>Wyszukaj produkt po id : </label><br/>
        <input id="id" type="text" name="id" placeholder="identyfikator" required
               oninvalid="this.setCustomValidity('proszę wprowadzić identyfikator')"
               oninput="this.setCustomValidity('')"
        ><button type="submit">&#8981;</button>
    </form>
</nav>

