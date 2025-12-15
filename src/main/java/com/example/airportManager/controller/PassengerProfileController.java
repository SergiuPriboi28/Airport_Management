package com.example.airportManager.controller;

import com.example.airportManager.dto.passengerProfile.PassengerProfileCreateDTO;
import com.example.airportManager.dto.passengerProfile.PassengerProfileResponseDTO;
import com.example.airportManager.dto.passengerProfile.PassengerProfileUpdateDTO;
import com.example.airportManager.model.PassengerProfile;
import com.example.airportManager.service.PassengerProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/passenger-profiles")
@Validated
public class PassengerProfileController {

    private final PassengerProfileService passengerProfileService;

    @GetMapping("/{id}")
    public PassengerProfile findPassengerProfileByID(@PathVariable UUID id){
        return passengerProfileService.getByID(id);
    }

    @PostMapping
    public ResponseEntity<PassengerProfileResponseDTO> create(
            @RequestBody PassengerProfileCreateDTO passengerProfileCreateDTO){

        return ResponseEntity.status(HttpStatus.CREATED).
                body(passengerProfileService.create(passengerProfileCreateDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PassengerProfileResponseDTO> update(
            @PathVariable UUID id,
            @RequestBody PassengerProfileUpdateDTO dto) {
        PassengerProfileResponseDTO updatedPassenger = passengerProfileService.update(id, dto);
        return ResponseEntity.ok(updatedPassenger);
    }

}
