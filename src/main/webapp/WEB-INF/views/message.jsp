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
    <title>AMM - info</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<c:url value="/static/css/bootstrap.css" />" rel="stylesheet"/>
    <link href="<c:url value="/static/css/motyw.css" />" rel="stylesheet"/>
</head>
<body>

<div class="wrapper">

    <jsp:include page="navbar.jsp"/>

    <div class="container text-center py-5">

        <h2 class="font-weight-bold mb-5"><c:out value="${message}"/></h2>

    </div>

</body>
</html>