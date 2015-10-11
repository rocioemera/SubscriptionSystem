<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
			<div id="module_content">
				<c:if test="${fn:length(reqCancellationList) > 0}">
					<table cellpadding="5" id="sub_reportContent">
						<thead id="sub_headerReportContent">
							<th>Customer Name</th>
							<th>Subscription Name</th>
							<th>Date Request</th>
							<th></th>
						</thead>
						<tbody>
							<c:forEach items="${reqCancellationList}" var="reqCancellation" varStatus="status">
								<tr class="<c:if test="${status.count % 2 == 0}">even</c:if>">
									<td>${reqCancellation.getCustSubscription().getCustomer().getFullname()}</td>
									<td>${reqCancellation.getCustSubscription().getSubscription().getName()}</td>
									<td>${reqCancellation.getDateReq()}</td>
									<td>
										<ul class="actions">
											<li><a href="approveCancellation.htm?ID=${reqCancellation.getID()}" 
												class="button special small">Approve Cancellation</a></li>
										</ul>
									<td>	
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
			</div>
		</section>
		<jsp:include page="footer.jspx"/>
	</div>
</body>
</html>