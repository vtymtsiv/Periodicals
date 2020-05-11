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
<div class="d-flex flex-wrap">
    <c:forEach items="${periodicals}" var="periodical">
        <div class="card m-4 " style="width: 18rem; min-width:10rem;">

            <div id="periodical-photo-${periodical.id}" class="carousel slide" data-ride="carousel">
                <div class="carousel-inner">
                    <c:forEach items="${periodical.photos}" var="periodicalPhoto" varStatus="loop">
                        <div class="carousel-item${ loop.index == 0 ? " active" : "" }">
                            <img class="d-block w-100" src="data:image/png;base64, ${periodicalPhoto.photo}">
                        </div>
                    </c:forEach>
                </div>
                <a class="carousel-control-prev" href="#periodical-photo-${periodical.id}" role="button" data-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="carousel-control-next" href="#periodical-photo-${periodical.id}" role="button" data-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
            <div class="card-body">
                <p class="card-text">${periodical.name}</p>
                <p class="card-text">${periodical.description}</p>
                <c:if test="${!periodical.isUserSubscribed}">
                    <a href="/periodicals/subscribe/${periodical.id}" class="btn btn-primary">Subscribe</a>
                </c:if>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>
