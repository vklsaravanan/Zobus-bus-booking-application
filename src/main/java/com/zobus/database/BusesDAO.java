package com.zobus.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import com.zobus.dbmanager.DatabaseConnectionManager;
import com.zobus.model.SearchBusesModel;
import com.zobus.helper.Date;

public class BusesDAO {
	public static List<SearchBusesModel> searchBus(String source, String destination, String day_of_week) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		day_of_week = Integer.toString(new Date(day_of_week).getDayOfWeek());

		List<SearchBusesModel> buses = new ArrayList<>();
		try {
			connection = DatabaseConnectionManager.getConnection();
			String sql = "SELECT "
					+ "    Buses.bus_id, Buses.bus_number, Buses.bus_name, Buses.total_seats, BusRoutes.departure_date_time, "
					+ "    BusRoutes.arrival_date_time,  BusRoutes.fare FROM  Routes JOIN "
					+ "    BusRoutes ON Routes.route_id = BusRoutes.route_id JOIN "
					+ "    Buses ON BusRoutes.bus_id = Buses.bus_id WHERE Routes.source = ? "
					+ "    AND Routes.destination = ? AND FIND_IN_SET(?, BusRoutes.day_of_week) > 0";
			
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, source);
			preparedStatement.setString(2, destination);
			// here need to convert date into any time because search only by date but time
			// stamp contain time also
			preparedStatement.setInt(3, Integer.valueOf(day_of_week));
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				SearchBusesModel bus = new SearchBusesModel();

				bus.setBus_id(resultSet.getInt("bus_id"));
				bus.setBus_number(resultSet.getString("bus_number"));
				bus.setBus_name(resultSet.getString("bus_name"));
				bus.setTotal_seats(resultSet.getInt("total_seats"));
				bus.setDeparture_date_time(resultSet.getTimestamp("departure_date_time"));
				bus.setArrival_date_time(resultSet.getTimestamp("arrival_date_time"));
				bus.setFare(resultSet.getInt("fare"));  

				buses.add(bus);
			}

			return buses;

		} catch (Exception e) { 
			System.err.println("error from Dao searchBus\n" + e.getMessage() + "\n");

			return null;
		} finally {
			// Close resources in reverse order of opening
			try {
				if (resultSet != null)
					resultSet.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)	
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

