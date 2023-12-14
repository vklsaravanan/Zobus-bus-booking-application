<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title> ${initParam.projectName} </title>
		<!-- jquery  -->
	<script type="text/javascript" src="assets/js/global/jquery-3.6.1.min.js"></script>
	<script type="text/javascript" src="assets/js/basePageSupporter.js"></script>
</head>
<body>
<jsp:include page="components/header/header.jsp"/>
<p>Search bus</p>

	<form action="<%=request.getContextPath()%>/search" method="get">
		<input type="text">
		<input type="text">
		<input type="date">
		<button type="submit">search</button>
	</form>
	
</body>
weq</html>  