<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>This is the item detail page</title>
    
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

	/*	This part is for the item operation	 */
	$("#putOnSale").click(function () {
		if (window.confirm("Do you really want to publish this item?")) {
			$("#itemDetailForm").attr("action", "<c:url value = '/item/putItemOnSale.action'/>").attr("method", "post").submit();
		} else {
			return;
		}
	});
	$("#putOffSale").click(function () {
		if (window.confirm("Do you really want to remove this item?")) {
			$("#itemDetailForm").attr("action", "<c:url value = '/item/putItemOffSale.action'/>").attr("method", "post").submit();
		} else {
			return;	
		}
	});
	$("#republish").click(function () {
		if (window.confirm("Do you really want to republish this item?")) {
			$("#itemDetailForm").attr("action", "<c:url value = '/item/republishItem.action'/>").attr("method", "post").submit();
		} else {
			return;	
		}
	});	
	$("#editItem").click(function () {
		alert("Going to 'itemInfo.jsp' page...");
		return;
	});
});
</script>

  
  <body>
    <h4 align = "center">Here is the detailed information of the item. <a href = "<c:url value = '/home.jsp'/>">Home</a></h4>	
    
<form id = "itemDetailForm" enctype="application/x-www-form-urlencoded"> 
    <table border="1" cellspacing="0" bordercolor="pink" width = "50%" align="center">
    	<caption>Item Details</caption>
    	<tr>
    		<td>ID</td>
    		<td><input type = "text" name = "id" value = "${item.id }" readonly="readonly"/></td>
    	</tr>
     	<tr>
    		<td>Name</td>
    		<td><input type = "text" name = "itemName" value = "${item.itemName }" readonly="readonly"/></td>
    	</tr>
    	<tr>
    		<td>Price</td>
    		<td><input type = "text" name = "itemPrice" value = "${item.itemPrice }" readonly="readonly"/></td>
    	</tr>
    	<tr>
    		<td>Stock</td>
    		<td><input type = "text" name = "itemStock" value = "${item.itemStock }" readonly="readonly"/></td>
    	</tr>    
    	<tr>
    		<td>Manufacture Place</td>
    		<td><input type = "text" name = "itemPlaceOfManufacture" value = "${item.itemPlaceOfManufacture }" readonly="readonly"/></td>
    	</tr>   
    	<tr>
    		<td>State</td>
    		<td><input type = "text" name = "itemState" value = "${item.itemState }" readonly="readonly"/></td>
    	</tr>  
    	<tr>
    		<td>Image</td>
    		<td>
    			<img width = "100" height = "100" src="${item.fullUploadURL }"/>
    		</td>
    	</tr>
    	<tr>
    		<td>Detail</td>
    		<td><input name = "itemDetail" value = "${item.itemDetail }" readonly="readonly"/></td>
    	</tr>
    <c:if test="${item.itemState eq 'unactive' }">
     	<tr>
    		<td>Operation</td>
    		<td>
    			<input type = "button" id = "putOnSale" value = "Put On Sale"/>
    			<input type = "button" id = "editItem" value = "Edit Item Information"/>
    		</td>
    	</tr>   	
    </c:if>
    <c:if test="${item.itemState eq 'active' }">
     	<tr>
    		<td>Operation</td>
    		<td>
   				<input type = "button" id = "putOffSale" value = "Put Off Sale"/>
   				<input type = "button" id = "republish" value = "Republish">
    			<input type = "button" id = "editItem" value = "Edit Item Information"/>
    		</td>
    	</tr>   
    </c:if>    
    </table>
</form>
 
  </body>
</html>
