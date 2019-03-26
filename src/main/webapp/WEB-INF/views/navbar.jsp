<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    response.setCharacterEncoding("UTF-8");
    request.setCharacterEncoding("UTF-8");
%>

<div class="sticky-top">
    <nav class="navbar navbar-expand-lg navbar-dark bg-darkblue">

        <a class="navbar-brand ml-lg-3" href="<c:url value="/statistics"/>">
            <img src="${pageContext.request.contextPath}/static/images/artMotywBaner.jpg" width="200" height="47"/></a>

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">

                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/statistics"/>">Statystyki</a>
                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link " href="" role="button" data-toggle="dropdown">Dodaj</a>

                    <div class="dropdown-menu bg-lightyellow">
                        <a class="dropdown-item" href="<c:url value="/jewelery/addNewJewelery" />">Nowa biżuteria</a>
                        <a class="dropdown-item" href="<c:url value="/clothing/addNewClothing" />">Nowe ubranie</a>
                    </div>

                </li>

                <li class="nav-item dropdown">
                    <a class="nav-link " href="" id="navbarDropdown" role="button" data-toggle="dropdown">Wyszukaj</a>

                    <div class="dropdown-menu bg-lightyellow">
                        <a class="dropdown-item" href="<c:url value="/jewelery/filterJewelery" />">Biżuteria</a>
                        <a class="dropdown-item" href="<c:url value="/clothing/filterClothes" />">Ubrania</a>
                        <a class="dropdown-item" href="<c:url value="/product/showAllProducts" />">Pokaż wszystkie produkty</a>
                    </div>

                </li>

            </ul>

            <form class="form-inline mb-3 my-lg-2" action="<c:url value="/product/find"/>">

                <div class="form-group-row">

                    <input class="form-control col-lg-4 offset-lg-1 my-1" type="text" name="id" placeholder="ID" required
                           oninvalid="this.setCustomValidity('proszę wprowadzić identyfikator')"
                           oninput="this.setCustomValidity('')">

                    <button class="btn btn-sm btn-warning bg-midyellow font-weight-bold col-lg-6 p-2" type="submit">Szukaj po ID</button>
                </div>
            </form>

            <form class="form-inline my-lg-2" action="<c:url value="/statisticsForMonth"/>">

                <div class="form-group-row">

                    <select class="custom-select col-lg-3 mt-1 my-lg-1" title="miesiąc" name="month">
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
                    </select>

                    <input class="form-control mx-0 col-lg-2 my-1" type="text" name="year" placeholder="rok" pattern="(19|20)\d\d" required
                           oninvalid="this.setCustomValidity('nieprawidłowy rok')"
                           oninput="this.setCustomValidity('')">

                    <button class="btn btn-sm btn-warning bg-midyellow font-weight-bold col-lg-6 p-2" type="submit">Pokaż statystyki</button>
                </div>
            </form>

        </div>
    </nav>

    <div class="container under-navbar "></div>
</div>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
