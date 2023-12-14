<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<div align="center">
	<div style="display: flex;
    justify-content: center;"> <span> ZOBUS </span> 
		<form action="login?redirectTo=<%= request.getServletPath() %>" method="get">
	        <button type="submit">login</button>
	    </form>
	    <form action="signup" method="get">
	        <button type="submit">signup </button>
	    </form>
	</div>
</div>