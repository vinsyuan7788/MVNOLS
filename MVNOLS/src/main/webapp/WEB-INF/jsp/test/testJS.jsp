<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>JS testing page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>

<script type = "text/javascript">
window.onload = function () {
	
	/*	This part converts JSON object to string	 */
	var person = {"name": "Vince", "age": 25, "gender": "male"};
	alert("The JSON object: " + person);													// Output JSON object
	alert("The JSON object: " + person.name + ", " + person.age + ", " +person.gender);	// Output JSON object
	var personString = JSON.stringify(person);						
	alert("The string converted from JSON object: " + personString);		// Output string converted from JSON object

	/*	This part converts string to JSON object	*/
	var string = "{\"name\": \"Vince\", \"age\": 25, \"gender\": \"male\"}";
	alert("The string: " + string);
	var personJson = eval("(" + string +  ")");						// If string is put into a parenthese in eval(), will be treated as JS codes
	alert("The JSON object converted from string: " + personJson.name + ", " + personJson.age + ", " +personJson.gender);	// Output the JSON object converted from string
	personJson = JSON.parse(string);
	alert("The JSON object converted from string: " + personJson.name + ", " + personJson.age + ", " +personJson.gender);	// Output the JSON object converted from string
};
</script>
  
  <body>
    <h4>Hello ${sessionScope.user.username}. Welcome to test MVNOLS</h4>
    <a href = "<c:url value = '/redirection/testModule.action'/>">Click here back to testing page</a>
  </body>
</html>
