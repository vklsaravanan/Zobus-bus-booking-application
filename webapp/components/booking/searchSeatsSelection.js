var form;

$(document).ready(function(){
    // Assign the form inside the ready function
    form = $("#passangerEnterForm");
    $()
});

$(document).on('change keyup click', '#passangerEnterForm input ', function() {
    console.log($(this).val());
});

// Function to create input fields dynamically
function createInputField(seat, busId) {
	
	let name = selected_seats[busId][seat]["name"] 
	var inputName = $('<input required>').attr({
		type: 'text',
		name: 'passengerName',
		placeholder: 'Name',
		value : name!=undefined? name: ""
	});
	
	let age = selected_seats[busId][seat]["age"]
	var inputAge = $('<input required>').attr({
		type: 'text',
		name: 'passengerAge',
		placeholder: 'Age',
		value : age!=undefined? age: ""
	});

	var selectGender = $('<select required>').attr({
		name: 'passengerGender'
	}).append($('<option>').text('Select Gender').attr('value', ''));

	// Add gender options
	['Male', 'Female', 'Other'].forEach(function(gender) {
		selectGender.append($('<option>').text(gender).attr('value', gender.toLowerCase()));
	});
	
	// Append the inputs to the form
	form.append(
		$('<div>').addClass('passenger-form').append(
			$('<h4>').text(seat),
			inputName,
			inputAge,
			selectGender,
		)
	);
	
}

function createPassangerForm(busId) {
	form.empty();
	// Get the selected seats for the specified bus
	var seats = selected_seats[busId];
	// Loop through the selected seats and create input fields
	form.append($("<input>"))
	for (var seat in seats) {
		createInputField(seat, busId);
	}
	form.append(
		$("<button>").text("pay now")
	)
}

// Submit form using Ajax
$('#passangerEnterForm').submit(function(e) {
	e.preventDefault();

	// Get form data
	var formData = $(this).serialize();

	// Add busId to the form data
	formData += '&busId=' + busId;

	// Your Ajax request here
	$.ajax({
		type: 'POST',
		url: 'your-api-endpoint',
		data: formData,
		success: function(response) {
			// Handle success
			console.log(response);
		},
		error: function(error) {
			// Handle error
			console.error(error);
		}
	});
});