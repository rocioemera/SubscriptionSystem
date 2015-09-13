<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<style type="text/css">
		#popupbox{
			 position: fixed;
			 top: 50px;
	    	right: 20px;
			visibility: hidden; 
		}
	</style>
	<script language="JavaScript" type="text/javascript">
		function login(showhide){
		if(showhide == "show"){
		    document.getElementById('popupbox').style.visibility="visible";
		}else if(showhide == "hide"){
		    document.getElementById('popupbox').style.visibility="hidden"; 
		}
		}
	</script>
	<head>
		<title>Internet of Things</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/main.css" />
		<!--[if lte IE 8]><link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/ie8.css" /><![endif]-->
	</head>
	<body class="landing">
		<div id="page-wrapper">

			<!-- Header -->
				<header id="header" class="alt">
					
					<nav id="nav">
						<ul>
							<li><a href="index.html">Home</a></li>
							
							<li><a href="javascript:login('show');" class="button">Sign In</a></li>
						</ul>
					</nav>
					
					<div id="popupbox" class="box"> 
						
							<form name="login" action="" method="post">
								<h3>Username:</h3>
								<input style="color: #5D5D55;" type="text" name="username" id="name"  />
								<h3>Password:</h3>
								
								<input style="color: #5D5D55;" name="password" type="password"  />
								<ul class="actions">
											<li><input class="button small" style="background-color: #5D5D55;"  type="submit" name="submit" value="login" /></li>
											<li><a class="button special small" style="background-color: #5D5D55;"  href="javascript:login('hide');">close</a></li>
								</ul>
												
							</form>
						
						
					</div> 
					
				</header>

			<!-- Banner -->
				<section id="banner">
					<h2>Internet of Things</h2>
					<p>IoT services is a company that offers on demand services using the Internet of Things Technology</p>
					<ul class="actions">
						<li><a href="#" class="button special">Sign Up</a></li>
						<li><a href="administrator.jsp" class="button">Learn More</a></li>
					</ul>
				</section>

			<!-- Main -->
				<section id="main" class="container">

					<section class="box special">
						<header class="major">
							<h2>Introducing new subscription packages
							<br />
							for home automation and smart security</h2>
							<p>IoT services offers new hardware and software that lets you easily monitor, control, and secure your home from anywhere..</p>
						</header>
						
					</section>

					

					<div class="row">
						<div class="4u 7u(narrower)">

							<section class="box special">
								<span class="image featured"><img src="${pageContext.request.contextPath}/images/pic02.jpg" alt="" /></span>
								<h3>Sed lorem adipiscing</h3>
								<p>Integer volutpat ante et accumsan commophasellus sed aliquam feugiat lorem aliquet ut enim rutrum phasellus iaculis accumsan dolore magna aliquam veroeros.</p>
								<h3>$10/month</h3>
								<ul class="actions">
									<li><a href="#" class="button alt">Subscribe</a></li>
								</ul>
							</section>

						</div>
						
						<div class="4u 7u(narrower)">

							<section class="box special">
								<span class="image featured"><img src="${pageContext.request.contextPath}/images/pic02.jpg" alt="" /></span>
								<h3>Sed lorem adipiscing</h3>
								<p>Integer volutpat ante et accumsan commophasellus sed aliquam feugiat lorem aliquet ut enim rutrum phasellus iaculis accumsan dolore magna aliquam veroeros.</p>
								<h3>$10/month</h3>
								<ul class="actions">
									<li><a href="#" class="button alt">Subscribe</a></li>
								</ul>
							</section>

						</div>
						<div class="4u 7u(narrower)">

							<section class="box special">
								<span class="image featured"><img src="${pageContext.request.contextPath}/images/pic03.jpg" alt="" /></span>
								<h3>Accumsan integer</h3>
								<p>Integer volutpat ante et accumsan commophasellus sed aliquam feugiat lorem aliquet ut enim rutrum phasellus iaculis accumsan dolore magna aliquam veroeros.</p>
								<h3>$10/month</h3>
								<ul class="actions">
									<li><a href="#" class="button alt">Subscribe</a></li>
								</ul>
							</section>

						</div>
					</div>

				</section>

		

			<!-- Footer -->
				<footer id="footer">
					<ul class="icons">
						<li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
						<li><a href="#" class="icon fa-facebook"><span class="label">Facebook</span></a></li>
						<li><a href="#" class="icon fa-instagram"><span class="label">Instagram</span></a></li>
						<li><a href="#" class="icon fa-google-plus"><span class="label">Google+</span></a></li>
					</ul>
					<ul class="copyright">
						<li>&copy; IoT services. All rights reserved.</li><li>2015</li>
					</ul>
				</footer>

		</div>

		<!-- Scripts -->
			<script src="${pageContext.request.contextPath}/assets/js/jquery.min.js"></script>
			<script src="${pageContext.request.contextPath}/assets/js/jquery.dropotron.min.js"></script>
			<script src="${pageContext.request.contextPath}/assets/js/jquery.scrollgress.min.js"></script>
			<script src="${pageContext.request.contextPath}/assets/js/skel.min.js"></script>
			<script src="${pageContext.request.contextPath}/assets/js/util.js"></script>
			<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
			<script src="${pageContext.request.contextPath}/assets/js/main.js"></script>

	</body>
</html>