package com.test.ates.service.implement;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.test.ates.DTO.FlightBaggageCargoDTO;
import com.test.ates.DTO.FlightDTO;
import com.test.ates.entity.Baggage;
import com.test.ates.entity.Cargo;
import com.test.ates.entity.Flight;
import com.test.ates.repository.BaggageRepository;
import com.test.ates.repository.CargoRepository;
//import com.test.ates.repository.BaggageRepository;
import com.test.ates.repository.FlightRepository;
import com.test.ates.service.FlightService;

@Service
public class FlightServiceImplement implements FlightService {

	@Autowired
	FlightRepository flightRepository;
	
	@Autowired
	BaggageRepository baggageRepository;
	
	@Autowired
	CargoRepository cargoRepository;
	
	public static final String CARGO = "cargo";
	
	public static final String BAGGAGE = "baggage";
	
	public static final String DEPARTURE = "departure";
	
	public static final String ARRIVAL = "arrival";
	
	@Override
	public List<Flight> allFlight(){
		List<Flight> flightList = flightRepository.findAll();
		
		return flightList;
	}
	@Override
	public FlightDTO newFlight(FlightDTO flightDTO) {
		
		if (Objects.nonNull(flightDTO.getFlightNumber()) && Objects.nonNull(flightDTO.getArrivalAirportIATACode()) && 
				Objects.nonNull(flightDTO.getDepartureAirportIATACode())) {
			
			Flight flight = new Flight();
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			
			flight.setFlightNumber(flightDTO.getFlightNumber());
			flight.setArrivalAirportIATACode(flightDTO.getArrivalAirportIATACode());
			flight.setDepartureAirportIATACode(flightDTO.getDepartureAirportIATACode());
			flight.setDepartureDate(timestamp);
			
			
			flightRepository.save(flight);
			
			flightDTO.setFlightId(flight.getId());
			flightDTO.setDepartureDate(timestamp);
			
			return flightDTO;
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Please insert flightNumber, departureAirportIATACode and arrivalAirportIATACode");
		}
	}
	
	@Override 
	public List<FlightBaggageCargoDTO> getAllFlightBaggageCargo() {
		
		List<Flight> flightList = flightRepository.findAll();
		
		List<FlightBaggageCargoDTO> flightBCList = new ArrayList<FlightBaggageCargoDTO>();
		for (Flight flight : flightList) {
			List<Baggage> baggage = baggageRepository.findAllByFlightId(flight.getId());
			List<Cargo> cargo = cargoRepository.findAllByFlightId(flight.getId());
			FlightBaggageCargoDTO flightBCDTO = new FlightBaggageCargoDTO(flight);
			flightBCDTO.setBaggage(baggage);
			flightBCDTO.setCargo(cargo);
			flightBCList.add(flightBCDTO);
		}
		
		return flightBCList;
	}
	@Override
	public Long getWeight(Long flightId, String type) {
		Long weight = 0L;
		
		if (Objects.nonNull(type) && type.equals(CARGO)) {
			weight = flightRepository.getSumCargoWeith(flightId);

		} else if (Objects.nonNull(type) && type.equals(BAGGAGE)) {
			weight = flightRepository.getSumBaggageWeith(flightId);

		} else {
			weight = flightRepository.getSumBaggageWeith(flightId) + flightRepository.getSumCargoWeith(flightId);
		}
		return weight;
	}
	@Override 
	public Long getFlightIataAirport(String iata, String type){
		Long count = 0L;
		if (Objects.nonNull(type) && type.equals(DEPARTURE)) {
			count = flightRepository.getCountDepartureAirport(iata.toUpperCase());
		} else if (Objects.nonNull(type) && type.equals(ARRIVAL)) {
			count = flightRepository.getCountArrivalAirport(iata.toUpperCase());
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please insert airport IATA");
		}
		return count;
	}
	@Override 
	public Long getTotalNumberPieces(String iata, String type){
		Long countPieces = 0L;
		if (Objects.nonNull(type) && type.equals(DEPARTURE)) {
			if (Objects.nonNull(flightRepository.getTotalNumberPiecesDeparture(iata.toUpperCase()))) {
			countPieces = flightRepository.getTotalNumberPiecesDeparture(iata.toUpperCase());
			} else {
				countPieces = 0L;
			}
		} else if (Objects.nonNull(type) && type.equals(ARRIVAL)) {
			if (Objects.nonNull(flightRepository.getTotalNumberPiecesArrival(iata.toUpperCase()))) {
			countPieces = flightRepository.getTotalNumberPiecesArrival(iata.toUpperCase());
			} else {
				countPieces = 0L;
			}
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please insert airport IATA");
		}
		return countPieces;
	}
}
