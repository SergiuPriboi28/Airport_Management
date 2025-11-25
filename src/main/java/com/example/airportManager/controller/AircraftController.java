package com.example.airportManager.controller;

import com.example.airportManager.dto.aircraft.AircraftCreateDTO;
import com.example.airportManager.dto.aircraft.AircraftResponseDTO;
import com.example.airportManager.dto.aircraft.AircraftUpdateDTO;
import com.example.airportManager.dto.airport.AirportResponseDTO;
import com.example.airportManager.model.Aircraft;
import com.example.airportManager.service.AircraftService;
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

@RestController
@RequestMapping("/api/aircraft")
@Validated
public class AircraftController {

    private final AircraftService aircraftService;

    @Autowired
    public AircraftController(AircraftService aircraftService) {
        this.aircraftService = aircraftService;
    }

    @GetMapping("/{id}")
    public Aircraft findAircraftById(@PathVariable Long id){
        return aircraftService.getById(id);
    }

    @GetMapping
    public Page<AircraftResponseDTO> getAll(
            @ParameterObject @PageableDefault(sort = "name", direction = Sort.Direction.ASC)Pageable pageable
    ){
        return aircraftService.getAll(pageable);
    }

    @PostMapping
    public ResponseEntity<AircraftResponseDTO> create (
            @RequestBody AircraftCreateDTO aircraftCreateDTO){
        return ResponseEntity.status(HttpStatus.CREATED).
                body(aircraftService.create(aircraftCreateDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AircraftResponseDTO> update(
            @PathVariable Long id,
            @RequestBody AircraftUpdateDTO aircraftUpdateDTO) {
        AircraftResponseDTO updatedAircraft = aircraftService.update(id, aircraftUpdateDTO);
        return ResponseEntity.ok(updatedAircraft);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        aircraftService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
