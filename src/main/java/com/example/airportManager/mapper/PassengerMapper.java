package com.example.airportManager.mapper;

import com.example.airportManager.dto.PassengerCreateDTO;
import com.example.airportManager.dto.PassengerResponseDTO;
import com.example.airportManager.model.Passenger;
import com.example.airportManager.service.PassengerService;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PassengerMapper {
    Passenger toEntity(PassengerCreateDTO passengerCreateDTO);
    PassengerResponseDTO toResponse(Passenger passenger);
}
