<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>Here is the item list</title>
    
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
	
	/*	This part is for redirection and request sending	*/
	$("#homeLink").click(function () {
		window.location.href = "<c:url value = '/home.jsp'/>";
	});
	$("#addNewItem").click(function () {
		window.location.href = "<c:url value = '/redirection/itemAdminAdd.action'/>";
	});
	$("#searchLink").click(function () {
		window.location.href = "<c:url value = '/redirection/itemAdminCriteria.action'/>";
	});
	$("#logoutLink").click(function () {
		window.location.href = "<c:url value = '/user/userLogout.action'/>";
	});	
	
	/*	This is checkbox all-select and all-deselect operation	*/
	$("#selectAll").click(function () {
		$(":checkbox[name='checkedId']").attr("checked",$(this).attr("checked"));
	});
	
	/*  
	 *  Validation requirement:
	 *  1. Register a validation for a speicific form: $("#FormID").validate({...})
	 *  2. Key_in_rules_&_messages = <input>id = <input>name
	 *     -- e.g. currentPageCode = <input>id = <input>name
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
	 
	/*	This part is to specify the operation when "Put On Sale" button is clicked	*/
	$("#putOnSaleInBatches").click(function () {
		/*	Get the number of checked checkbox	*/
		var checkedNumber = $(":checkbox[name='checkedId']:checked").size();
		/*	If there is more than 1 checkbox, go on to process	*/
		if (checkedNumber > 0) {
			/*	If click "yes" on the confirm window, go on to process	*/
			if (window.confirm("Do you really want to put the item(s) ON sale?")) {
				/*	Set the attribute for the specific form & submit	 */
				$("#itemListForm").attr("action", "<c:url value = '/item/putItemsOnSale.action'/>").attr("method", "post").submit();			
			} else {
			/*	If click "no" on the confirm window, then return to original page	*/
				return;
			}
		} else {
		/*	If there is no checked checkedbox, pop out an alert & return to original page	*/
			alert("Please select at least one item");
			return;
		}
	});
	
	/*	This part is to specify the operation when "Put Off Sale" button is clicked	 */
	$("#putOffSaleInBatches").click(function () {
		var checkedNumber = $(":checkbox[name='checkedId']:checked").size();
		if (checkedNumber > 0) {
			if (window.confirm("Do you really want to put the item(s) OFF sale?")) {
				$("#itemListForm").attr("action", "<c:url value = '/item/putItemsOffSale.action'/>").attr("method", "post").submit();
			} else {
				return;
			}	
		} else {
			alert("Please select at least one item");
			return;
		}
	});
});
</script>

  <body>
  <h4 align = "center">Welcome ${sessionScope.user.username}! Here are the items. <a href = "javascript:void(0)" id = "homeLink">Home</a> <a href = "javascript:void(0)" id = "searchLink">Set search conditions</a> <a href = "javascript:void(0)" id = "addNewItem">Add new item</a> <a href = "javascript:void(0)" id = "logoutLink">Log-out</a></h4>

  <form id = "itemListForm" enctype="application/x-www-form-urlencoded">
  	<table border="1" cellspacing="0" bordercolor="pink" width = "50%" align="center">
    	<tr align="center">
    	<c:if test="${sessionScope.buttonFlag eq 'putOnSale' or sessionScope.buttonFlag eq 'putOffSale'}">	
    		<td>
				<input type = "checkbox" id = "selectAll" name = "selectAll"/>
			</td>
		</c:if>	
    		<td>Image</td>
    		<td>Item Name</td>
    		<td>Item Price</td>
    		<td>Operation</td>
    	</tr>
    <c:forEach items="${pageItemBean.beanListForCurrentPage}" var = "bean">
    	<tr align="center">
		<c:if test="${sessionScope.buttonFlag eq 'putOnSale' or sessionScope.buttonFlag eq 'putOffSale'}">
			<td>
				<input type = "checkbox" id = "checkedId" name = "checkedId" value = "${bean.id }"/>
			</td>
		</c:if>
    		<td><img width = "100" height = "100" src="${bean.fullUploadURL }"></td>
    		<td>${bean.itemName }</td>
    		<td>${bean.itemPrice }</td>
    		<td>
    			<a href = "<c:url value = '/item/queryItemById.action'/>?id=${bean.id}">[View Item Details]</a>
    		</td>
    	</tr>
    </c:forEach>
		<tr>
			<td colspan = "5" align="center">
			<c:choose>
				<c:when test="${sessionScope.buttonFlag eq 'putOnSale' }">
					<input type = "submit" id = "putOffSaleInBatches" value = "Put Off Sale"/>
				</c:when>
				<c:when test="${sessionScope.buttonFlag eq 'putOffSale'}">
					<input type = "submit" id = "putOnSaleInBatches" value = "Put On Sale"/>
				</c:when>
				<c:otherwise>
					<font color = "orange" style = "font-weight: bold;">Here are the information of items in all states</font>
				</c:otherwise>
			</c:choose> 	
			</td>
		<tr>
  	</table>
  </form>
  <br/>
  
