package com.example.airportManager.dto.passenger;

public record PassengerUpdateDTO(
        String firstName,
        String lastName,
        String email,
        String docType,
        int docNumber,
        String nationality
) {
}
