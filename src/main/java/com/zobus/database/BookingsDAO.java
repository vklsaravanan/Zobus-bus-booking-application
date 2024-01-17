package com.zobus.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.servlet.RequestDispatcher;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.zobus.dbmanager.DatabaseConnectionManager;
import com.zobus.model.BusSeatsModel;
import com.zobus.model.BusSeatsModel.Deck;
import com.zobus.model.BusSeatsModel.Side;
import com.zobus.model.PassengersModel;

public class BookingsDAO {
	
	public static Object GetAvailableSeats(String bus_id, Timestamp date_time) {
		Connection connection = null;
		PreparedStatement stm =null;
		ResultSet result=null; 
		String query = "SELECT  "
				+ "    bus_seats.*, ps.gender, buses.*  "
				+ "FROM "
				+ "    bus_seats "
				+ "        LEFT JOIN "
				+ "    ( SELECT  "
				+ "        passengers.* "
				+ "    FROM "
				+ "        bookings b "
				+ "    JOIN passengers ON b.booking_id = passengers.booking_id "
				+ "    WHERE "
				+ "        b.bus_id = ? "
				+ "            AND b.booking_status = 'booked' "
				+ "            AND b.journey_date_time = ?) AS ps ON bus_seats.seat_id = ps.seat_number "
				+ "        JOIN "
				+ "    zobus.buses ON buses.bus_id = bus_seats.bus_id "
				+ "WHERE "
				+ "    bus_seats.bus_id = ?;";
		
		try {
			connection = DatabaseConnectionManager.getConnection();
			
			stm =  connection.prepareStatement(query);
			stm.setInt(1, Integer.parseInt(bus_id));
			stm.setTimestamp(2, date_time);
			stm.setInt(3, Integer.parseInt(bus_id));
			result = stm.executeQuery();
			
//			List<BusSeatsModel> seats = new ArrayList<>();
			JsonArray seats = new JsonArray();
			JsonObject seatSize = new JsonObject();
			
			while(result.next()) {
					BusSeatsModel busSeat = new BusSeatsModel();
					PassengersModel passenger = new PassengersModel();
					 
					busSeat.setBusId( result.getInt("bus_id") );
					busSeat.setSeatId( result.getInt("seat_id") ); 
					
					busSeat.setSeatNumber( result.getString("seat_no") );
					busSeat.setSide( result.getString("side").equals("L")? Side.L:Side.R );
//					System.err.println(result.getString("deck"));
					busSeat.setDeck( result.getString("deck").equals("lower")? Deck.LOWER :Deck.UPPER );
					busSeat.setColumnNum( result.getInt("column_num") );
					busSeat.setRowNum( result.getInt("row_num") ); 
					busSeat.setSleeper( result.getBoolean("sleeper") );
					
					String gender = result.getString("gender");
					
					passenger.setGender(gender==null? '\u0000' : gender.charAt(0) );
					//storing total seat size with conditon
					if ( seatSize.size() == 0 && result.getInt("lower_left_column")!=0) {
						JsonObject lower = new JsonObject();
						JsonObject lowerRight = new JsonObject();
						JsonObject lowerLeft = new JsonObject();
						lowerRight.addProperty("row", result.getInt("lower_right_row"));
						lowerRight.addProperty("column", result.getInt("lower_right_column"));
						lowerLeft.addProperty("row", result.getInt("lower_left_row"));
						lowerLeft.addProperty("column", result.getInt("lower_left_column"));
						lower.add("right", lowerRight);
						lower.add("left", lowerLeft);
						
						seatSize.add("lower", lower);
						 
						//checking is upper deck exist or not
						byte upperRightRow = (byte)result.getInt("upper_right_row");
						byte upperLeftRow = (byte)result.getInt("upper_left_row");
						
						if(upperRightRow > 0 && upperLeftRow >0) { 
							JsonObject upper = new JsonObject();
							JsonObject upperRight = new JsonObject();
							JsonObject upperLeft = new JsonObject();
							
							upperRight.addProperty("row", upperRightRow);
							upperRight.addProperty("column", result.getInt("upper_right_column"));
							upperLeft.addProperty("row", upperLeftRow);
							upperLeft.addProperty("column", result.getInt("upper_left_column"));
							
							upper.add("right", upperRight);
							upper.add("left", upperLeft);
  
							seatSize.add("upper", upper);
							 
						}
						
					}
					busSeat.setPassanger(passenger); 
					String temp =  new Gson().toJson( busSeat);
					seats.add( new Gson().fromJson(temp, JsonObject.class));
					
			}
			
			JsonObject data = new JsonObject();
			
			data.add("seatSize", seatSize);
			data.add("seats", seats);
			
			return data; 
			
		}catch(Exception e) {
			System.out.println(e+"\n error from BookingDao.getAvailableseats");
		} 
		 finally {
				// Close resources in reverse order of opening
				try {
					if (result != null)
						result.close();
					if (stm != null)
						stm.close();
					if (connection != null)	
						connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		return "{}";  
	}
}
