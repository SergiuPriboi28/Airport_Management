package com.example.airportManager.controller;

import com.example.airportManager.dto.FlightCreateDTO;
import com.example.airportManager.dto.FlightResponseDTO;
import com.example.airportManager.dto.FlightUpdateDTO;
import com.example.airportManager.model.Flight;
import com.example.airportManager.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/flights")
public class FlightController {

    private final FlightService flightService;

    @GetMapping("/{id}")
    public ResponseEntity<Flight> findFlightById(@PathVariable Long id){
        return ResponseEntity.ok(flightService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<FlightResponseDTO>> getAll(
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String dir){
        List<FlightResponseDTO> flightList = flightService.getAll(sortBy, dir);
        return ResponseEntity.ok(flightList);
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