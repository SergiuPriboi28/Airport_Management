package com.example.airportManager.controller;

import com.example.airportManager.dto.AircraftCreateDTO;
import com.example.airportManager.dto.AircraftResponseDTO;
import com.example.airportManager.dto.AircraftUpdateDTO;
import com.example.airportManager.model.Aircraft;
import com.example.airportManager.service.AircraftService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/aircraft")
public class AircraftController {

    private final AircraftService aircraftService;

    @GetMapping("/{id}")
    public Aircraft findAircraftById(@PathVariable Long id){
        return aircraftService.getById(id);
    }

    @GetMapping
    public ResponseEntity<List<AircraftResponseDTO>> getAll(
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String dir){
        List<AircraftResponseDTO> aircraftList = aircraftService.getAll(sortBy, dir);
        return ResponseEntity.ok(aircraftList);
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
