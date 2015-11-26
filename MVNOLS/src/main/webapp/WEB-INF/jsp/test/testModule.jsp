<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="oscache" uri="http://www.opensymphony.com/oscache" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Welcome. This page is for testing the system</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<!-- JQuery EasyUI CSS -->
	<link type="text/css" rel="stylesheet" href="<c:url value = '/css/easyui/themes/default/easyui.css'/>">
	<link type="text/css" rel="stylesheet" href="<c:url value = '/css/easyui/themes/icon.css'/>">

  </head>
  
  <!-- Load the JS library -->
  <script type="text/javascript" src="<c:url value = '/js/jquery/jquery-1.4.2.js'/>"></script>
  <!-- JQuery EasyUI library -->
  <script type="text/javascript" src="<c:url value = '/js/easyui/jquery.easyui.min.js'/>"></script>
  <script type="text/javascript" src="<c:url value = '/js/easyui/locale/easyui-lang-en.js'/>"></script>


  
  <script type="text/javascript">
$().ready(function () {
	
	/*	This is a AJAX request to query the users & generate elements dynamically	 */
	$.ajax ({
		url: "<c:url value = '/testAjax/queryTotalUsers.action'/>",
		type: "get",
		success: function (data) {
			for (var i = 1; i <= data; i++) {
				var optionElement = $("<option/>");
				optionElement.attr("value",i).text(i);
				$("#choose_id").append(optionElement);
			}
		}
	});
	
	/*	This is the AJAX request to return the province list & generate option elements accordingly  */
	$.ajax ({
		url: "<c:url value = '/testAjax/queryCountries.action'/>",
		type: "get",
		success: function (data) {
			for (var i = 0; i < data.length; i++) {
				var optionElement = $("<option/>");
				optionElement.attr("value", data[i].id).text(data[i].countryName);
				$("#country").append(optionElement);
			}
		}
	});
	
	/*	
	 * 	This is the AJAX request to return the state list & generate option elements accordingly 
	 *  when <select id = "country" .../> is changed
     */
	$("#country").change(function () {
		
		/*	If select the <option>Please select a country</option>: reset & alert & return to the current page (*****)	*/
		if ($("#country").val() == "") {
			$("#state").empty().append($("<option/>").attr("value", "").text("Please select a state"));
			$("#city").empty().append($("<option/>").attr("value", "").text("Please select a city"));
			alert("Please select a country");
			return;
		} 
		
		/*	Else: send an AJAX request to query the result	*/
		$.ajax ({
			url: "<c:url value = '/testAjax/queryStates.action'/>?countryId=" + this.value,	// this.value (*****)
			type: "get",
			success: function (data) {
				/*  Remove all the child elements & append the initial <option/> (*****)  */
				$("#state").empty().append($("<option/>").attr("value","").text("Please select a state"));
				$("#city").empty().append($("<option/>").attr("value","").text("Please select a city"));
				/*	Append the new <option/> dynamically  */
				for (var i = 0; i < data.length; i++) {
					var optionElement = $("<option/>");
					optionElement.attr("value", data[i].id).text(data[i].stateName);
					$("#state").append(optionElement);
				}
			}
		});
	});
	
	/*	
	 *	This is the AJAX request to return the city list & generate the option elements accordingly
	 *	when the <select id = "state" ... /> is changed
	 */
	$("#state").change(function () {
		
		/*	If select the <option>Please select a state</option>: reset & alert & return to the current page (*****)	*/
		if ($("#state").val() == "") {
			$("#city").empty().append($("<option/>").attr("value","").text("Please select a city"));
			alert("Please select a state");
			return;
		} 
		
		/*	Else: send an AJAX request to query the result	*/
		$.ajax ({
			url: "<c:url value = '/testAjax/queryCities.action'/>?stateId=" + this.value,	// this.value (*****)
			type: "get",
			success: function (data) {
				/*	Remove all the child elements & append the inital <option/>	(*****) */ 
				$("#city").empty().append($("<option/>").attr("value","").text("Please select a city"));
				/*  Append the new <option/> dynamically  */
				for (var i = 0; i < data.length; i++) {
					var optionElement = $("<option/>");
					optionElement.attr("value", data[i].id).text(data[i].cityName);
					$("#city").append(optionElement);
				}
			}		
		});
	});
	
	/*	This part is for datebox & datetimebox UI 	*/
	$("#date").datebox({
		editable: true
	});
	$("#datetime").datetimebox({
		editable: true
	});
	 
	/*	This is checkbox all-select and all-unselect operation	*/
	$("#checkAll").click(function () {
		$(":checkbox[name='hobby']").attr("checked", $(this).attr("checked"));
	});
	
	/*	This is checkbox reverse-select operation	*/
	$("#checkReverse").click(function () {
		$(":checkbox[name='hobby']").each(function(){
			$(this).attr("checked", !$(this).attr("checked"));
		});
	});
	
	/*	This part is to submit the form when submission button is clicked   */ 
	$("#search").click(function () {
		$("#advancedSearchFormWithDateConersion").attr("action","<c:url value = '/test/dateConversion.action'/>").attr("method","get").submit();
	});
	$("#queryUser").click(function () {
		window.location.href = "<c:url value = '/test/queryUser.action'/>?id=1";
	});
	$("#query").click(function () {
		if ($("#choose_id").val() == 0) {
			alert("Please select a id");
			return;
		} else {
			$("#queryForm").attr("action", "<c:url value = '/test/queryUser.action'/>").attr("method","post").submit();		
		}
	});
	$("#getCurrentPageURL").click(function () {
		var queryParameters = "?parameterA=aaa&parameterB=bbb&parameterC=ccc";
		window.location.href = "<c:url value = '/test/getCurrentPageURL.action'/>?currentPageURL=" + window.location.href + "&currentPageURLWithParameters=" + window.location.href + queryParameters;
	});
	$("#submit").click(function () {
		$("#hobbyForm").attr("action", "<c:url value = '/test/processChecbox.action'/>").attr("method", "get").submit();
	});
	$("#generateStaticPage").click(function () {
		window.location.href = "<c:url value = '/test/generateStaticPage.action'/>";
	});
});
  </script>
  
  <body>
  	<h4>Welcome ${sessionScope.user.username}. Here is to test the mvnols, including regular action, AJAX action, caching, static page generation, etc.<br/>
	<a href="<c:url value = '/home.jsp'/>">Home</a> 
  	<a href="<c:url value = '/user/userLogout.action'/>">Log-out</a></h4>
	
  	This is a get request using &lt;a/&gt; element: <br/>
  	<a href="javascript:void(0)" id = "queryUser">Click here to query the user</a><br/><br/>
 
    This is a post request using &lt;form/&gt; element: <br/>
    Which user do you want to query? <br/>
    <form id = "queryForm" enctype="application/x-www-form-urlencoded">
    <select name = "id" id = "choose_id">
    	<option value="0">Please select a id</option>
    </select>
    <input type="submit" id = "query" value="query"/> ${requestScope.msg}
    </form> 
    
  	This is a a get request using &lt;form/&gt; element to test advanced search and date conversion:
	<form id = "advancedSearchFormWithDateConersion" enctype="application/x-www-form-urlencoded">
		Name: <input type = "text" name = "name"/><br/>
		Brand: <input type = "text" name = "brand"><br/> 
		Date: <input type = "text" class="easyui-datebox" id = "date" name = "date"/><br/>
		DateTime: <input type = "text" class="easyui-datetimebox" id = "datetime" name = "datetime"/><br/>
		<input type = "submit" id = "search" value = "search"/>
	</form>
    
    This is a test to get current page URL: 
    <input type = "button" id = "getCurrentPageURL" value = "get current page URL"/>  
	<br/><br/>
	
    This is an exception request: <br/>
    <a href="<c:url value = '/test/returnException.action'/>">Click here to query a calculation result</a>
	<br/><br/>
	
	This is a dropdown list interaction: <br/>
	<select id = "country" name = "country">
		<option value = "">Please select a country</option>
	</select>
	<select id = "state" name = "state">
		<option value = "">Please select a state</option>
	</select>
	<select id = "city" name = "city">
		<option value = "">Please select a city</option>
	</select>
	<br/><br/>
	
	This is a checkbox interaction: <br/>
	Please check your hobbies: <input type = "checkbox" id = "checkAll" name = "checkAll"> All|No Check <input type = "checkbox" id = "checkReverse" name = "checkReverse"/> Reverse Check<br/>
	<form id = "hobbyForm" enctype="application/x-www-form-urlencoded">
		<input type = "checkbox" id = "hobby" name = "hobby" value = "Movies"/>Movies
		<input type = "checkbox" id = "hobby" name = "hobby" value = "Musics"/>Musics
		<input type = "checkbox" id = "hobby" name = "hobby" value = "Shopping"/>Shopping
		<input type = "checkbox" id = "hobby" name = "hobby" value = "Learnning"/>Learnning
		<input type = "checkbox" id = "hobby" name = "hobby" value = "Travelling"/>Travelling
		<input type = "submit" id = "submit" value = "submit"/>
	</form>
	
	This is an OSCache (page caching) test: <br/>
	Current time: <%=new Date() %><br/> 
	<!-- 
		<oscache:cache/> for web-page local cache
		** scope: specify the cache scope, default is "application"
		** time: specify the web-page refresh interval (in unit of second)
		** key: specify what key is changed to refresh the web-page
		For web-page global cache, use "CacheFilter" configured in "web.xml"
		** The cache refresh time depends on the real application development
		** Do not open global cache during development period
	-->
	<oscache:cache scope="application" time="60">	
	Cached time: <%=new Date() %><br/>
	</oscache:cache>
	<br/>
	
	This is a test to generate static page: <br/>
	<a href = "javascript:void(0)" id = "generateStaticPage">Click here to generate static page</a> <br/>
	
  </body>
</html>