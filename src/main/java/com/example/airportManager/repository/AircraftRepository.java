package com.example.airportManager.repository;

import com.example.airportManager.model.Aircraft;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AircraftRepository extends JpaRepository<Aircraft, Long> {
//    @NotBlank(message = "Aircraft ID is required.")
    Optional<Aircraft> findById(Long aircraftId);
}
