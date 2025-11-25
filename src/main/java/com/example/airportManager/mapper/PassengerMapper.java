package com.example.airportManager.mapper;

import com.example.airportManager.dto.passenger.PassengerCreateDTO;
import com.example.airportManager.dto.passenger.PassengerResponseDTO;
import com.example.airportManager.model.Passenger;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PassengerMapper {
    Passenger toEntity(PassengerCreateDTO passengerCreateDTO);
    PassengerResponseDTO toResponse(Passenger passenger);
}
