<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Congratulations! Testing succeeds!</title>
    
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
  	<h4>Hello ${sessionScope.user.username}. Welcome to test MVNOLS</h4>
    <h4>Below is the user information</h4>
    Username: ${requestScope.user.username}<br/>
    Password: ${requestScope.user.password }<br/>
    email: ${requestScope.user.email }<br/>
    state: ${requestScope.user.state }<br/>
    Birthday: <fmt:formatDate value="${requestScope.user.birthday}" pattern="yyyy-MM-dd"/><br/>
    Detail: ${requestScope.user.detail}<br/>
    <a href = "<c:url value = '/redirection/testModule.action'/>">Click here back to testing page</a>
  </body>
</html>
