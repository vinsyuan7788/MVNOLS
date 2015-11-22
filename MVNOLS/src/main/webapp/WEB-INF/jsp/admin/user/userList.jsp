<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctm" uri="/WEB-INF/tld/custom-tags.tld" %>

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
	$("#searchLink").click(function () {
		window.location.href = "<c:url value = '/redirection/userAdminCriteria.action'/>";
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
				max: "${pageUserBean.totalPages}"		// EL is appllicable in JS or JS framework (e.g. JQuery, etc.)
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
	
	/*	This part is to specify the operation when "Deleted Selected Users" button is clicked	 */
	$("#disableUsersInBatches").click(function () {
		/*	Get the number of checked checkbox	*/
		var checkedNumber = $(":checkbox[name='checkedId']:checked").size();
		/*	If there is more than 1 checkbox, go on to process	*/
		if (checkedNumber > 0) {
			/*	If click "yes" on the confirm window, go on to process	*/
			if (window.confirm("Careful! Do you really need to disable the user(s)? Once a user is disabled, the recovery procedure will be complex!")) {
				/*	Set the attribute for the specific form & submit	 */
				$("#userListForm").attr("action", "<c:url value = '/user/deleteUsers.action'/>").attr("method", "post").submit();			
			} else {
			/*	If click "no" on the confirm window, then return to original page	*/
				return;
			}
		} else {
		/*	If there is no checked checkedbox, pop out an alert & return to original page	*/
			alert("Please select at least one user");
			return;
		}
	});
	
	/*	This part is to specify the operation when "Recover Selected Users" button is clicked	 */
	$("#enableUsersInBatches").click(function () {
		var checkedNumber = $(":checkbox[name='checkedId']:checked").size();
		if (checkedNumber > 0) {
			if (window.confirm("Do you really want to recover the selected user(s)?")) {
				$("#userListForm").attr("action", "<c:url value = '/user/recoverUsers.action'/>").attr("method", "post").submit();
			} else {
				return;
			}	
		} else {
			alert("Please select at least one user");
			return;
		}
	});
});
</script>



  <body>
    <h4 align = "center">Hello ${sessionScope.user.username} administrator. This is the user list. <a href = "javascript:void(0)" id = "homeLink">Home</a> <a href = "javascript:void(0)" id = "searchLink">Set search conditions</a></h4>
    <form id = "userListForm" enctype="application/x-www-form-urlencoded">
    <ctm:token/>
    <table border="1" cellspacing="0" bordercolor="pink" width = "50%" align="center">
    	<tr align = "center">
    	<c:if test="${sessionScope.buttonFlag eq 'disabled' or sessionScope.buttonFlag eq 'enabled'}">	
    		<td>
				<input type = "checkbox" id = "selectAll" name = "selectAll"/>
			</td>
		</c:if>	
    		<td>id</td>
    		<td>FirstName</td>
    		<td>LastName</td>
    		<td>UserName</td>
    		<td>Gender</td>
    		<td>Email</td>
    		<td>Cellphone</td>
    		<td>Nationality</td>
    		<td>State</td>
    		<td>Score</td>
    		<td colspan = "2">Operation</td>
    	</tr>
	<c:forEach items="${pageUserBean.beanListForCurrentPage}" var = "bean" >
		<tr>
		<c:if test="${sessionScope.buttonFlag eq 'disabled' or sessionScope.buttonFlag eq 'enabled'}">
			<td>
				<input type = "checkbox" id = "checkedId" name = "checkedId" value = "${bean.id }"/>
			</td>
		</c:if>
			<td>${bean.id }</td>
			<td>${bean.firstname }</td>
			<td>${bean.lastname }</td>
			<td>${bean.username }</td>
			<td>${bean.gender }</td>
			<td>${bean.email }</td>
			<td>${bean.cellphone }</td>
			<td>${bean.nationality }</td>
			<td>${bean.state }</td>
			<td>${bean.score }</td>
			<td>
				<a href="<c:url value=''/>">[Edit]</a>
			</td>
			<td>
				<a href="<c:url value=''/>">[Delete]</a>
			</td>
		</tr>
	</c:forEach>
	<tr>
		<td colspan = "13" align="center">
		<c:choose>
			<c:when test="${sessionScope.buttonFlag eq 'enabled' }">
				<input type = "submit" id = "disableUsersInBatches" value = "Delete Selected Users"/>
			</c:when>
			<c:when test="${sessionScope.buttonFlag eq 'disabled'}">
				<input type = "submit" id = "enableUsersInBatches" value = "Recover Selected Users"/>
			</c:when>
			<c:otherwise>
				<font color = "orange" style = "font-weight: bold;">Here are the information of users in all states</font>
			</c:otherwise>
		</c:choose> 	
		</td>
	<tr>
    </table>
    </form>
    <br/>
    
