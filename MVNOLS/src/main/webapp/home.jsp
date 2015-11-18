<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>http://localhost:8080/mvnols/home.jsp</title>
    
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
  <c:choose>
  	<c:when test="${empty sessionScope.user}">
  		<h4>Hello visitor. Welcome to mvnols. <a href="<c:url value = '/login.jsp'/>">Login</a> <a href="<c:url value = '/register.jsp'/>">Register</a></h4>
  	</c:when>
  	<c:otherwise>
  		<h4>Hello ${sessionScope.user.username}. Welcome to mvnols. <a href = "<c:url value = '/user/userLogout.action'/>">Log-out</a></h4>
  	</c:otherwise>
  </c:choose> 
  
  <a href = "<c:url value = '/itemPagination/queryItemListByCriteria.action'/>">Click here to view all items</a><br/>
  <a href = "<c:url value = '/redirection/itemCriteria.action'/>">Click here for advanced search on items</a><br/>
  
  <c:if test="${not empty sessionScope.user}">
  <br/>Here is the member module:<br/>
  <a href = "<c:url value = '/redirection/testModule.action'/>">Click here to the mvnols testing module</a><br/>
  <a href = "<c:url value = '/redirection/userAccount.action'/>">Click here to the account management</a><br/>
  
  <c:if test="${sessionScope.user.roleid eq 2 or sessionScope.user.roleid eq 3 }">  <%-- <c:if test="${sessionScope.user.role eq 'admin'}"> --%>
  <br/>Here is the administrator module:<br/>
  <a href = "<c:url value = '/userPagination/queryUserListByAdminCriteria.action'/>">Click here to manage all users</a><br/>
  <a href = "<c:url value = '/redirection/userAdminCriteria.action'/>">Click here to set the conditions for user query</a><br/>
  <a href = "<c:url value = '/redirection/itemAdminAdd.action'/>">Click here to add a new item</a><br/>
  <a href = "<c:url value = '/itemPagination/queryItemListByAdminCriteria.action'/>">Click here to manage all items</a><br/>
  <a href = "<c:url value = '/redirection/itemAdminCriteria.action'/>">Click here to set the conditions for item query</a><br/>
  </c:if>
  
  <c:if test="${sessionScope.user.roleid eq 3 }">
  <br/>Here is the super administrator module:<br/>
  <a href = "">Click here to role & privilege management</a><br/>
  </c:if>
  
  </c:if>
  </body>
</html>
