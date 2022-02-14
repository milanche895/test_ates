package com.test.ates.DTO;

import java.util.List;

import com.test.ates.entity.Baggage;
import com.test.ates.entity.Cargo;
import com.test.ates.entity.Flight;

public class FlightBaggageCargoDTO {
	
	private Long flightId;
	
	private List<Baggage> baggage;
	
	private List<Cargo> cargo;
	
	public FlightBaggageCargoDTO() {
		
	}
	public FlightBaggageCargoDTO(Flight flight) {
		this.flightId = flight.getId();
		this.baggage = flight.getBaggage();
		this.cargo = flight.getCargo();
	}
	public Long getFlightId() {
		return flightId;
	}
	public void setFlightId(Long flightId) {
		this.flightId = flightId;
	}
	public List<Baggage> getBaggage() {
		return baggage;
	}
	public void setBaggage(List<Baggage> baggage) {
		this.baggage = baggage;
	}
	public List<Cargo> getCargo() {
		return cargo;
	}
	public void setCargo(List<Cargo> cargo) {
		this.cargo = cargo;
	}

}
