package com.example.airportManager.controller;

import com.example.airportManager.dto.airport.AirportResponseDTO;
import com.example.airportManager.dto.flight.FlightCreateDTO;
import com.example.airportManager.dto.flight.FlightResponseDTO;
import com.example.airportManager.dto.flight.FlightUpdateDTO;
import com.example.airportManager.model.Flight;
import com.example.airportManager.model.FlightStatus;
import com.example.airportManager.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/flights")
@Validated
@CrossOrigin(origins = "http://localhost:4200")
public class FlightController {

    private final FlightService flightService;

    @GetMapping("/{id}")
    public ResponseEntity<Flight> findFlightById(@PathVariable Long id){
        return ResponseEntity.ok(flightService.getById(id));
    }

    @GetMapping
    public Page<FlightResponseDTO> getAll(
            @ParameterObject @PageableDefault(sort = "departureScheduled", direction = Sort.Direction.ASC) Pageable pageable,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Optional<LocalDateTime> dateFrom,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Optional<LocalDateTime> dateTo,
            @RequestParam Optional<Long> routeId,
            @RequestParam Optional<FlightStatus> status
    ){
        return flightService.getAll(pageable, dateFrom, dateTo, routeId, status);
    }

    @PostMapping
    public ResponseEntity<FlightResponseDTO> create (
            @RequestBody FlightCreateDTO flightCreateDTO){
        return ResponseEntity.status(HttpStatus.CREATED).
                body(flightService.create(flightCreateDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FlightResponseDTO> update(
            @PathVariable Long id,
            @RequestBody FlightUpdateDTO flightUpdateDTO) {
        FlightResponseDTO updatedFlight = flightService.update(id, flightUpdateDTO);
        return ResponseEntity.ok(updatedFlight);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        flightService.delete(id);
        return ResponseEntity.noContent().build();
    }

}