package com.example.airportManager.dto.passengerProfile;

public record PassengerProfileResponseDTO(
        String docType,
        String docNumber,
        String nationality,
        String loyaltyTier,
        String emergencyContact
) {
}
