package com.example.airportManager.dto;

import com.example.airportManager.model.BookingStatus;

public record BookingUpdateDTO(
        String pnr,
        String flight,
        String passenger,
        BookingStatus bookingStatus
) {
}
