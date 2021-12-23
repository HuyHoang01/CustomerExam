<%-- 
    Document   : customer_form_response
    Created on : Dec 23, 2021, 11:52:51 AM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table border="1">
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Age</th>
                <th>Address</th>
            </tr>
            <c:forEach var="tempCus" items="${Customers}">
                <tr>
                    <td>${tempCus.id}</td>
                    <td>${tempCus.name}</td>
                    <td>${tempCus.age}</td>
                    <td>${tempCus.address}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
