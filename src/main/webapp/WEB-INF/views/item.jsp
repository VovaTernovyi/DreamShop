<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <!-- Theme Made By www.w3schools.com - No Copyright -->
    <title>${item.itemName}</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link href="http://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>



<body>
<%@include file="navBar.jsp" %>


<div class="row">
    <div class="col-sm-4">
        <div class="container-fluid bg-1 text-center">
            <h3 class="margin"><c:out value="${item.itemName}"/></h3>
            <img src="/static/images/null.png">
        </div>
    </div>

    <div class="col-sm-4">
        <div class="container-fluid bg-2 text-center">
            <h3 class="margin">Description</h3>
            <p><c:out value="${item.description}"/></p>
        </div>
    </div>

    <div class="col-sm-4">
        <div class="container-fluid bg-3 text-center">
            <h3 class="margin">Add to cart:</h3><br>
            <c:choose>
                <c:when test="${item.stock>0}">
                    <a href="<c:url value="/item/${item.id}/addtocart"/>" class="btn btn-default btn-lg">
                        <span class="glyphicon glyphicon-shopping-cart"></span> ${item.price}
                    </a>
                </c:when>
                <c:otherwise>
                    <a href="<c:url value="#"/>" class="btn btn-default disabled">
                        <span class="glyphicon glyphicon-shopping-cart"></span> ${item.price}
                    </a>
                </c:otherwise>
            </c:choose>
        </div>
        <div class="container-fluid bg-2 text-center">
            <h5 class="margin">In stock:
            <p><c:out value="${item.stock}"/></p></h5>
        </div>
</div>
    <!-- Footer -->
</div>
<footer class="container-fluid bg-4">
    <p>Back to top</p>
</footer>
<a class="back-to-top glyphicon glyphicon-arrow-up" href="/item/${item.id}" title="Top"></a>
</body>
</html>
