<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>All periodicals</title>
</head>
<body>
<jsp:include page="navbar.jsp"/>

<h2>All periodicals</h2>

<c:forEach items="${periodicals}" var="periodical">
    <p>${periodical.name}</p>
    <p>${periodical.description}</p>
    <br>
</c:forEach>
</body>
</html>
