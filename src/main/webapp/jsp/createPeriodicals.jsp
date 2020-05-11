<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
    <title>Create periodical</title>
</head>
<body>

<div>
    <jsp:include page="navbar.jsp"/>
    <div class="container">
        <div class="row">
            <div class="col-sm">
            </div>
            <div class="col-sm">
                <h3>New periodical</h3>
                <form action="/periodicals/save" method="POST" enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="formGroupExampleInput">Name</label>
                        <input type="text" class="form-control" id="formGroupExampleInput" placeholder="Name" name="name">
                    </div>
                    <div class="form-group">
                        <label for="formGroupExampleInput2">Description</label>
                        <input type="text" class="form-control" id="formGroupExampleInput2" placeholder="Description" name="description">
                    </div>
                    <div class="form-group">
                        <label for="formGroupExampleInput3">Photos</label>
                        <input type="file" multiple class="form-control" id="formGroupExampleInput3" name="photos">
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="checkbox" id="gridCheck1" name="active">
                        <label class="form-check-label" for="gridCheck1">
                            Active
                        </label>
                    </div>
                    <br>
                    <input type="hidden" name="${_csrf.parameterName}"
                           value="${_csrf.token}"/>
                    <button type="submit" class="btn btn-primary">Save</button>
                </form>
            </div>
            <div class="col-sm">
            </div>
        </div>
    </div>


</div>
</body>
</html>
