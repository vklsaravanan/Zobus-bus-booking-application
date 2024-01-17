/*
if user retrive new available seats data the 
seats clickale function will apply to newer seats
*/
var selected_seats = {};

$(document).ready(function() {

	var queryParams = new URLSearchParams(location.search);
	var queryDate = null;
	

	if (!queryParams.has('date')) {
		showNotification("Date not specified", "error");
	} else {
		queryDate = queryParams.get("date");
	}

	$('.view_seats_btn').on('click', function() {
		let bus_id = ($(this).attr("id")).replace("busId-", "")
		//remove previews selected seat data on selected_seats variable
		delete selected_seats[bus_id];
		let seat_avail_div = $(`#bus_${bus_id} .seat_availability_div`);
		let view_seat_btn = $(`#busId-${bus_id}`);
		if (seat_avail_div.length > 0) {
			seat_avail_div.slideUp(400, "swing", function() {
				$(this).remove();
				view_seat_btn.text("view seats");
			});
			return;
		}
		let timeFromBus = new Date($(this).attr("time"));
		var firstDate = new Date(queryDate);
		var secondDate = new Date(timeFromBus);

		var year = firstDate.getFullYear();
		var month = firstDate.getMonth();
		var day = firstDate.getDate();

		secondDate.setFullYear(year);
		secondDate.setMonth(month);
		secondDate.setDate(day);

		let timestamp = secondDate.getTime();

		let loc = location.origin + "/" + location.pathname.split("/")[1];
		//		ajex for view seat button 
		$.ajax({
			url: loc + "/api/seat-info?bus_id=" + bus_id + "&date=" + timestamp,
			success: function(data) {
				$(`#bus_${bus_id} .seat_availability_div`).remove();
				setSeatAvailability(data);
				restartTooltip();
				restartSelectSeats();

				$(`#bus_${bus_id} .view_seats_btn`).text("hide seats"); //changing button text function name 
				$.niceToast.success("seat appeared");
			},
			error: function(error) {
				$.niceToast.error("check your internet connection")
			}
		});
		

	});

	function setSeatAvailability(response) {
		var busId = response.seats[0].busId;
		// creating array for seats
		let seatSize = response.seatSize;

		let lowerRight = createTwoDimArray(seatSize.lower.right.row, seatSize.lower.right.column)
		let lowerLeft = createTwoDimArray(seatSize.lower.left.row, seatSize.lower.left.column)
		let upperRight = createTwoDimArray(seatSize.upper.right.row, seatSize.upper.right.column)
		let upperLeft = createTwoDimArray(seatSize.upper.left.row, seatSize.upper.left.column)

		// initialize object for storing seats object into 2D array
		let seats = {
			lower: {
				right: lowerRight,
				left: lowerLeft
			},
			upper: {
				right: upperRight,
				left: upperLeft
			}
		}

		//assigning  seats values to  the seats object 
		for (let seat of response.seats) {
			let side = seat.side == "R" ? "right" : "left";
			seats[(seat.deck).toLowerCase()][side][seat.rowNum][seat.columnNum] = {
				"seat_no": seat.seatNumber,
				"gender": seat.passanger.gender,
				"isAvailable": seat.passanger.gender == " " ? true : false,
				"isSleeper": seat.sleeper
			}
		}

		var seatsDiv = $("<div></div>").addClass("seat_availability_div");

		for (let deck in seatSize) {
			var deckDiv = $("<div></div>").addClass(`deck-${deck}`);
			for (let side in seatSize[deck]) {
				var sideDiv = $("<div></div>").addClass(`${deck}-${side}`); //upper-lower
				for (let i = 0; i < seatSize[deck][side]["column"]; i++) {
					var column = $("<div></div>").addClass("seatColumn");
					for (let j = 0; j < seatSize[deck][side]["row"]; j++) {

						let seatValues = seats[deck.toLowerCase()][side][j][i];
						var dublicateSeat = $("<label></label>");

						if (seatValues) {
							let seat = $("<input type='checkbox'> class=\"seat_checkbox\" ").attr({ "id": `${busId}-${seatValues.seat_no}`});
							dublicateSeat.addClass(`seat sleeper-${seatValues.isSleeper}`);
							let dubSeatAttributes = {
								"for": `${busId}-${seatValues.seat_no}`,
								"data-bs-toggle": "tooltip",
								"data-bs-html": "true",
								"title": `Seat | ${seatValues.seat_no} `
							}
							dublicateSeat.attr(dubSeatAttributes)
							dublicateSeat.addClass(`gen-${seatValues.gender}`)
							column.append(seat);
						} else {
							dublicateSeat.addClass("hereNoSeat")
						}
						column.append(dublicateSeat);
					}
					sideDiv.append(column);
				}
				deckDiv.append(sideDiv);
			} 
			seatsDiv.append(deckDiv);
		}
		$(`div[id=bus_${busId}]`).append(seatsDiv);
		
		// add book button 
		let book_btn = $(`<button class="btn passanger_enter_div" id="book_btn_${busId}" style="display:none;">book</button>`);
		seatsDiv.append(book_btn);
		
		// show seats button
		book_btn.on("click", function(e){ 
			let bus_id = $(this).attr("id").replace("book_btn_","");
			$("body").css("overflow", "hidden");
			$(".seatsPassangerEnterDiv").toggle(function(){
				createPassangerForm(bus_id);
			})
		})
		// close button function for seats passangers enter div
		$(".seatsPassangerEnterDivCloseBtn").on("click",function(){
			$(".seatsPassangerEnterDiv").hide();
			$("body").css("overflow", "auto");	
		})
	}

})

/*
this is helper function which is used to create seat structure
*/
function createTwoDimArray(rows, columns) {
	var array = [];
	for (var i = 0; i < rows; i++) {
		var row = [];
		for (var j = 0; j < columns; j++) {
			row.push(0);
		}
		array.push(row);
	}
	return array;
}

/*
if user retrive new available seats data this tooltip will restart for newer seats
*/
function restartTooltip() {
	var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'))
	var tooltipList = tooltipTriggerList.map(function(tooltipTriggerEl) {
		return new bootstrap.Tooltip(tooltipTriggerEl)
	})
}


function restartSelectSeats() {

	$(".seat").click(function() {
		let a = $("input[id=" + $(this).attr("for") + "]");
		var data = a.attr("id").split("-"); // [0] = busId, [1] = seat_id

		if (!a.is(":checked")) { //if checked

			if (!selected_seats[data[0]]) {
				selected_seats[data[0]] = {};
			}

			if (Object.keys(selected_seats[data[0]]).length > 4) {
				showNotification("Per booking, 5 seats only allowed", "error");
				//	unchecking seat 
				a.prop("checked", ! false);
				
				return;
			} 
			selected_seats[data[0]][data[1]] = {};
		} else { // unchecking
			delete selected_seats[data[0]][data[1]]
		}
		
		//show / hide book btn
		if( Object.keys(selected_seats[data[0]]).length > 0){
			$(`#book_btn_${data[0]}`).slideDown(500, "swing");
		}else{
			$(`#book_btn_${data[0]}`).slideUp(500, "swing");
		}
		console.log(selected_seats)
	});
	
}

