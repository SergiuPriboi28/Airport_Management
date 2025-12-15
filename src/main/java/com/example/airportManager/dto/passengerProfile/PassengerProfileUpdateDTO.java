package com.example.airportManager.dto.passengerProfile;

public record PassengerProfileUpdateDTO(
        String firstName,
        String lastName,
        String email,
        String docType,
        int docNumber,
        String nationality
) {
}
