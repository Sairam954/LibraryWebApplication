<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="language" value="${language}" scope="session" />

<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle var="bundle" basename="com.finalweb.bundle.local" />
<fmt:message var="search" bundle="${bundle}" key="local.search" />
<fmt:message var="logout" bundle="${bundle}" key="local.logout" />

<html lang="${language}">
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>${userName}User Details</title>
<style>
body {
	background-repeat: no-repeat;
	background-size: cover;
}

.table {
	padding-top: 400px;
}

.form-control {
	display: inline-block;
	width: 80%;
}

label {
	width: 70px;
}

table td {
	vertical-align: top;
}

#update, #delete {
	display: inline;
}
</style>
</head>
<body>






	<nav class="navbar navbar-default" role="navigation"> <!-- Brand and toggle get grouped for better mobile display -->


	<!-- Collect the nav links, forms, and other content for toggling -->
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		<ul class="nav navbar-nav">
			<li class="dropdown"><a href="LoginSucessAdminPage"><button
						type="button" class="btn btn-warning btn-sm">
						<span class="glyphicon glyphicon-home" aria-hidden="true"></span>

					</button></a></li>



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

	</div>
	<c:if test="${errorMessage}!=null">${errorMessage}</c:if>

	<div class="container">
		<h2>All Users</h2>
		<table class="table table-hover">
			<thead class="thead-inverse table-active">

				<tr>

					<th>Name</th>
					<th>Email</th>
					<th>Phonenumber</th>

				</tr>
			</thead>
			<tbody>

				<c:forEach var="user" items="${allusers}">
					<tr>

						<td>${user.userName}</td>
						<td>${user.userEmail}</td>
						<td>${user.phoneNumber}</td>
					</tr>

				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>