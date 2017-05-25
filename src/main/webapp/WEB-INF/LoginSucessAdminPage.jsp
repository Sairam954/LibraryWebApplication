<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="language" value="${language}" scope="session" />

<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle var="bundle" basename="com.finalweb.bundle.local" />
<fmt:message var="logout" bundle="${bundle}" key="local.logout" />

<html lang="${language}">


<head>
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; pageEncoding="UTF-8"">
<style>
body {
	font-family: "Open Sans", "Segoe UI", Frutiger, "Frutiger Linotype",
		"Dejavu Sans", "Helvetica Neue", Arial, sans-serif;
	font-size: 14px;
	line-height: 1.5em;
	font-weight: 400;
}

.tilewrapper {
	margin: auto;
	width: 600px;
}

p, span, a, ul, li, button {
	font-family: inherit;
	font-size: inherit;
	font-weight: inherit;
	line-height: inherit;
}

strong {
	font-weight: 600;
}

h1, h2, h3, h4, h5, h6 {
	font-family: "Open Sans", "Segoe UI", Frutiger, "Frutiger Linotype",
		"Dejavu Sans", "Helvetica Neue", Arial, sans-serif;
	line-height: 1.5em;
	font-weight: 300;
}

strong {
	font-weight: 400;
}

.tile {
	width: 100%;
	display: inline-block;
	box-sizing: border-box;
	background: #fff;
	padding: 20px;
	margin-bottom: 30px;
}

.tile .title {
	margin-top: 0px;
}

.tile.purple, .tile.blue, .tile.red, .tile.orange, .tile.green {
	color: #fff;
}

.tile.purple {
	background: #5133ab;
}

.tile.purple:hover {
	background: #3e2784;
}

.tile.red {
	background: #ac193d;
}

.tile.red:hover {
	background: #7f132d;
}

.tile.green {
	background: #00a600;
}

.tile.green:hover {
	background: #007300;
}

.tile.blue {
	background: #2672ec;
}

.tile.blue:hover {
	background: #125acd;
}

.tile.orange {
	background: #dc572e;
}

.tile.orange:hover {
	background: #b8431f;
}
</style>
<title>Admin Home Page</title>
</head>
<body>
	<nav class="navbar navbar-default" role="navigation"> <!-- Brand and toggle get grouped for better mobile display -->


	<!-- Collect the nav links, forms, and other content for toggling -->
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		<ul class="nav navbar-nav">
			

			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown">Language<b class="caret"></b></a>
				<ul class="dropdown-menu">
					<li><a href="">English</a></li>
					<li><a href="">Hindi</a></li>

				</ul></li>
		</ul>

		<ul class="nav navbar-nav navbar-right">

			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown">${userName} <b class="caret"></b></a>
				<ul class="dropdown-menu">
					<li><a href="admin?commandName=accountDetails">Account
							Details</a></li>
					<li><a href="logout?commandName=logout">${logout}</a>
						</form></li>

				</ul></li>
		</ul>
	</div>
	<div></div>



	<!-- /.navbar-collapse --> </nav>
	<div class="row">
		<div class="col-md-12">
			<h1 align="center">
				<strong>Welcome ${userName} Admin</strong>
			</h1>
		</div>
	</div>
	<div class="container tilewrapper">

		<div class="row">
			<div class="col-sm-6">
				<div class="tile purple">
					<h3 class="title">User Details</h3>

				</div>
			</div>
			<div class="col-sm-6">
				<a href="admin?commandName=allbookadmin">
					<div class="tile red">
						<h3 class="title">Book Details</h3>

					</div>
				</a>
			</div>

		</div>

	</div>

</body>
</html>