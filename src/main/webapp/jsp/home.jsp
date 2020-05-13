<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
    <link href="css/app.css" rel="stylesheet" type="text/css">
    <title>Spring Security Example - ProgrammingFree</title>
</head>
<body class="security-app">
<jsp:include page="navbar.jsp"/>
<div class="lc-block">
    <h1><spring:message code="welcome" /></h1>
</div>
</body>
</html>
