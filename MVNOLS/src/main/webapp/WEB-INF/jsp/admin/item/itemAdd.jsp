<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Welcome to item adding page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
<script type="text/javascript" src="<c:url value = '/js/jquery/jquery-1.4.2.js'/>"></script>
<script type="text/javascript" src="<c:url value = '/js/validation/jquery.validate.js'/>"></script>
<script type="text/javascript" src="<c:url value = '/js/validation/jquery.form.js'/>"></script>

<script type="text/javascript">
$().ready(function() {

	/*	This part is for redirection and request sending	*/
	$("#homeLink").click(function () {
		window.location.href = "<c:url value = '/home.jsp'/>";
	});
	$("#logoutLink").click(function () {
		window.location.href = "<c:url value = '/user/userLogout.action'/>";
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
	
	/*	This is a AJAX request to submit the upload file & display back	 */
	$("#uploadImage").change(function () {
		var options = {
			url: "<c:url value = '/uploadAjax/uploadImage.action'/>",
			type: "post",
			dataType: "json",
			success: function (data) {
				$("#img").attr("src", data.requestURL);
				$("#itemImage").val(data.relativePath);
			}
		};
		$("#itemForm").ajaxSubmit(options);
	});
	
	/*  
	 *  Validation requirement:
	 *  1. Register a validation for a speicific form: $("#FormID").validate({...})
	 *  2. Key_in_rules_&_messages = <input>name
	 *     -- e.g. username = <input>name
	 *  3*. Specify submission handler: submitHandler:function(form){...}: NOT necessary
	 *  4. ONLY workable for statically-generated elements
	 */
	$("#itemForm").validate({
		/*	This part specifies the validation rules	 */
		rules: {
			itemName: "required",
			itemPrice: {
				required: true,
				number: true,
			},
			itemPlaceOfManufacture: {
				required: true,
				maxlength: 50
			},
			captcha: {
				required: true,
				remote: "<c:url value = '/captchaAjax/captchaValidation.action'/>"
			}
		},
		/*	This part specifies the error messages	*/
		messages: {
			itemName: "Please enter the item name",
			itemPrice: {
				required: "Please enter a item price",
				number: "Please enter the number correctly"
			},
			itemPlaceOfManufacture: {
				required: "Please provide a manufacture place",
				maxlength: $.validator.format("Your manufacture place cannot exceed {0} characters long")
			},
			captcha: {
				required: "Please enter the captcha",
				remote: "The captcha is incorrect"
			}
		}
	});
	
	/*	This is the operation when "Add Item" is clicked	*/
	$("#addItem").click(function () {
		$("#itemForm").attr("action", "<c:url value = '/item/addItem.action'/>").attr("method", "post").submit();
	});
});
</script>
  
  <body>
   <h4 align = "center">Welcome ${sessionScope.user.username}! You can add your new item here. <a href = "javascript:void(0)" id = "homeLink">Home</a> <a href = "javascript:void(0)" id = "logoutLink">Log-out</a></h4>
   
   <form id = "itemForm" enctype="multipart/form-data">
   		<table border="1" cellspacing="0" bordercolor="pink" width = "50%" align="center">
   			<tr>
   				<td>Item Name:</td>
   				<td><input type = "text" id = "itemName" name = "itemName"></td>
   			</tr>
   		   	<tr>
   				<td>Item Price:</td>
   				<td><input type = "text" id = "itemPrice" name = "itemPrice"></td>
   			</tr>
   			<tr>
   				<td>Manufacture place:</td>
   				<td><input type = "text" id = "itemPlaceOfManufacture" name = "itemPlaceOfManufacture"></td>
   			</tr>
   			<tr>
   				<td>Item Detail:</td>
   				<td><input type = "text" id = "itemDetail" name = "itemDetail"></td>
   			</tr>
   			<tr>
   				<td>Item Image:</td>
   				<td>
					<img width = "100" height = "100" name = "img" id="img">
					<input type="hidden" name="itemImage" id="itemImage"/>
					<input type="file" name = "uploadImage" id = "uploadImage"/>
   				</td>
   			</tr>
   		    <tr>
   				<td>Captcha</td>
   				<td>
   					<input type = "text" id = "captcha" name = "captcha"/>
					<img src="<c:url value = '/captcha/CaptchaServlet.servlet'/>" id = "captcha_img">
   				</td>
   			</tr>
   		   	<tr>
   				<td></td>
   				<td><input type = "submit" id = "addItem" value = "Add Item"></td>
   			</tr>
   		</table>
   </form>
   
  </body>
</html>
