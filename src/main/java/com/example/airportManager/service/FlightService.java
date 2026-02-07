package com.example.airportManager.service;

import com.example.airportManager.dto.flight.*;
import com.example.airportManager.model.Flight;
import com.example.airportManager.model.FlightStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface FlightService {
    Flight getById(Long id);
    Page<FlightResponseDTO> getAll(Pageable pageable,
                                   Optional<LocalDateTime> dateFrom,
                                   Optional<LocalDateTime> dateTo,
                                   Optional<Long> routeId,
                                   Optional<FlightStatus> status);
    FlightResponseDTO create(FlightCreateDTO flightCreateDTO);
    FlightResponseDTO update(Long id, FlightUpdateDTO flightUpdateDTO);
    void delete(Long id);

    FlightSearchResponseDTO searchFlights(LocalDate departureDate,
                                          LocalDate returnDate,
                                          FlightType flightType,
                                          Optional<Long> routeId,
                                          Optional<FlightStatus> status);
}
