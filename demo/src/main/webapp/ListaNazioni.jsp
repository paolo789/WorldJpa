<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<table border=1>
<th>Nazione</th><th>Popolazione</th>
<c:forEach items="${nazioni}" var="nazione">
    <tr><td><a href ="listacitta?nation=${nazione.code}">${nazione.name}</a></td><td>${nazione.population}</td></tr> <!--  mostrare anche popolazione e passare come valore il code (mostrando per� sempre a video il nome)-->
</c:forEach>
</table>
<br>
<a href="seconda">Back</a>
</body>
</html>