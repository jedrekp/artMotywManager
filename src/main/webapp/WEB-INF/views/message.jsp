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
    <link href="<c:url value="/static/css/amm.css" />" rel="stylesheet"/>
</head>
<body>

<div class="wrapper">

    <jsp:include page="navbar.jsp"/>

    <div class="container">
        <div class="top-border"></div>
        <h2 id="user-message"><c:out value="${message}"/></h2>
    </div>
</body>
</html>