package com.example.airportManager.dto.passengerProfile;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PassengerProfileCreateDTO(
        String docType,
        String docNumber,
        String nationality,
        String emergencyContact
){}