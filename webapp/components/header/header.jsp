
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.zobus.helper.URLManager"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search | Zobus</title>

</head>
<body>

	<jsp:include page="/assets/add-on/notification.jsp" />
	<jsp:include page="/assets/add-on/bootstrapAndPopper.jsp" />


	<div align="center">
		<div style="display: flex; justify-content: center;">
			<span> ZOBUS </span>
			<%
			request.getSession().setAttribute("redirectTo", URLManager.getFullURL(request));

			String user_name = (String) session.getAttribute("user_name");
			if (user_name != null) {
			%>
			<button><%=user_name%></button>
			<button>My bookings</button>
			<button>cancel Ticket</button>
			<form action="logout" method="post">
				<button type="submit">logout</button>
			</form>
			<%
			} else {
			// setting redirectAttribute for redirectTo request if user want to login into new page
			%>
			<form action="login" method="get">
				<button type="submit">login</button>
			</form>
			<form action="signup" method="get">
				<button type="submit">signup</button>
			</form>
			<%
			} // if condition close
			%>
		</div>
	</div>

</body>
</html>