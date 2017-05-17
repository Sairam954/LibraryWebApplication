<%@ page language="java"  pageEncoding="UTF-8"
   %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
  <c:set var="language"
	value="${language}"
	scope="session" />

<fmt:setLocale value="${language}"  scope="session"/>
<fmt:setBundle var="bundle" basename="com.finalweb.bundle.local" />

<html lang="${language}">
<head>
  
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <title>${userName} Library</title>
  <style>
body {
   
    background-repeat: no-repeat;
    background-size:cover;
}
      .table{
          
           padding-top: 400px;
          
          
      }
</style>
</head>
<body>






<nav class="navbar navbar-default" role="navigation">
  <!-- Brand and toggle get grouped for better mobile display -->
  

  <!-- Collect the nav links, forms, and other content for toggling -->
  <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
    <ul class="nav navbar-nav">
     
      
      <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">Language<b class="caret"></b></a>
        <ul class="dropdown-menu">
          <li><a href="">English</a></li>
          <li><a href="">Hindi</a></li>
        
        </ul>
      </li>
    </ul>
    <div class="col-sm-3 col-md-3">
        <form class="navbar-form" role="search" action="search" >
        <div class="input-group">
        <input type="hidden" name="commandName" value="search" />
            <input type="text" class="form-control" placeholder="Search" name="search">
            <div class="input-group-btn">
                <button class="btn btn-default" type="submit"><i class="glyphicon glyphicon-search"></i></button>
            </div>
        </div>
        </form>
    </div>
    <ul class="nav navbar-nav navbar-right">
      
      <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown">${userName} <b class="caret"></b></a>
        <ul class="dropdown-menu">
          <li><form class="form-group" action="logout" method="post">
		<input type="hidden" name="commandName" value="Logout" />
		<button type="submit"><fmt:message bundle="${bundle}"
						key="logout" /></button>
	
	</form></li>
          
        </ul>
      </li>
    </ul>
  </div>
    <div>
        
        
    </div>
    
    
    
    <!-- /.navbar-collapse -->
</nav>
    <div class="container">
    </div>
                                <div class="text-danger">
				<h4><c:if test="${notFound!=null}">${notFound}</c:if></h4>
			</div> 
    <table class="table table-hover">
  <thead class="thead-inverse table-active">
  
    <tr>
      
      <th>Book Title</th>
      <th>Author</th>
      <th>Description</th>
    </tr>
  </thead>
  <tbody>
   
		<c:forEach var="searchBook" items="${searchBooks}">
			<tr>
				
				<td>${searchBook.bookTitle}</td>
				<td>${searchBook.bookAuthor}</td>
				<td>${searchBook.description}</td>
			</tr>

		</c:forEach>
  </tbody>
</table>
    </div>
    </body>
</html>