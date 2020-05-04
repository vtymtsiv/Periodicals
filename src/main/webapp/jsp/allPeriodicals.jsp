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

    // Todo Align a bit
    <div class="container">
        <div class="card" style="width: 18rem;">
            <div class="card-body">
                <h5 class="card-title">${periodical.name}</h5>
                <p class="card-text">${periodical.description}</p>
                <a href="/periodical/${periodical.id}" class="card-link">Card link</a>
            </div>
        </div>
    </div>
</c:forEach>
</body>
</html>
