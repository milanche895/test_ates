package com.test.ates.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.test.ates.entity.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
	Flight findFlighteById(Long id);
	
//	@Query(value = "SELECT count(DISTINCT o.orderTime) FROM Order o")
	@Query(value = "SELECT SUM(c.weight) FROM flight f INNER JOIN cargo c ON f.id = c.flight_id WHERE f.id = :flightId", nativeQuery = true)
	Long getSumCargoWeith(@Param("flightId")Long flightId);
	
	@Query(value = "SELECT SUM(b.weight) FROM flight f INNER JOIN baggage b ON f.id = b.flight_id WHERE f.id = :flightId", nativeQuery = true)
	Long getSumBaggageWeith(@Param("flightId")Long flightId);
	
	@Query(value = "SELECT COUNT(f.departure_airport) FROM flight f WHERE f.departure_airport = :departureAirport", nativeQuery = true)
	Long getCountDepartureAirport(@Param("departureAirport")String departureAirport);
	
	@Query(value = "SELECT COUNT(f.arrival_airport) FROM flight f WHERE f.arrival_airport = :arrivalAirport", nativeQuery = true)
	Long getCountArrivalAirport(@Param("arrivalAirport")String arrivalAirport);
	
	@Query(value = "SELECT SUM(b.pieces) FROM flight f INNER JOIN baggage b ON f.id = b.flight_id WHERE f.arrival_airport = :arrivalAirport", nativeQuery = true)
	Long getTotalNumberPiecesArrival(@Param("arrivalAirport")String arrivalAirport);
	
	@Query(value = "SELECT SUM(b.pieces) FROM flight f INNER JOIN baggage b ON f.id = b.flight_id WHERE f.departure_airport = :departureAirport", nativeQuery = true)
	Long getTotalNumberPiecesDeparture(@Param("departureAirport")String departureAirport);
}
