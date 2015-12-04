<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="customer.title"/></title>
<style>
.error {
	background: white;
	color : red;
	text-align: left;
}
</style>
</head>
<body>
<h3><spring:message code="customer.title"/></h3>
<fieldset>
<form:form method="post" modelAttribute="customer">
	<form:label path="name" cssErrorClass="error">
		<spring:message code="customer.name"/> :
	</form:label>
  	<form:input type="text" path="name" /> 
    <form:errors path="name" cssClass="error"/> <br> 
	<form:label path="address" cssErrorClass="error">
		<spring:message code="customer.address"/> : 
	</form:label>
    <form:input type="text" path="address" size="60" /> 
    <form:errors path="address" cssClass="error"/> <br>
	<form:label path="email" cssErrorClass="error">
		<spring:message code="customer.email"/> : 
	</form:label>
    <form:input type="text" path="email" /> 
    <form:errors path="email" cssClass="error"/> <br>
    <%
    /* <!-- 
    <form:label path="password" cssErrorClass="error">비밀번호 : </form:label>
    <form:password path="password" /> 
    <form:errors path="password" cssClass="error"/> <br>
     --> */
     %>
    <input type="submit" value=<spring:message code="save"/> />
</form:form>
</fieldset>
</body>
</html>