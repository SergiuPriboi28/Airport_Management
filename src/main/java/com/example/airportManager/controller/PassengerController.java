package com.example.airportManager.controller;

import com.example.airportManager.dto.PassengerCreateDTO;
import com.example.airportManager.dto.PassengerResponseDTO;
import com.example.airportManager.model.Passenger;
import com.example.airportManager.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/passengers")
public class PassengerController {

    private final PassengerService passengerService;

    @GetMapping("/{id}")
    public Passenger findPassengerByID(@PathVariable Long id){
        return passengerService.getByID(id);
    }

    @PostMapping
    public ResponseEntity<PassengerResponseDTO> create(@RequestBody PassengerCreateDTO passengerCreateDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(passengerService.create(passengerCreateDTO));
    }

}
