package com.example.airportManager.dto.booking;

import com.example.airportManager.model.BookingStatus;

public record BookingUpdateDTO(
        String pnr,
        String flight,
        String passenger,
        BookingStatus bookingStatus
) {
}
