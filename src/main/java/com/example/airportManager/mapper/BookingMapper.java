package com.example.airportManager.mapper;

import com.example.airportManager.dto.BookingCreateDTO;
import com.example.airportManager.dto.BookingResponseDTO;
import com.example.airportManager.dto.BookingUpdateDTO;
import com.example.airportManager.model.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring")
public interface BookingMapper {
    Booking toEntity(BookingCreateDTO bookingCreateDTO);
    BookingResponseDTO toResponse(Booking booking);
    void updateBookingFromDto(BookingUpdateDTO bookingUpdateDTO, @MappingTarget Booking booking);
}
