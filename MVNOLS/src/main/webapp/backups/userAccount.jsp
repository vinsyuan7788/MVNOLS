<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>The is the user account page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>

<!-- Load the JS library -->
<script type="text/javascript" src = "<c:url value = '/js/jquery-1.4.2.js'/>"></script>

<script type="text/javascript">
/*	This is a function to change the captcha	*/
function changeCaptcha () {
	$("#captcha").attr("src", "<c:url value = '/captcha/CaptchaServlet.servlet'/>?xxx=" + new Date().getTime());
}

/*	This part is the information format check	*/
function checkUsername () {
	var username_td = $("#username_td");
	var username = $("input[name='username']").val();
	if ($.trim(username) == "") {
		username_td.text("Username cannot be null");
	} else {
		username_td.text("");
	}
}
function checkPassword () {
	var password_td = $("#password_td");
	var password = $("input[name='password']").val();
	if ($.trim(password) == "") {
		password_td.text("Password cannot be null");
	} else {
		password_td.text("");
	} 
}
function checkEmail () {
	var email_td = $("#email_td");
	var email = $("input[name='email']").val();
	if ($.trim(email) == "") {
		email_td.text("Email cannot be null");
	} else {
		email_td.text("");
	}
}
</script>
  
  <body>
  <h4>Hello ${user.username}. Here is your account information.
  <a href = "<c:url value = '/home.jsp'/>">Home</a> 
  <a href = "<c:url value = '/user/userLogout.action'/>">Log-out</a></h4>
  
  	<form action = "<c:url value = '/user/updateUser.action'/>" method = "post" enctype = "multipart/form-data">
	<input type = "hidden" name = "id" value = "${user.id}"/>
	<table>
		<caption>${requestScope.successMessage}</caption>
		<tr>
			<td>User Name*:</td>
			<td><input type = "text" name = "username" value = "${user.username}" onblur = "checkUsername()"/></td>
			<td id = "username_td">${requestScope.errorMessages.username}</td>
		</tr>
		<tr>
			<td>Password*:</td>
			<td><input type = "password" name = "password" value = "${user.password}" onblur = "checkPassword()"/></td>
			<td id = "password_td">${requestScope.errorMessages.password}</td>
		</tr>
		<tr>
			<td>email*:</td>
			<td><input type = "text" name = "email" value = "${user.email}" onblur = "checkEmail()"/></td>
			<td id = "email_td">${requestScope.errorMessages.email}</td>
		</tr>
		<tr>
			<td>Gender:</td>
			<td>
				<input type = "radio" name = "gender" value = "Male" <c:if test="${user.gender eq 'Male'}">checked="checked"</c:if>/>Male
				<input type = "radio" name = "gender" value = "Female" <c:if test="${user.gender eq 'Female'}">checked="checked"</c:if>/>Female			
			</td>
			<td></td>
		</tr>
		<tr>
			<td>Birthday:</td>
			<td><input type = "text" name = "birthday" value = "<fmt:formatDate value="${user.birthday}" pattern = "yyyy-MM-dd"/>"/></td>
			<td></td>
		</tr>
		<tr>
			<td>Address:</td>
			<td>
				<select name = "address">
					<option value = "null">Please select an address</option>
					<option value = "CHN" <c:if test = "${user.address eq 'CHN'}">selected="selected"</c:if>>CHN</option>
					<option value = "KOR" <c:if test = "${user.address eq 'KOR'}">selected="selected"</c:if>>KOR</option>
					<option value = "JPN" <c:if test = "${user.address eq 'JPN'}">selected="selected"</c:if>>JPN</option>
					<option value = "USA" <c:if test = "${user.address eq 'USA'}">selected="selected"</c:if>>USA</option>
				</select>
			</td>
			<td></td>
		</tr>
		<tr>
			<td>Detail:</td>
			<td><input type="text" name = "detail" value = "${user.detail}"/></td>
			<td></td>
		</tr>
		<tr>
			<td>Score:</td>
			<td><input type = "text" name = "score" value = "${user.score}"/></td>
			<td></td>
		</tr>
		<tr>
			<td>Upload file:</td>
			<td><input type = "file" name = "uploadFile"></td>
			<td>${requestScope.errorMessages.uploadFailure}</td>
		</tr>
		<tr>
			<td></td>
			<td><input type = "submit" value = "Modify"/></td>
			<td></td>
		</tr>		
	</table>
	</form> 
  </body>
</html>
