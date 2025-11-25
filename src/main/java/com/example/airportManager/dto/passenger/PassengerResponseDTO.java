package com.example.airportManager.dto.passenger;

public record PassengerResponseDTO(
        String firstName,
        String lastName,
        String email,
        String docType,
        String docNumber,
        String nationality
) {
}
