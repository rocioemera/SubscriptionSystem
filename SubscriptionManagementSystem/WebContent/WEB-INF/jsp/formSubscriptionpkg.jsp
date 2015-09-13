<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="java.util.ArrayList" %>
<%@page import="com.subscriptionmng.model.admin.Item" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>IoT subscription management system</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css" />
	<script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/jquery.dropotron.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/jquery.scrollgress.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/skel.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/util.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/main.js"></script>
	<script src="${pageContext.request.contextPath}/resources/common/js/sub_main.js"></script>
	<link href="${pageContext.request.contextPath}/resources/common/css/sub_menustyle.css" rel="stylesheet"/>
</head>
<body>
	<div id="page-wrapper">
		<jsp:include page="menu.jspx"/>
		<section id="main" class="container">
			<form:form action="${REQ_ACTION}" commandName="subscriptionV">
				<form:hidden path="ID" />
			    <table id="sub_tableForm">
			    	<tr>
						<td colspan="2"><input type="submit" value=${FORM_SUBMIT_VALUE}></td>
					</tr>
					<tr>
						<td>Subscription Name :</td>
						<td><form:input path="name" /></td>
					</tr>
					<tr>
						<td>Description :</td>
						<td><form:input path="description" /></td>
					</tr>
					<tr>
						<td>Price :</td>
						<td><form:input path="price" /></td>
					</tr >
						<td colspan="2">
							<div id="sub_pickItemSubscription">
							<div style="color:#e89980;padding-bottom: 2px">Select Items:</div>
							<form:checkboxes title="Select Items:" 
								path="items" id="items" items="${itemList}" 
								itemLabel="name" itemValue="ID" element="div"/>
							</div>
						</td>
				</table>
				
			</form:form>
		</section>
		<jsp:include page="footer.jspx"/>
	</div>
</body>
</html>