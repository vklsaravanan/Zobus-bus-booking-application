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
	<jsp:include page="components/busSearchTools.jsp"/>
	

</body>
</html>  