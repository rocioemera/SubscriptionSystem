<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
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
			<div class="row">
				<div id="sb-search" class="sb-search">
					<form action="${pageContext.request.contextPath}/homeView.htm" >
						<input class="sb-search-input" placeholder="Enter your search term..." type="search" value="" name="search" id="search">
						<input type="submit" class="button special small" style="height: 58px; width:10%; float: right" value="Search">
					</form>
				</div>
			</div>
			<div class="row">
				<c:forEach items="${subscriptionList}" var="subscription" varStatus="status">
					<div class="4u 7u(narrower)">
						<form:form action="${pageContext.request.contextPath}/customerManage/purchase.htm" 
							commandName="subscriptionV" 
							>
						<input type="hidden" name="ID" value="${subscription.getID()}" />
						<input type="hidden" name="version" value="${subscription.getVersion()}" />
						<input type="hidden" name="name" value="${subscription.getName()}" />
						<input type="hidden" name="description" value="${subscription.getDescription()}" />
						<input type="hidden" name="price" value="${subscription.getPrice()}" />
						
							<section class="box special">
								<span class="image featured"><img src="${pageContext.request.contextPath}/images/pic02.jpg" alt="" /></span>
								<h3>${subscription.getName()}</h3>
								<p>${subscription.getDescription()}</p>
								<h3>${subscription.getPrice()}/month</h3>
								<ul class="actions">
									<li><input type="submit" class="button special small" value="Purchase"></li>
								</ul>
								<!-- 
								<ul class="actions">
									<li><a href="${pageContext.request.contextPath}/customerManage/purchase.htm?ID=${subscription.getID()}" 
										class="button special small">Purchase</a></li>
								</ul>
								 -->
							</section>
						</form:form>
					</div>
				</c:forEach>
			</div>
		</section>
		<jsp:include page="footer.jspx"/>
	</div>
</body>
</html>