<%-- This part serves for pagination in back-end --%>
<center id = "paging">
Page:${pageItemBean.currentPageCode}/Total:${pageItemBean.totalPages}
<a href = "<c:url value = '/itemPagination/queryItemListByAdminCriteria.action?currentPageCode=1&itemName=${pageItemBean.itemName }&itemPriceInterval=${pageItemBean.itemPriceInterval }&itemPriceRanking=${pageItemBean.itemPriceRanking }&itemState=${pageItemBean.itemState }'/>">First</a>
<c:if test="${pageItemBean.currentPageCode != 1}">
	<a href = "<c:url value = '/itemPagination/queryItemListByAdminCriteria.action?currentPageCode=${pageItemBean.currentPageCode-1}&itemName=${pageItemBean.itemName }&itemPriceInterval=${pageItemBean.itemPriceInterval }&itemPriceRanking=${pageItemBean.itemPriceRanking }&itemState=${pageItemBean.itemState }'/>">Previous</a>
</c:if>
<c:forEach items="${pageItemBean.displayPageCodes }" var="pageCode">
	<c:choose>
		<c:when test="${pageCode eq pageItemBean.currentPageCode}">
			[${pageCode}]
		</c:when>
		<c:otherwise>
		    <a href = "<c:url value = '/itemPagination/queryItemListByAdminCriteria.action?currentPageCode=${pageCode}&itemName=${pageItemBean.itemName }&itemPriceInterval=${pageItemBean.itemPriceInterval }&itemPriceRanking=${pageItemBean.itemPriceRanking }&itemState=${pageItemBean.itemState }'/>">${pageCode}</a>
		</c:otherwise>
	</c:choose>
</c:forEach>
<c:if test="${pageItemBean.currentPageCode != pageItemBean.totalPages }">
	<a href = "<c:url value = '/itemPagination/queryItemListByAdminCriteria.action?currentPageCode=${pageItemBean.currentPageCode+1}&itemName=${pageItemBean.itemName }&itemPriceInterval=${pageItemBean.itemPriceInterval }&itemPriceRanking=${pageItemBean.itemPriceRanking }&itemState=${pageItemBean.itemState }'/>">Next</a>
</c:if>
<a href = "<c:url value = '/itemPagination/queryItemListByAdminCriteria.action?currentPageCode=${pageItemBean.totalPages}&itemName=${pageItemBean.itemName }&itemPriceInterval=${pageItemBean.itemPriceInterval }&itemPriceRanking=${pageItemBean.itemPriceRanking }&itemState=${pageItemBean.itemState }'/>">Last</a>
</center><br/>

<center>
<form id="pagingQueryForm" method="get" action="<c:url value = '/itemPagination/queryItemListByAdminCriteria.action'/>">
<input type = "hidden" name = "itemName" value = "${pageItemBean.itemName}"/>
<input type = "hidden" name = "itemPriceInterval" value = "${pageItemBean.itemPriceInterval}"/>
<input type = "hidden" name = "itemPriceRanking" value = "${pageItemBean.itemPriceRanking}"/>
<input type = "hidden" name = "itemState" value = "${pageItemBean.itemState }" />
Please enter the page that you want to go: <input type = "text" id="currentPageCode" name="currentPageCode" value = "${pageItemBean.currentPageCode}"/>
<input type="submit" value="go"/>
</form>
</center>

<center>
<form action = "<c:url value = '/itemPagination/queryItemListByAdminCriteria.action'/>" method = "get">
<input type = "hidden" name = "itemName" value = "${pageItemBean.itemName}"/>
<input type = "hidden" name = "itemPriceInterval" value = "${pageItemBean.itemPriceInterval}"/>
<input type = "hidden" name = "itemPriceRanking" value = "${pageItemBean.itemPriceRanking}"/>
<input type = "hidden" name = "itemState" value = "${pageItemBean.itemState }" />
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
