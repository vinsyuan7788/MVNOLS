<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
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
    URL for accessing this project: <br/>
    http://localhost:8080/mvnols/home.jsp <br/>
 	URL for login validation test: <br/>
    1. http://localhost:8080/mvnols/test/queryUser.action?id=1 <br/>
    2. http://localhost:8080/mvnols/test/returnException.action <br/>
  </body>
</html>
