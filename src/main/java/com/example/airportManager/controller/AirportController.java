package com.example.airportManager.controller;

import com.example.airportManager.dto.airport.AirportCreateDTO;
import com.example.airportManager.dto.airport.AirportResponseDTO;
import com.example.airportManager.model.Airport;
import com.example.airportManager.service.AirportService;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airports")
@Validated
@CrossOrigin(origins = "http://localhost:4200")
public class AirportController {
    private final AirportService airportService;

    @Autowired
    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Airport> findAirportById(@PathVariable Long id){
        return ResponseEntity.ok(airportService.getByID(id));
    }

    @GetMapping
    public Page<AirportResponseDTO> getAll(
            @ParameterObject @PageableDefault(sort = "name", direction = Sort.Direction.ASC)Pageable pageable
            ){
        return airportService.getAll(pageable);
    }

    @PostMapping
    public ResponseEntity<AirportResponseDTO> create (
            @Valid @RequestBody AirportCreateDTO airportCreateDTO){
        return ResponseEntity.status(HttpStatus.CREATED).
                body(airportService.create(airportCreateDTO));
    }

//    add update method

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        airportService.delete(id);
        return ResponseEntity.noContent().build();
    }

}