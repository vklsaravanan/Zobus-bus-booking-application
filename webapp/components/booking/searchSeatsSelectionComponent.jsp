<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<style type="text/css">
	.seatsPassangerEnterDiv {
		position: fixed;
		background-color: #919191eb;
		top: 0;
		width: 100vw;
		height: 100vh;
		display:none;
	}
</style>
<script type="text/javascript" src="components/booking/searchSeatsSelection.js"></script>
</head>
<body>
	<div class="seatsPassangerEnterDiv vh-100 rounded-3">
		<button class="btn seatsPassangerEnterDivCloseBtn">close</button>
		<div class="overflow-auto">
			<div class="seatsListPassInputsDiv">
				<form action="" id="passangerEnterForm">
					<input type="text" hidden id="busId">
					
				</form>
			</div>
		</div>
	</div>
</body>
</html>