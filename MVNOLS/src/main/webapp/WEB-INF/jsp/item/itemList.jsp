<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Welcome to the item list</title>
    
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
  
<script type="text/javascript">
$().ready(function() {
	
	/*	This part is for redirection and request sending	*/
	$("#loginLink").click(function () {
		if (window.location.search == null || window.location.search == "") {
			window.location.href = "<c:url value = '/redirection/login.action'/>?host=" + window.location.host + "&pathname=" + window.location.pathname;
		} else {
			window.location.href = "<c:url value = '/redirection/login.action'/>" + window.location.search + "&host=" + window.location.host + "&pathname=" + window.location.pathname;
		}
	});
	$("#homeLink").click(function () {
		window.location.href = "<c:url value = '/home.jsp'/>";
	});
	$("#searchLink").click(function () {
		window.location.href = "<c:url value = '/redirection/itemCriteria.action'/>";
	});
	$("#logoutLink").click(function () {
		window.location.href = "<c:url value = '/user/userLogout.action'/>";
	});	
	
	/*  
	 *  Validation requirement:
	 *  1. Register a validation for a speicific form: $("#FormID").validate({...})
	 *  2. Key_in_rules_&_messages = <input>name
	 *     -- e.g. currentPageCode = <input>name
	 *  3. Specify submission handler: submitHandler:function(form){...} 
	 *  4. ONLY workable for statically-generated elements
	 */
	$("#pagingQueryForm").validate({
		/*	This part specifies the validation rules	 */
		rules: {
			currentPageCode: {
				required: true,
				digits: true,
				min: 1,
				max: "${pageItemBean.totalPages}"		// EL is appllicable in JS or JS framework (e.g. JQuery, etc.)
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
    <c:choose>
  	<c:when test="${empty sessionScope.user}">
  		<h4 align = "center">Welcome to mvnols! Here are the items. <a href = "javascript:void(0)" id = "loginLink">Login</a> <a href = "javascript:void(0)" id = "homeLink">Home</a> <a href = "javascript:void(0)" id = "searchLink">Set search conditions</a></h4>
  	</c:when>
  	<c:otherwise>
  		<h4 align = "center">Welcome ${sessionScope.user.username}! Here are the items. <a href = "javascript:void(0)" id = "homeLink">Home</a> <a href = "javascript:void(0)" id = "searchLink">Set search conditions</a> <a href = "javascript:void(0)" id = "logoutLink">Log-out</a></h4>
  	</c:otherwise>
  </c:choose> 
  
  <form id = "itemListForm" enctype="application/x-www-form-urlencoded">
  	<table border="1" cellspacing="0" bordercolor="pink" width = "50%" align="center">
    	<tr align = "center">
    		<td>Image</td>
    		<td>Item Name</td>
    		<td>Item Price</td>
    		<td>Operation</td>
    	</tr>
    <c:forEach items="${pageItemBean.beanListForCurrentPage}" var = "bean">
    	<tr align = "center">
    		<td><img width = "100" height = "100" src="${bean.fullUploadURL }"></td>
    		<td>${bean.itemName }</td>
    		<td>${bean.itemPrice }</td>
    		<td>
    			<a href = "<c:url value = '/html/item/${bean.id }.html'/>">[View Item Details]</a>
    		</td>
    	</tr>
    </c:forEach>
  	</table>
  </form>
  <br/>
  
<%-- This part serves for pagination in back-end --%>
<center id = "paging">
Page:${pageItemBean.currentPageCode}/Total:${pageItemBean.totalPages}
<a href = "<c:url value = '/itemPagination/queryItemListByCriteria.action?currentPageCode=1&itemName=${pageItemBean.itemName }&itemPriceInterval=${pageItemBean.itemPriceInterval }&itemPriceRanking=${pageItemBean.itemPriceRanking }'/>">First</a>
<c:if test="${pageItemBean.currentPageCode != 1}">
	<a href = "<c:url value = '/itemPagination/queryItemListByCriteria.action?currentPageCode=${pageItemBean.currentPageCode-1}&itemName=${pageItemBean.itemName }&itemPriceInterval=${pageItemBean.itemPriceInterval }&itemPriceRanking=${pageItemBean.itemPriceRanking }'/>">Previous</a>
</c:if>
<c:forEach items="${pageItemBean.displayPageCodes }" var="pageCode">
	<c:choose>
		<c:when test="${pageCode eq pageItemBean.currentPageCode}">
			[${pageCode}]
		</c:when>
		<c:otherwise>
		    <a href = "<c:url value = '/itemPagination/queryItemListByCriteria.action?currentPageCode=${pageCode}&itemName=${pageItemBean.itemName }&itemPriceInterval=${pageItemBean.itemPriceInterval }&itemPriceRanking=${pageItemBean.itemPriceRanking }'/>">${pageCode}</a>
		</c:otherwise>
	</c:choose>
</c:forEach>
<c:if test="${pageItemBean.currentPageCode != pageItemBean.totalPages }">
	<a href = "<c:url value = '/itemPagination/queryItemListByCriteria.action?currentPageCode=${pageItemBean.currentPageCode+1}&itemName=${pageItemBean.itemName }&itemPriceInterval=${pageItemBean.itemPriceInterval }&itemPriceRanking=${pageItemBean.itemPriceRanking }'/>">Next</a>
</c:if>
<a href = "<c:url value = '/itemPagination/queryItemListByCriteria.action?currentPageCode=${pageItemBean.totalPages}&itemName=${pageItemBean.itemName }&itemPriceInterval=${pageItemBean.itemPriceInterval }&itemPriceRanking=${pageItemBean.itemPriceRanking }'/>">Last</a>
</center><br/>

<center>
<form id="pagingQueryForm" method="get" action="<c:url value = '/itemPagination/queryItemListByCriteria.action'/>">
<input type = "hidden" name = "itemName" value = "${pageItemBean.itemName}"/>
<input type = "hidden" name = "itemPriceInterval" value = "${pageItemBean.itemPriceInterval}"/>
<input type = "hidden" name = "itemPriceRanking" value = "${pageItemBean.itemPriceRanking }"/>
Please enter the page that you want to go: <input type = "text" id="currentPageCode" name="currentPageCode" value = "${pageItemBean.currentPageCode}"/>
<input type="submit" value="go"/>
</form>
</center>

<center>
<form action = "<c:url value = '/itemPagination/queryItemListByCriteria.action'/>" method = "get">
<input type = "hidden" name = "itemName" value = "${pageItemBean.itemName}"/>
<input type = "hidden" name = "itemPriceInterval" value = "${pageItemBean.itemPriceInterval}"/>
<input type = "hidden" name = "itemPriceRanking" value = "${pageItemBean.itemPriceRanking }"/>
Please select the page you want to go:
<select name = "currentPageCode">
	<c:forEach var="pageCode" begin = "1" end = "${pageItemBean.totalPages}">
		<option value = "${pageCode}" <c:if test = "${pageCode eq pageItemBean.currentPageCode }">selected = "selected"</c:if>>${pageCode}</option>
	</c:forEach>
</select>
<input type = "submit" value = "go">
</form>
</center> 
  
  </body>
</html>
