<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>


<body>
<%@include file="navBar.jsp" %>

<div class="conatiner">

    <div class="panel-heading">User Profile</div>

    <div class="panel-body">

        <dl class="dl-horizontal">
            <dt>First Name:</dt>
            <dd>${user.firstName}</dd>
            <dt>Last Name:</dt>
            <dd>${user.lastName}</dd>
            <dt>Email:</dt>
            <dd>${user.email}.</dd>
            <dt>SSO ID:</dt>
            <dd>${user.ssoId}</dd>
            <c:if test="${user.ssoId.equals(userProfileCurrent)}">
                <dt>Balance:</dt>
                <dd>${user.balance}</dd>
            </c:if>
        </dl>

        <div class="btn-toolbar text-center" role="toolbar">
            <sec:authorize access="hasRole('ADMIN')">
            <a href="<c:url value='/user/delete/${user.ssoId}' />" class="btn btn-danger">Delete</a></td>
        </div>
        </sec:authorize>
        <c:if test="${currentUserName.equals(user.ssoId)}">
            <a href="<c:url value='/user/${user.ssoId}/edit' />" class="btn btn-success">Edit</a></td>
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Overall Price</th>
                    <th>Quantity</th>
                    <th>Date</th>
                </tr>
                </thead>
                <tbody>
                <c:if test="${user.boughtItemsList!=null}">
                    <c:forEach items="${user.boughtItemsList}" var="itemRecord">
                        <tr>
                            <td>${itemRecord.itemName}</td>
                            <td>${itemRecord.price}</td>
                            <td>${itemRecord.quantity}</td>
                            <td>${itemRecord.transactionTime}</td>
                            <td><a href="<c:url value='/item/${itemRecord.originalItemId}' />"
                                   class="btn btn-success custom-width">View</a></td>
                        </tr>
                    </c:forEach>
                </c:if>
                </tbody>
            </table>
        </c:if>
        <a href="<c:url value="/" />">Back to the main page</a>
    </div>
</div>
</body>
</html>
