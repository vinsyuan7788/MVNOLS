<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'itemCriteria.jsp' starting page</title>
    
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
		/*	Public advanced search uses "get" method, so that in this case the search conditions can be obtained to allow users to return to most recently visited page after login  */
		$("#criteriaForm").attr("action", "<c:url value = '/itemPagination/queryItemListByCriteria.action'/>").attr("method", "get").submit();
	});
});
</script>
  <body>
  <h4>Welcome to mvnols! Please set the criteria for comprehensive query of items. <a href = "<c:url value = '/home.jsp'/>">Home</a></h4>
  <form id = "criteriaForm" enctype="application/x-www-form-urlencoded">
  Item name: <input type = "text" id = "itemName" name = "itemName"/>
  <br/>
  price range: <input type = "radio" id = "itemPriceInterval" name = "itemPriceInterval" value = "all" checked="checked"/>All
  		 <input type = "radio" id = "itemPriceInterval" name = "itemPriceInterval" value = "low"/>low (0 - 100)
  		 <input type = "radio" id = "itemPriceInterval" name = "itemPriceInterval" value = "mid"/>mid (100 - 1000)
         <input type = "radio" id = "itemPriceInterval" name = "itemPriceInterval" value = "high"/>high (> 1000)
  <br/>
  price ranking: <input type = "radio" id = "itemPriceRanking" name = "itemPriceRanking" value = "ascending" checked="checked"/>From Low To High
  		 <input type = "radio" id = "itemPriceRanking" name = "itemPriceRanking" value = "descending"/>From High to Low
  <br/>
  <input type = "hidden" name = "itemState" value = "active"/>
  <input type = "submit" id = "query" value = "Query"/>
  <br/>
  </form> 
      
  </body>
</html>
