<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.zobus.helper.DurationBetween"%>
<%@ page import="java.sql.Timestamp"%>
<%@ page import="com.zobus.model.SearchBusesModel"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search | Zobus</title>
<link rel="stylesheet" type="text/css" href="assets/css/global/busSeatStructure.css">
</head>
<body>
	<%-- Iterate over the buses and display each item --%>
	<jsp:include page="/components/header/header.jsp" />
	<script type="text/javascript" src="view/search/searchPage.js"></script>
	<jsp:include page="/components/busSearchTools.jsp" />
	<div class="bus_list_div">
		<c:forEach var="bus" items="${buses}">
			<div class="bus_div" id="bus_${bus.bus_id}">
				${bus.bus_id} ${bus.bus_number} ${bus.bus_name}
				${bus.departure_date_time} ${bus.arrival_date_time} ${bus.fare}
				${bus.total_seats}<br>

				<c:set var="busDeparture" value="${bus.departure_date_time}" />
				<c:set var="busArrival" value="${bus.arrival_date_time}" />

				<%=DurationBetween.getDurationBetween((Timestamp) pageContext.getAttribute("busDeparture"),
		(Timestamp) pageContext.getAttribute("busArrival"))%>

				<button class="view_seats_btn" id="busId-${bus.bus_id}" time="${bus.departure_date_time}">
					view seats  
				</button> 

			</div>
			<!-- seat availability -->
			
			<br>
		</c:forEach>
	</div>
	<jsp:include page = "/components/booking/searchSeatsSelectionComponent.jsp" />
</body>
</html>