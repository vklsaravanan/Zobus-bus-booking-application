<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.zobus.helper.DurationBetween" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="com.zobus.model.SearchEndPointModel" %>

<!DOCTYPE html>
<html>
<head> 
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		<%-- Iterate over the buses and display each item --%>
 	<div class="">
        <c:forEach var="bus" items="${buses}">
        
            ${bus.bus_id}
            ${bus.bus_number}
            ${bus.bus_name}
            ${bus.departure_date_time}
            ${bus.arrival_date_time}
            ${bus.fare}
            ${bus.total_seats}<br>
           	<%-- Declare variables outside the forEach --%>
		    <c:set var="busDeparture" value="${bus.departure_date_time}" />
		    <c:set var="busArrival" value="${bus.arrival_date_time}" />
			
		    <%= DurationBetween.getDurationBetween((Timestamp)pageContext.getAttribute("busDeparture"), (Timestamp)pageContext.getAttribute("busArrival"))
		    %>
		    <c:out value='${travel_duration}'/><br>
		    ${travel_duration}
        </c:forEach>	
      
	</div>
</body>
</html>