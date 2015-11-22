<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Advanced search page for user</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
<script type="text/javascript" src="<c:url value = '/js/jquery-1.4.2.js'/>"></script>
<script type="text/javascript" src="<c:url value = '/js/jquery.validate.js'/>"></script>

<script type="text/javascript">
$().ready(function () {
	/*	This part is to submit the query criteria when the "Query" button is submitted	*/
	$("#query").click(function () {
		/*  Private advanced search uses "post" method	*/
		$("#criteriaForm").attr("action", "<c:url value = '/userPagination/queryUserListByAdminCriteria.action'/>").attr("method", "post").submit();
	});
});
</script>

  <body>
  <h4>Welcome ${sessionScope.user.username } administrator. Please set the criteria for comprehensive query of users. <a href = "<c:url value = '/home.jsp'/>">Home</a></h4>
  <form id = "criteriaForm" enctype="application/x-www-form-urlencoded">
  Username: <input type = "text" id = "username" name = "username"/>
  <br/>
  Gender: <select name = "gender">
  		      <option value = "all">All</option>
  		 <!-- <option value = "null">Unknown</option> -->  <!-- null value is unqueryable in database through "SELECT * FROM USER WHERE gender NOT IN ('Male', 'Female')" -->   
  		      <option value = "Male">Male</option>
  		      <option value = "Female">Female</option>
  		  </select>
  <br/>
  state: <input type = "radio" id = "state" name = "state" value = "all" checked="checked"/>All
  		 <input type = "radio" id = "state" name = "state" value = "active"/>active
  		 <input type = "radio" id = "state" name = "state" value = "unactive"/>unactive
         <input type = "radio" id = "state" name = "state" value = "disabled"/>disabled
  <br/>
  <input type = "submit" id = "query" value = "Query"/>
  <br/>
  </form> 
  
  </body>
</html>
