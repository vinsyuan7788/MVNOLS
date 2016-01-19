<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Sign-in Page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
<!-- JQuery Validation CSS -->	
<link type="text/css" rel="stylesheet" href="<c:url value = '/css/validation/css/screen.css'/>" media="screen"/>

<!-- JQuery library -->
<script type="text/javascript" src="<c:url value = '/js/jquery/jquery-1.4.2.js'/>"></script>
<!-- JQuery Validation library -->
<script type="text/javascript" src="<c:url value = '/js/validation/jquery.validate.js'/>"></script>
<script type="text/javascript" src="<c:url value = '/js/validation/cmxforms.js'/>"></script>

<script type="text/javascript">
$().ready(function() {
	
	/*	
	 * 	This is a non-stackable event function to change the captcha
	 *  1. If the src request still remains the same, the browser may directly return the same captcha from the browser cache
	 *     -- It depends on the internal mechanism & settting of different browsers
	 *  2. To make sure to avoid this situation: add a new timestamp such that each time src will send a new request, then 
	 *     browser will return a new captcha from the server (servlet or action)
	 */	
	$("#captcha_img").unbind("click");
	$("#captcha_img").bind("click", function () {
		$(this).attr("src", "<c:url value = '/captcha/CaptchaServlet.servlet'/>?xxx=" + new Date().getTime());
	});
	
	/*  
	 *  Validation requirement:
	 *  1. Register a validation for a speicific form: $("#FormID").validate({...})
	 *  2. Key_in_rules_&_messages = <input>name
	 *     -- e.g. username = <input>name
	 *  3*. Specify submission handler: submitHandler:function(form){...}: NOT necessary
	 *  4. ONLY workable for statically-generated elements 
	 */
	$("#loginForm").validate({
		/*	This part specifies the validation rules	 */
		rules: {
			username: {
				required: true,
				minlength: 2
			},
			password: {
				required: true,
				minlength: 5,
//				remote: "<c:url value = '/userAjax/signInValidation.action'/>"
				/*	Below is the default complete form of "remote" for customization	*/
 				remote: {
					url: "<c:url value = '/userAjax/signInValidation.action'/>",
					type: "get",
					dataType: "json",
					data: {
						username: function () {
							return $("#username").val();
						},
						password: function () {
							return $("#password").val();
						}
					}
				}				
			},
			captcha: {
				required: true,
				remote: "<c:url value = '/captchaAjax/captchaValidation.action'/>"
			}
		},
		/*	This part specifies the error messages	*/
		messages: {
			username: {
				required: "Please enter a user name",
				minlength: $.validator.format("Your username must consist of at least {0} characters"),
			},
			password: {
				required: "Please provide a password",
				minlength: $.validator.format("Your password must be at least {0} characters long"),
				remote: "Your infomraiton is incorrect"
			},
			captcha: {
				required: "Please enter the captcha",
				remote: "The captcha is incorrect"
			}
		}
	});
	
	/*	Customize the default messages for all fields or|and a specific field if necessary	*/
	$.extend($.validator.messages, {
		required: "You gotta do it bro",
		email: "This ain't right play boy"
	});
	
	/*	This part is to submit the login form when the "Login" button is clicked	*/
	$("#login").click(function () {
		$("#loginForm").attr("action", "<c:url value = '/user/userLogin.action'/>").attr("method", "post").submit();
	});
});
</script>

<style type="text/css">
#loginForm { width: 670px; }
#loginForm label.error {
	margin-left: 10px;
	width: auto;
	display: inline;
}
</style>

</head>



<body>

<h1 id="banner">Welcome to log in to OLS project</h1>
&nbsp;&nbsp;&nbsp;&nbsp;<a href = "<c:url value = '/home.jsp'/>">Home.</a>&nbsp;&nbsp;No account? <a href = "<c:url value = '/register.jsp'/>">Click here for registration</a>&nbsp;
<div id="main">
<form class="cmxform" id="loginForm" enctype="application/x-www-form-urlencoded">
	<fieldset>
		<legend>Please fill up the following information for login</legend>
		<input type = "hidden" name = "returnURL" value = "${sessionScope.returnURL}"/>
		<p>
			<label for="username">Username</label>
			<input type = "text" id="username" name="username" />
		</p>
		<p>
			<label for="password">Password</label>
			<input type="password" id="password" name="password" />
		</p>
		<p>
			<label for="captcha">Captcha:</label>
			<input type = "text" id = "captcha" name = "captcha"/>
			<img src="<c:url value = '/captcha/CaptchaServlet.servlet'/>" id = "captcha_img">
		</p>
		<p>
			<input class="submit" type="submit" id = "login" value="Login"/>
		</p>
	</fieldset>
</form>
</div>

</body>
</html>