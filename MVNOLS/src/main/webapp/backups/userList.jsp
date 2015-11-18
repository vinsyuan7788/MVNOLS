<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>This is the user list page</title>
    
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
$().ready(function() {
	
	/*  
	 *  Validation requirement:
	 *  1. Register a validation for a speicific form: $("#FormID").validate({...})
	 *  2. Key_in_rules_&_messages = <input>id = <input>name
	 *     -- e.g. currentPageCode = <input>id = <input>name
	 *  3. Specify submission handler: submitHandler:function(){...} 
	 */
	$("#pagingQueryForm").validate({
		/*	This part specifies the validation rules	 */
		rules: {
			currentPageCode: {
				required: true,
				digits: true,
				min: 1,
				max: "${pageBean.totalPages}"		// EL is appllicable in JS or JS framework (e.g. JQuery, etc.)
			}
		},
		/*	This part specifies the error messages	*/
		messages: {
			currentPageCode: {
				required: "Please enter a page number",
				digits: "invalid input",
				min: "invalid value",
				max: $.validator.format("Maximum is {0}")
			}
		},
		/*	
		 *	This is the action that will be taken when submit button is clicked & detects no error messages
		 *	1. If validation fails (i.e. any error message is detected) when submit is clicked, submission will be stopped
		 */
		submitHandler:function(){ 
			form.submit();		// Submit the form to the specified action (i.e. <c:url value = '/user/userRegister.action'/>)
        }
	});
});
</script>

  <body>
    <h4 align = "center">Hello ${sessionScope.user.username} administrator. This is the user list. <a href = "<c:url value = '/home.jsp'/>">Home</a></h4>
    <table border="1" cellspacing="0" bordercolor="pink" width = "50%" align="center">
    	<tr align = "center">
    		<td>id</td>
    		<td>FirstName</td>
    		<td>LastName</td>
    		<td>UserName</td>
    		<td>Gender</td>
    		<td>Email</td>
    		<td>Cellphone</td>
    		<td>Nationality</td>
    		<td>State</td>
    		<td>Score</td>
    		<td colspan = "2">Operation</td>
    	</tr>
	<c:forEach items="${pageBean.beanListForCurrentPage}" var = "bean">
		<tr>
			<td>${bean.id }</td>
			<td>${bean.firstname }</td>
			<td>${bean.lastname }</td>
			<td>${bean.username }</td>
			<td>${bean.gender }</td>
			<td>${bean.email }</td>
			<td>${bean.cellphone }</td>
			<td>${bean.nationality }</td>
			<td>${bean.state }</td>
			<td>${bean.score }</td>
			<td>
				<a href="<c:url value=''/>">Edit</a>
			</td>
			<td>
				<a href="<c:url value=''/>">Delete</a>
			</td>
		</tr>
</c:forEach>
    </table>
    <br/>
    
<%-- This part serves for pagination in back-end --%>
<center id = "paging">
Page:${pageBean.currentPageCode}/Total:${pageBean.totalPages}
<a href = "<c:url value = '/user/queryUserList.action?currentPageCode=1'/>">First</a>
<c:if test="${pageBean.currentPageCode != 1}">
	<a href = "<c:url value = '/user/queryUserList.action?currentPageCode=${pageBean.currentPageCode-1}'/>">Previous</a>
</c:if>
<c:if test="${pageBean.currentPageCode != pageBean.totalPages }">
	<a href = "<c:url value = '/user/queryUserList.action?currentPageCode=${pageBean.currentPageCode+1}'/>">Next</a>
</c:if>
<a href = "<c:url value = '/user/queryUserList.action?currentPageCode=${pageBean.totalPages}'/>">Last</a>
</center><br/>

<center>
<form id="pagingQueryForm" method="post" action="<c:url value = '/user/queryUserList.action'/>">
Please enter the page that you want to go: <input type = "text" id="currentPageCode" name="currentPageCode" value = "${pageBean.currentPageCode}"/>
<input type="submit" value="go"/>
</form>
</center>

<center>
<form action = "<c:url value = '/user/queryUserList.action'/>" method = "post">
Please select the page you want to go:
<select name = "currentPageCode">
	<c:forEach var="pageCode" begin = "1" end = "${pageBean.totalPages}">
		<option value = "${pageCode}" <c:if test = "${pageCode eq pageBean.currentPageCode }">selected = "selected"</c:if>>${pageCode}</option>
	</c:forEach>
</select>
<input type = "submit" value = "go">
</form>
</center>
    
  </body>
</html>
