<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Admin</title>
        <style>
            <%@include file="/bootstrap/bootstrap.min.css"%>
            <%@include file="/css/admin.css"%>
            <%@include file="/css/adminMenu.css"%>
            <%@include file="/css/editForm.css"%>
            <%@include file="/css/modalWindow.css"%>
        </style>
        <script src="<c:url value="/js/jquery-3.2.0.min.js"/>"></script>
        <script src="<c:url value="/js/adminJS.js"/>"></script>
        <script src="<c:url value="/js/editForm.js"/>"></script>
        <script src="<c:url value="/js/buildTable.js"/>"></script>
        <script src="<c:url value="/js/jquery.autocomplete.js"/>"></script>
        <link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
    </head>
    <body>
        <div id="main">
            <nav class='animated flipInX'>
                <ul id="tableList">
                    <c:forEach var="item" items="${tableNames}">
                        <li id=${item}><a href="#">${item}</a></li>
                    </c:forEach>
                </ul>
            </nav>

            <table class="flatTable"></table>
        </div>
    </body>
</html>

