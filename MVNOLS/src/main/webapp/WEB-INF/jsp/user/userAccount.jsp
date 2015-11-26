<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctm" uri="/WEB-INF/tld/custom-tags.tld" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Welcome to the personal account</title>
    
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
<!-- JQuery EasyUI CSS -->
<link type="text/css" rel="stylesheet" href="<c:url value = '/css/easyui/themes/default/easyui.css'/>">
<link type="text/css" rel="stylesheet" href="<c:url value = '/css/easyui/themes/icon.css'/>">

<!-- Load the JS library -->
<script type="text/javascript" src="<c:url value = '/js/jquery/jquery-1.4.2.js'/>"></script>
<!-- JQuery Validation library -->
<script type="text/javascript" src="<c:url value = '/js/validation/jquery.validate.js'/>"></script>
<script type="text/javascript" src="<c:url value = '/js/validation/cmxforms.js'/>"></script>
<script type="text/javascript" src="<c:url value = '/js/validation/jquery.form.js'/>"></script>
<!-- JQuery EasyUI library -->
<script type="text/javascript" src="<c:url value = '/js/easyui/jquery.easyui.min.js'/>"></script>
<script type="text/javascript" src="<c:url value = '/js/easyui/locale/easyui-lang-en.js'/>"></script>

<script type="text/javascript">
$().ready(function() {
	
	/*	This is a AJAX request to query the nationality & generate elements dynamically	 */
	$.ajax ({
		url: "<c:url value = '/nationalityAjax/queryNationality.action'/>",
		type: "get",
		success: function (data) {
			for (var i = 0; i < data.length; i++) {
				var optionElement = $("<option/>");
				optionElement.attr("value",data[i]).text(data[i]);
				$("#nationality").append(optionElement);
			}
		}
	});
	
	/*	This is a AJAX request to submit the upload file & display back	 */
	$("#uploadFile").change(function () {
		var options = {
			url: "<c:url value = '/uploadAjax/uploadFile.action'/>",
			type: "post",
			dataType: "json",
			success: function (data) {
				$("#img").attr("src", data.requestURL);
				$("#uploadFilePath").val(data.relativePath);
			}
		};
		$("#userForm").ajaxSubmit(options);
	});
	
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
	$("#userForm").validate({
		/*	This part specifies the validation rules	 */
		rules: {
			firstname: "required",
			lastname: "required",
			username: {
				required: true,
				minlength: 2,
				remote: "<c:url value = '/userAjax/updateValidation.action'/>"
				/*	Below is the default complete form of "remote" for customization	*/
 /*				remote: {
					url: "<c:url value = '/userAjax/updateValidation.action'/>",
					type: "get",
					dataType: "json",
					data: {
						username: function () {
							return $("#username").val();
						}
					}
				}*/	
			},
			password: {
				required: true,
				minlength: 5
			},
			confirm_password: {
				required: true,
				minlength: 5,
				equalTo: "#password"
			},
			email: {
				required: true,
				email: true
			},
			cellphone: {
				digits: true
			},
			captcha: {
				required: true,
				remote: "<c:url value = '/captchaAjax/captchaValidation.action'/>"
			}
		},
		/*	This part specifies the error messages	*/
		messages: {
			firstname: "Please enter your first name",
			lastname: "Please enter your last name",
			username: {
				required: "Please enter a user name",
				minlength: $.validator.format("Your username must consist of at least {0} characters"),
				remote: "This user name is registered"
			},
			password: {
				required: "Please provide a password",
				minlength: $.validator.format("Your password must be at least {0} characters long")
			},
			confirm_password: {
				required: "Please confirm your password",
				minlength: $.validator.format("Your password must be at least {0} characters long"),
				equalTo: "Please enter the same password as above"
			},
			email: "Please enter a valid email address",
			cellphone: {
				digits: "Please enter a valid cellphone"
			},
			captcha: {
				required: "Please enter the captcha",
				remote: "The captcha is incorrect"
			}
		}
	});
	
	/*  Propose username by combining firstname and lastname  */
	$("#username").focus(function() {
		var firstname = $("#firstname").val();
		var lastname = $("#lastname").val();
		if(firstname && lastname && !this.value) {
			this.value = firstname + "." + lastname;
		}
	});
	
	/* 	Check if confirm password is still valid after password changed	 */
	$("#password").blur(function() {
		$("#confirm_password").valid();
	});
	
	/*	Customize the default messages for all fields or|and a specific field if necessary	*/
	$.extend($.validator.messages, {
		required: "You gotta do it bro",
		email: "This ain't right play boy"
	});
	
	/*	This part is for birthday UI 	*/
	$("#birthday").datebox({
		editable: true
	});
	
	/*	This part is to submit the user form when the "Update" button is clicked	*/
	$("#update").click(function () {
		$("#userForm").attr("action", "<c:url value = '/user/updateUser.action'/>").attr("method", "post").submit();
	});
});
</script>

