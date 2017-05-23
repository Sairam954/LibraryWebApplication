<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
<link
 href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
 rel="stylesheet">
<script
 src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script
 src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; pageEncoding="UTF-8"">
<title>Admin Home Page</title>
</head>
<body>
	<h1><fmt:message bundle="${bundle}"
						key="welcome" /> Admin ${userName}</h1>
	<form action="logout" method="post">
		<input type="hidden" name="commandName" value="Logout" />
		<button type="submit">Logout</button>
	
	</form>

</body>
</html>