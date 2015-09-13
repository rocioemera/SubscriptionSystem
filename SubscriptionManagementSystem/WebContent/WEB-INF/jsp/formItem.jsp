<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
			<form:form action="${REQ_ACTION}" commandName="itemV">
				<form:hidden path="ID" />
			    <table id="sub_tableForm">
					<tr>
						<td>Item Name :</td>
						<td><form:input path="name" /></td>
					</tr>
					<tr>
						<td>Description :</td>
						<td><form:input path="description" /></td>
					</tr>
					
					<tr>
						<td>Item Type :</td>
						<td><form:select path="itemType" class="${SUB_INPUTCLASS}">
							<form:option value="0" label="Select" />
							<form:option value="PRODUCT" label="product" />
							<form:option value="SERVICE" label="service" />
						</form:select></td>
					</tr>
					
					<tr>
						<td>Stock :</td>
						<td><form:input path="stock" /></td>
					</tr>
					<tr>
						<td>Family :</td>
						<td><form:input path="family" /></td>
					</tr>
					<tr>
						<td colspan="2"><input type="submit" value="Register"></td>
					</tr>
				</table>  
			</form:form>
		</section>
		<jsp:include page="footer.jspx"/>
	</div>
</body>
</html>