package com.example.airportManager.dto.booking;

import com.example.airportManager.model.BookingStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record BookingCreateDTO(
        @NotNull(message = "Flight ID is required.") Long flightId,
        @NotNull(message = "Passenger ID is required.") Long passengerId,
        @NotNull BookingStatus status
) {
}
