$(document).ready(function() {
	$("#search_bus_btn").click(function(event) {
		event.preventDefault();

		// Get form action URL

		// Get form data
		var fromValue = $("#bus_from_input").val();
		var toValue = $("#bus_to_input").val();
		var dateValue = $("#bus_departure_date").val();
		console.log(dateValue)
		if (/^([0-9]{4}-[0-9]{2}-[0-9]{2})$/.test('2023-12-90')) {
			dateValue = dateValue;
		} else {
			showNotification("Date not correct", 'error');

		}


		// Construct the query string
		var queryString = "from=" + fromValue + "&to=" + toValue + "&date=" + dateValue;

		// Update form action with the query string
		var updatedAction = location.href + queryString;
		$(this).attr('action', updatedAction);
		console.log("Updated Action:", updatedAction);


		location.assign(location.href + 'search?' + queryString);
	});
});
