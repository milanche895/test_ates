package com.test.ates.DTO;

import java.sql.Timestamp;

import com.test.ates.entity.Flight;

public class FlightDTO {
	
	private Long flightId;
	
	private Integer flightNumber;
	
	private String departureAirportIATACode;
	
	private String arrivalAirportIATACode;
	
	private Timestamp departureDate;
	
	public FlightDTO() {
		
	}
	public FlightDTO(Flight flight) {
		this.flightId = flight.getId();
		this.flightNumber = flight.getFlightNumber();
		this.departureAirportIATACode = flight.getDepartureAirportIATACode();
		this.arrivalAirportIATACode = flight.getArrivalAirportIATACode();
		this.departureDate = flight.getDepartureDate();
	}
	public Long getFlightId() {
		return flightId;
	}
	public void setFlightId(Long flightId) {
		this.flightId = flightId;
	}
	public Integer getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(Integer flightNumber) {
		this.flightNumber = flightNumber;
	}
	public String getDepartureAirportIATACode() {
		return departureAirportIATACode;
	}
	public void setDepartureAirportIATACode(String departureAirportIATACode) {
		this.departureAirportIATACode = departureAirportIATACode;
	}
	public String getArrivalAirportIATACode() {
		return arrivalAirportIATACode;
	}
	public void setArrivalAirportIATACode(String arrivalAirportIATACode) {
		this.arrivalAirportIATACode = arrivalAirportIATACode;
	}
	public Timestamp getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(Timestamp departureDate) {
		this.departureDate = departureDate;
	}
	
}
