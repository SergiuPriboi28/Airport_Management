package com.example.airportManager.controller;

import com.example.airportManager.dto.AirportCreateDTO;
import com.example.airportManager.dto.AirportResponseDTO;
import com.example.airportManager.dto.AirportUpdateDTO;
import com.example.airportManager.model.Airport;
import com.example.airportManager.service.AirportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/airport")
public class AirportController {

    private final AirportService airportService;

    @GetMapping("/{id}")
    public ResponseEntity<Airport> findAirportById(@PathVariable Long id){
        return ResponseEntity.ok(airportService.getByID(id));
    }

    @GetMapping
    public ResponseEntity<List<AirportResponseDTO>> getAll(
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String dir){
        List<AirportResponseDTO> airportList = airportService.getAll(sortBy, dir);
        return ResponseEntity.ok(airportList);
    }

    @PostMapping
    public ResponseEntity<AirportResponseDTO> create (
            @RequestBody AirportCreateDTO airportCreateDTO){
        return ResponseEntity.status(HttpStatus.CREATED).
                body(airportService.create(airportCreateDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AirportResponseDTO> update(
            @PathVariable Long id,
            @RequestBody AirportUpdateDTO airportUpdateDTO) {
        AirportResponseDTO updatedAirport = airportService.update(id, airportUpdateDTO);
        return ResponseEntity.ok(updatedAirport);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        airportService.delete(id);
        return ResponseEntity.noContent().build();
    }

}