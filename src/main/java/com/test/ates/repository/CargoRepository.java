package com.test.ates.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.ates.entity.Cargo;

public interface CargoRepository extends JpaRepository<Cargo, Long> {

	List<Cargo> findAllByFlightId(Long flightId);
}
