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
    <title>AMM - Nowy produkt</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<c:url value="/static/css/bootstrap.css" />" rel="stylesheet"/>
    <link href="<c:url value="/static/css/asdasd.css" />" rel="stylesheet"/>
</head>
<body>

<jsp:include page="navbar.jsp"/>

<div class="container container-border text-center">

    <div class="row bg-greylike py-2 mx-auto">

        <div class="col-md-9 col-lg-6 bg-midyellow border-darkblue mt-5 mx-auto">

            <h2 class="my-3">Nowe ubranie</h2>

            <form:form method="POST" modelAttribute="clothing" enctype="multipart/form-data">

                <form:hidden path="availability" value="${availability}"/>

                <div class="input-group col-sm-8 mx-auto">
                    <div class="input-group-prepend">
                        <div class="input-group-text bg-darkblue border-0 text-light">Identyfikator</div>
                    </div>
                    <form:input path="id" cssClass="form-control"/>
                </div>
                <form:errors path="id" cssClass="error text-danger font-weight-bold"/>


                <div class="input-group col-sm-8 mx-auto mt-3">
                    <div class="input-group-prepend">
                        <div class="input-group-text bg-darkblue border-0 text-light">Cena</div>
                    </div>
                    <form:input path="price" cssClass="form-control"/>
                </div>
                <form:errors path="price" cssClass="error text-danger font-weight-bold"/>

                <div class="input-group col-sm-8 mx-auto mt-3">
                    <div class="input-group-prepend">
                        <label class="input-group-text bg-darkblue border-0 text-light">Typ ubrania</label>
                    </div>
                    <form:select path="clothingType" cssClass="custom-select">
                        <c:forEach items="${clothingTypes}" var="type">
                            <form:option value="${type}">${type.typeName}</form:option>
                        </c:forEach>
                    </form:select>
                </div>

                <div class="input-group col-sm-8 mx-auto mt-3">
                    <div class="input-group-prepend">
                        <div class="input-group-text bg-darkblue border-0 text-light">Rozmiar</div>
                    </div>
                    <form:select path="size" cssClass="custom-select">
                        <c:forEach items="${clothingSizes}" var="size">
                            <form:option value="${size}">${size.sizeName}</form:option>
                        </c:forEach>
                    </form:select>
                </div>

                <div class="input-group col-sm-8 mx-auto mt-3">
                    <div class="input-group-prepend">
                        <div class="input-group-text bg-darkblue border-0 text-light">Motyw</div>
                    </div>
                    <form:select path="theme" cssClass="custom-select">
                        <c:forEach items="${clothingThemes}" var="theme">
                            <form:option value="${theme}">${theme.themeName}</form:option>
                        </c:forEach>
                    </form:select>
                </div>

                <div class="input-group col-sm-8 mx-auto mt-3">
                    <div class="input-group-prepend">
                        <div class="input-group-text bg-darkblue border-0 text-light">Model kroju</div>
                    </div>
                    <form:input path="cutType" cssClass="form-control"/>
                </div>
                <form:errors path="cutType" cssClass="error text-danger font-weight-bold"/>

                <div class="input-group col-sm-8 mx-auto mt-3">
                    <div class="input-group-prepend">
                        <div class="input-group-text bg-darkblue border-0 text-light">Opis produktu</div>
                    </div>
                    <form:textarea path="description" placeholder="opcjonalne" cssClass="form-control description-form-input"/>
                </div>
                <form:errors path="description" cssClass="error text-danger font-weight-bold"/><br/>

                <div class="input-group col-sm-8 mx-auto upload-input-group">
                    <form:input path="imageFile" type="file" id="file-upload-1" cssClass="invisible"/>
                    <button type="button" id="upload-button" class="btn btn-warning border-darkblue bg-lightyellow font-weight-bold">
                        Wybierz zdjęcie
                    </button>
                    <span id="upload-text" class="mx-1 my-auto">Nie wybrano zdjęcia</span>
                </div>
                <form:errors path="imageFile" cssClass="error"/><br/>

                <button class="btn-lg btn-warning border-darkblue bg-lightyellow font-weight-bold mb-2" type="submit">Dodaj produkt</button>
            </form:form>

        </div>
    </div>
</div>

<script>
    const realFileBtn = document.getElementById("file-upload-1");
    const customBtn = document.getElementById("upload-button");
    const customTxt = document.getElementById("upload-text");

    customBtn.addEventListener("click", function() {
        realFileBtn.click();
    });

    realFileBtn.addEventListener("change", function() {
        if (realFileBtn.value) {
            customTxt.innerHTML = realFileBtn.files[0].name;
        } else {
            customTxt.innerHTML = "Nie wybrano zdjęcia";
        }
    });
</script>

</body>
</html>
