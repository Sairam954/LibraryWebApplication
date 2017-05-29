<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="language" value="${language}" scope="session" />

<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle var="bundle" basename="com.finalweb.bundle.local" />
<fmt:message var="logout" bundle="${bundle}" key="local.logout" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>${userName}</title>
<style>
label {
	float: right;
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
	<div class="text-danger">
		<h4>
			<c:if test="${errorMessage!=null}">${errorMessage}</c:if>
		</h4>
	</div>
	<div class="modal-body">


		<div class="container">
			<div class="panel panel-default">
				<div class="panel-heading">
					<b>Account Details 
				</div>
				<div class="panel-body">
					<table class="table table-hover">
						<tr>

							<button type="button" class="btn btn-primary btn-sm pull-right"
								data-toggle="modal" data-target="#edit">
								<span class="glyphicon glyphicon-edit"></span> Edit
							</button>

							<div class="modal fade" id="edit" role="dialog">
								<div class="modal-dialog">

									<!-- Modal content-->
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title">Edit Account</h4>
										</div>
										<div class="modal-body">
											<form class="form-horizontal" action="admin" method="update">
												<input type="hidden" name="commandName" value="updateuser" />


												<div class="form-group">
													<label class="control-label col-sm-4">Name</label>
													<div class="col-sm-8">
														<input type="text" class="form-control" id="userName"
															value="${user.userName}" name="username" required>
													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-sm-4">Email</label>
													<div class="col-sm-8">
														<input type="text" class="form-control" id="userEmail"
															value="${user.userEmail}" name="email" disabled>
													</div>
												</div>

												<div class="form-group">
													<label class="control-label col-sm-4">Phone Number</label>
													<div class="col-sm-8">
														<input type="text" class="form-control"
															id="userPhonenumber" value="${user.phoneNumber}"
															pattern="[0-9]{10}" name="phonenumber" required>
													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-sm-4">Current
														Password </label>
													<div class="col-sm-8">
														<input type="password" class="form-control" id="oldpassword"
															name="oldpassword" required>
													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-sm-4">New Password
													</label>
													<div class="col-sm-8">
														<input type="password" class="form-control" id="newpassword"
															name="newpassword">
													</div>
												</div>

												<div class="form-group">
													<div class="  pull-left">
														<br>
														<button type="submit" class="btn btn-info pull-left">Update
															Account</button>
													</div>
												</div>
											</form>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-default "
												data-dismiss="modal">Close</button>
										</div>
									</div>

								</div>
							</div>

						</tr>
						<tr>
							<td><h5>Name:</td>
							<td>${user.userName}</h5></td>
						</tr>
						<tr>
							<td><h5>Email:</td>
							<td>${user.userEmail}</h5></td>
						</tr>
						<tr>
							<td><h5>Phone Number:</td>
							<td>${user.phoneNumber}</h5></td>
						</tr>


					</table>

				</div>
			</div>

		</div>
	</div>



</body>
</html>