<style type="text/css">
#userForm { width: 670px; }
#userForm label.error {
	margin-left: 10px;
	width: auto;
	display: inline;
}
</style>

</head>



  <body>
  
<h1 id="banner">Hello ${user.username}. Welcome to OLS project. Here is your account information.</h1>
&nbsp;&nbsp;&nbsp;&nbsp;<a href = "<c:url value = '/home.jsp'/>">Home.</a>
<div id="main">
<form class="cmxform" id="userForm" enctype = "multipart/form-data">
	<fieldset>
		${requestScope.successMessage};
		<legend>Please review or update your information</legend>
		<input type = "hidden" name = "id" value = "${user.id}"/>
		<input type = "hidden" name = "roleid" value = "${user.roleid }"/>
		<input type = "hidden" name = "activationuuid" value = "${user.activationuuid }"/>
		<ctm:token/>
		<p>
			<label for="firstname">Firstname</label>
			<input type = "text" id="firstname" name="firstname" value="${user.firstname}"/>
		</p>
		<p>
			<label for="lastname">Lastname</label>
			<input type = "text" id="lastname" name="lastname" value="${user.lastname}"/>
		</p>
		<p>
			<label for="username">Username</label>
			<input type = "text" id="username" name="username" value="${user.username}"/>
		</p>
		<p>
			<label for="password">Password</label>
			<input type="password" id="password" name="password" value="${user.password}"/>
		</p>
		<p>
			<label for="confirm_password">Confirm password</label>
			<input type="password" id="confirm_password" name="confirm_password" value="${user.password}"/>
		</p>
		<p>
			<label for="email">Email</label>
			<input type = "text" id="email" name="email" value="${user.email}"/>
		</p>
		<p>
			<label>Gender</label>
			<input type = "radio" name = "gender" value = "Male" <c:if test="${user.gender eq 'Male'}">checked="checked"</c:if>/>Male
			<input type = "radio" name = "gender" value = "Female" <c:if test="${user.gender eq 'Female'}">checked="checked"</c:if>/>Female
			<input type = "radio" name = "gender" value = "null" <c:if test="${user.gender eq 'null'}">checked="checked"</c:if>/>secret			
		</p>
		<p>
			<label>Birthday</label>
			<input type = "text" class="easyui-datebox" id = "birthday" name = "birthday" value = "<fmt:formatDate value="${user.birthday}" pattern = "MM/dd/yyyy"/>"/>
		</p>
		<p>
			<label>Nationality</label>
			<select id = "nationality" name = "nationality">
				<option value = "null">Secret</option>
			</select>
		</p>
		<p>
			<label>Cellphone</label>
			<input type = "text" id = "cellphone" name = "cellphone" value = "${user.cellphone}"/>
		</p>
		<p>
			<label>Detail</label>
			<input type="text" name = "detail" value = "${user.detail}" />
		</p>
		<p>
			<label>State</label>
			<input type="text" name = "state" value = "${user.state}" readonly="readonly"/>
		</p>
		<p>
			<label>Score</label>
			<input type="text" name = "score" value = "${user.score}" readonly="readonly"/>
		</p>
		<p>
			<label>Upload file</label>
			<img width = "100" height = "100" name = "img" id="img">
			<input type="hidden" name="uploadFilePath" id="uploadFilePath"/>
			<input type="file" name = "uploadFile" id = "uploadFile"/>
		</p>
		<p>
			<label for="captcha">Captcha</label>
			<input type = "text" id = "captcha" name = "captcha"/>
			<img src="<c:url value = '/captcha/CaptchaServlet.servlet'/>" id = "captcha_img">
		</p>	
		<p>
			<input class="submit" type="submit" id = "update" value="Update"/>
		</p>
	</fieldset>
</form>
</div>

  </body>
</html>