<%-- This part serves for pagination in back-end --%>
<center id = "paging">
Page:${pageUserBean.currentPageCode}/Total:${pageUserBean.totalPages}
<a href = "<c:url value = '/userPagination/queryUserListByAdminCriteria.action?currentPageCode=1&username=${sessionScope.pageUserBean.username }&gender=${sessionScope.pageUserBean.gender }&state=${sessionScope.pageUserBean.state }'/>">First</a>
<c:if test="${pageUserBean.currentPageCode != 1}">
	<a href = "<c:url value = '/userPagination/queryUserListByAdminCriteria.action?currentPageCode=${pageUserBean.currentPageCode-1}&username=${sessionScope.pageUserBean.username }&gender=${sessionScope.pageUserBean.gender }&state=${sessionScope.pageUserBean.state }'/>">Previous</a>
</c:if>
<c:forEach items="${pageUserBean.displayPageCodes }" var="pageCode">
	<c:choose>
		<c:when test="${pageCode eq pageUserBean.currentPageCode}">
			[${pageCode}]
		</c:when>
		<c:otherwise>
		    <a href = "<c:url value = '/userPagination/queryUserListByAdminCriteria.action?currentPageCode=${pageCode}&username=${sessionScope.pageUserBean.username }&gender=${sessionScope.pageUserBean.gender }&state=${sessionScope.pageUserBean.state }'/>">${pageCode}</a>
		</c:otherwise>
	</c:choose>
</c:forEach>
<c:if test="${pageUserBean.currentPageCode != pageUserBean.totalPages }">
	<a href = "<c:url value = '/userPagination/queryUserListByAdminCriteria.action?currentPageCode=${pageUserBean.currentPageCode+1}&username=${sessionScope.pageUserBean.username }&gender=${sessionScope.pageUserBean.gender }&state=${sessionScope.pageUserBean.state }'/>">Next</a>
</c:if>
<a href = "<c:url value = '/userPagination/queryUserListByAdminCriteria.action?currentPageCode=${pageUserBean.totalPages}&username=${sessionScope.pageUserBean.username }&gender=${sessionScope.pageUserBean.gender }&state=${sessionScope.pageUserBean.state }'/>">Last</a>
</center><br/>

<center>
<form id="pagingQueryForm" method="post" action="<c:url value = '/userPagination/queryUserListByAdminCriteria.action'/>">
<input type = "hidden" name = "username" value = "${sessionScope.pageUserBean.username }"/>
<input type = "hidden" name = "gender" value = "${sessionScope.pageUserBean.gender }"/>
<input type = "hidden" name = "state" value = "${sessionScope.pageUserBean.state }"/>
Please enter the page that you want to go: <input type = "text" id="currentPageCode" name="currentPageCode" value = "${pageUserBean.currentPageCode}"/>
<input type="submit" value="go"/>
</form>
</center>

<center>
<form action = "<c:url value = '/userPagination/queryUserListByAdminCriteria.action'/>" method = "post">
<input type = "hidden" name = "username" value = "${sessionScope.pageUserBean.username }"/>
<input type = "hidden" name = "gender" value = "${sessionScope.pageUserBean.gender }"/>
<input type = "hidden" name = "state" value = "${sessionScope.pageUserBean.state }"/>
Please select the page you want to go:
<select name = "currentPageCode">
	<c:forEach var="pageCode" begin = "1" end = "${pageUserBean.totalPages}">
		<option value = "${pageCode}" <c:if test = "${pageCode eq pageUserBean.currentPageCode }">selected = "selected"</c:if>>${pageCode}</option>
	</c:forEach>
</select>
<input type = "submit" value = "go">
</form>
</center>
  </body>
</html>
