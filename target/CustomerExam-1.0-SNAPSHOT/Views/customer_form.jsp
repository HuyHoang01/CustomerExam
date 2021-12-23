<%-- 
    Document   : customer_form
    Created on : Dec 23, 2021, 11:52:36 AM
    Author     : PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="../CustomerControllerServlet" method="get">
            <input type="hidden" name="command" value="SHOW"/>
            Name:<input type="text" name="name"/>
            <button type="submit">Search</button>
        </form>
    </body>
</html>
