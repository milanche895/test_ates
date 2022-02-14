package com.test.ates.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.ates.DTO.FlightBaggageCargoDTO;
import com.test.ates.DTO.FlightDTO;
import com.test.ates.entity.Flight;
import com.test.ates.service.FlightService;

@RestController
@RequestMapping("/flight")
public class FlightController {

	@Autowired
	FlightService flightService;
	
	@GetMapping("/all")
	public ResponseEntity<List<Flight>> getAllFlight(){
		return new ResponseEntity<List<Flight>>(flightService.allFlight(),HttpStatus.OK);
	}
	
	@PostMapping("/new")
	public ResponseEntity<FlightDTO> newFlight(@RequestBody FlightDTO flightDTO){
		return new ResponseEntity<FlightDTO>(flightService.newFlight(flightDTO),HttpStatus.OK);
	}
	
	@GetMapping("/cargo/baggage")
	public ResponseEntity<List<FlightBaggageCargoDTO>> getAllFlightBaggageCargo(){
		return new ResponseEntity<List<FlightBaggageCargoDTO>>(flightService.getAllFlightBaggageCargo(),HttpStatus.OK);
	}
	@GetMapping("/weight/{flightId}")
	public ResponseEntity<Long> getWeight(@PathVariable(name = "flightId") Long flightId,
											@RequestParam(name="type", required=false)String type){
		return new ResponseEntity<Long>(flightService.getWeight(flightId, type), HttpStatus.OK);
	}
	@GetMapping("/{type}")
	public ResponseEntity<Long> getFlightIataAirport(@PathVariable(name = "type") String type,
											@RequestParam(name="iata", required=false)String iata){
		return new ResponseEntity<Long>(flightService.getFlightIataAirport(iata, type), HttpStatus.OK);
	}
	@GetMapping("/baggage/{type}")
	public ResponseEntity<Long> getTotalNumberPieces(@PathVariable(name = "type") String type,
											@RequestParam(name="iata", required=false)String iata){
		return new ResponseEntity<Long>(flightService.getTotalNumberPieces(iata, type), HttpStatus.OK);
	}
}
