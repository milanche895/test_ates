package com.test.ates.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.ates.entity.Baggage;

public interface BaggageRepository extends JpaRepository<Baggage, Long> {
	
	List<Baggage> findAllByFlightId(Long flightid);

}
