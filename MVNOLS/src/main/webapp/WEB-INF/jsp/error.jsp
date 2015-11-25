<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>This is the error page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
   <h4> ${requestScope.errorMessage}</h4>
   <a href = "<c:url value = '/home.jsp'/>">Home</a>&nbsp;
<c:choose>
	<c:when test="${empty sessionScope.user}">
	   <a href = "<c:url value = '/login.jsp'/>">Login</a>&nbsp;
	   <a href = "<c:url value = '/register.jsp'/>">Register</a>&nbsp;
	</c:when>
	<c:otherwise>
	   <a href = "<c:url value = '/redirection/userAccount.action'/>">Account</a>&nbsp;
	</c:otherwise>
</c:choose>

  </body>
</html>
