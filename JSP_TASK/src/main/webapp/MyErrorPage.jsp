<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Error Page</title>
</head>
<body>

<h3><font color="Red"><%= exception %></font></h3><br>

<h3>Please </h3>
<%@ include file="withdraw.html" %>
</body>
</html>