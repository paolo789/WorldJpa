<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modifica</title>
</head>
<body>
<br>

<form action="modifica">
<c:if test="${citta != null}">
Modifica Città<br>
<table>
<tr>
<th>Nome Citta</th><th>Nazione</th><th>District</th><th>Population</th><th></th>
</tr>
<tr>
<td>
<input type="text" name="nomecitta" value=${citta.name }>
</td>

<td>
  <select name="country">
    <c:forEach items="${countries}" var="country">
        <option value="${country.code}" ${country.code == citta.countryCode ? 'selected' : ''}>${country.name}</option>
    </c:forEach>
</select></td>

<td>
<input type="text" name="district" value=${citta.district }>
</td>

<td>
<input type="text" name="population" value=${citta.population }>
</td>
<td>
<input type="hidden" name="id" value=${citta.id }><input type="submit" name="modify">
</td>
</tr> 
</table>
</c:if>
<c:if test="${citta == null}">
Aggiungi Città<br>
<table>
<tr>
<th>Nome Citta</th><th>Nazione</th><th>District</th><th>Population</th><th></th>
</tr>
<tr>
<td>
<input type="text" name="nomecitta">
</td>
<td>
<select name="country">
    <c:forEach items="${countries}" var="country">
        <option value="${country.code}" >${country.name}</option>
    </c:forEach>
</select>
</td>
<td>
<input type="text" name="district">
</td>
<td>
<input type="text" name="population">
</td>
<td>
<input type="submit" name="modify">
</td>
</tr>
</table>
</c:if>
</form>
</body>
</html>