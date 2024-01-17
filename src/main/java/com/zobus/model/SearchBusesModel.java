package com.zobus.model;

import java.sql.Timestamp;

public class SearchBusesModel {
	private int bus_id;
	private String bus_number;
	private String bus_name;
	private Timestamp departure_date_time;
	private Timestamp arrival_date_time;
	private int fare;
	private int total_seats;
	
	/**
	 * @return the bus_id
	 */
	public int getBus_id() {
		return bus_id;
	}
	/**
	 * @param bus_id the bus_id to set
	 */
	public void setBus_id(int bus_id) {
		this.bus_id = bus_id;
	}
	/**
	 * @return the bus_number
	 */
	public String getBus_number() {
		return bus_number;
	}
	/**
	 * @param bus_number the bus_number to set
	 */
	public void setBus_number(String bus_number) {
		this.bus_number = bus_number;
	}
	/**
	 * @return the bus_name
	 */
	public String getBus_name() {
		return bus_name;
	}
	/**
	 * @param bus_name the bus_name to set
	 */
	public void setBus_name(String bus_name) {
		this.bus_name = bus_name;
	}
	/**
	 * @return the departure_date_time
	 */
	public Timestamp getDeparture_date_time() {
		return departure_date_time;
	}
	/**
	 * @param departure_date_time the departure_date_time to set
	 */
	public void setDeparture_date_time(Timestamp departure_date_time) {
		this.departure_date_time = departure_date_time;
	}
	/**
	 * @return the arrival_date_time
	 */
	public Timestamp getArrival_date_time() {
		return arrival_date_time;
	}
	/**
	 * @param arrival_date_time the arrival_date_time to set
	 */
	public void setArrival_date_time(Timestamp arrival_date_time) {
		this.arrival_date_time = arrival_date_time;
	}
	/**
	 * @return the fare
	 */
	public int getFare() {
		return fare;
	}
	/**
	 * @param fare the fare to set
	 */
	public void setFare(int fare) {
		this.fare = fare;
	}
	/**
	 * @return the total_seats
	 */
	public int getTotal_seats() {
		return total_seats;
	}
	/**
	 * @param total_seats the total_seats to set
	 */
	public void setTotal_seats(int total_seats) {
		this.total_seats = total_seats;
	}
	
}
