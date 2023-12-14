<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>cancel ticket | ${initParam.projectName}</title>
</head>
<body>
	<!-- include the header JSP file --> 
	<jsp:include page="/components/header/header.jsp"/>
	<input type="text" id="cancal_booking_id">
	<button type="button" id="cancal_booking_btn">submit</button>
</body>
</html>