<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="language" value="${language}" scope="session" />

<fmt:setLocale value="${language}" scope="session" />
<fmt:setBundle var="bundle" basename="com.finalweb.bundle.local" />
<fmt:message var="search" bundle="${bundle}" key="local.search" />
<fmt:message var="logout" bundle="${bundle}" key="local.logout" />
<fmt:message var="booktitle" bundle="${bundle}" key="local.booktitle" />
<fmt:message var="author" bundle="${bundle}" key="local.author" />
<fmt:message var="description" bundle="${bundle}"
	key="local.description" />
<fmt:message var="action" bundle="${bundle}" key="local.action" />
<fmt:message var="moreinfo" bundle="${bundle}" key="local.moreinfo" />
<fmt:message var="removebook" bundle="${bundle}" key="local.removebook" />
<fmt:message var="deletebook" bundle="${bundle}" key="local.deletebook" />
<fmt:message var="updatebook" bundle="${bundle}" key="local.updatebook" />

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
<title>${userName}Library</title>
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
</style>
</head>
<body>






	<nav class="navbar navbar-default" role="navigation"> <!-- Brand and toggle get grouped for better mobile display -->


	<!-- Collect the nav links, forms, and other content for toggling -->
	<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		<ul class="nav navbar-nav">


			<!-- <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Language<b class="caret"></b></a>
        <ul class="dropdown-menu">
          <li><a href="">English</a></li>
          <li><a href="">Hindi</a></li>
        
        </ul>
      </li> -->
		</ul>
		
		<ul class="nav navbar-nav navbar-right">

			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown">${userName} <b class="caret"></b></a>
				<ul class="dropdown-menu">
					<li><a href="admin?commandName=accountDetails">Account Details</a></li>
					<li><a href="logout?commandName=logout">${logout}</a>
						</form></li>

				</ul></li>
		</ul>
	</div>
	<div></div>



	<!-- /.navbar-collapse --> </nav>
	<div class="container">
		<button type="button" class="btn btn-primary btn-lg"
			data-toggle="modal" data-target="#addBook">Add New Book</button>
	</div>
	<div class="modal fade" id="addBook" role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">New Book Details</h4>
				</div>
				<div class="modal-body">
					<form class="form-horizontal" action="admin?commandName=createBook"
						method="post">
						<div class="form-group">
							<label class="control-label col-sm-2" >Book
								Title</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="bookTitle"
									placeholder="Enter Title" name="bookTitle" required>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" >Author</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="author"
									placeholder="Enter Author" name="bookAuthor" required>
							</div>
						</div>

						<div class="form-group">
							<label class="control-label col-sm-2" >Book Type</label>
							<div class="col-sm-10">


								<SELECT name="bookType" required>
									<OPTION value="ebook" selected>Ebook</OPTION>
									<OPTION value="paperback">paperback</OPTION>
								</SELECT>


							</div>
						</div>
						<div class="form-group" required>
							<label class="control-label col-sm-2" for="pwd">Book
								Language</label>
							<div class="col-sm-10">
								<label> <input type="checkbox" name="bookLanguage"
									value="English">English
								</label>

							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-2" for="pwd">Description</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" id="description"
									placeholder="Enter Description" name="description">
							</div>
						</div>

						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<button type="submit" class="btn btn-info">Create Book</button>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>

		</div>
	</div>

	</div>
<c:if test="${errorMessage}!=null">${errorMessage}</c:if>

	<div class="container">
		<h2>All Books</h2>
		<table class="table table-hover">
			<thead class="thead-inverse table-active">

				<tr>

					<th>${booktitle}</th>
					<th>${author}</th>
					<th>${moreinfo}</th>
					<th>${action}</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach var="book" items="${allBooks}">
					<tr>

						<td>${book.bookTitle}</td>
						<td>${book.bookAuthor}</td>
						<td>

							<button type="button" class="btn btn-info btn-sm"
								data-toggle="modal" data-target="#${book.bookId}">More
								Info</button> <!-- Modal -->
							<div class="modal fade" id="${book.bookId}" role="dialog">
								<div class="modal-dialog">

									<!-- Modal content-->
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title">Book Details</h4>
										</div>
										<div class="modal-body">
											<table>
												<td>
												<tr>
													<h5>Book Title :${book.bookTitle}</h5>
												</tr>
												<tr>
													<h5>Auther name :${book.bookAuthor}</h5>
												</tr>
												<tr>
													<h5>Book Type:${book.bookType}</h5>
												</tr>
												<tr>
													<h5>Book Description :${book.description}</h5>
												</tr>
												</td>
											</table>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-default"
												data-dismiss="modal">Close</button>
										</div>
									</div>

								</div>
							</div>

						</td>



						<td><a
							href="admin?commandName=deletebook&bookId=${book.bookId}"><button
									type="button" class="btn btn-danger btn-sm ">${deletebook}</button>
						</a>
							<button type="button" class="btn btn-primary btn-sm"
								data-toggle="modal" data-target="#update${book.bookId}">Update
								Book</button>
							<div class="modal fade" id="update${book.bookId}" role="dialog">
								<div class="modal-dialog">

									<!-- Modal content-->
									<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal">&times;</button>
											<h4 class="modal-title">Update Book</h4>
										</div>
										<div class="modal-body">
											<form class="form-horizontal"
												action="admin?commandName=updateBook&bookId=${book.bookId}"
												method="post">
												<div class="form-group">
													<label class="control-label col-sm-2" >Book
														Title</label>
													<div class="col-sm-10">
														<input type="text" class="form-control" id="bookTitle"
															value="${book.bookTitle}" name="bookTitle" required>
													</div>
												</div>
												<div class="form-group" >
													<label class="control-label col-sm-2" >Author</label>
													<div class="col-sm-10">
														<input type="text" class="form-control" id="bookAuthor"
															value="${book.bookAuthor}" name="bookAuthor" required>
													</div>
												</div>

												<div class="form-group">
													<label class="control-label col-sm-2" >Book
														Type</label>
													<div class="col-sm-10">
														<SELECT name="bookType" required>
															<OPTION value="ebook" selected>Ebook</OPTION>
															<OPTION value="paperback">paperback</OPTION>
														</SELECT>
													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-sm-2" >Description</label>
													<div class="col-sm-10">
														<input type="text" class="form-control" id="description"
															value="${book.description}" name="description">
													</div>
												</div>
												<div class="form-group">
													<label class="control-label col-sm-2" checked>Book
														Language</label>
													<div class="col-sm-10">
														<label class="radio-inline"> <input type="radio"
															name="bookLanguage" value="English">${book.bookLanguage}
														</label>

													</div>
												</div>

												<div class="form-group">
													<div class="col-sm-offset-2 col-sm-10">
														<button type="submit" class="btn btn-info">Update
															Book</button>
													</div>
												</div>
											</form>
										</div>
										<div class="modal-footer">
											<button type="button" class="btn btn-default"
												data-dismiss="modal">Close</button>
										</div>
									</div>

								</div>
							</div></td>


					</tr>

				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>