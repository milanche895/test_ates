package com.test.ates.service;

import java.util.List;

import com.test.ates.DTO.FlightBaggageCargoDTO;
import com.test.ates.DTO.FlightDTO;
import com.test.ates.entity.Flight;

public interface FlightService {

	List<FlightBaggageCargoDTO> getAllFlightBaggageCargo();

	FlightDTO newFlight(FlightDTO flightDTO);

	Long getWeight(Long flightId, String type);

	List<Flight> allFlight();

	Long getFlightIataAirport(String iata, String type);

	Long getTotalNumberPieces(String iata, String type);

}
