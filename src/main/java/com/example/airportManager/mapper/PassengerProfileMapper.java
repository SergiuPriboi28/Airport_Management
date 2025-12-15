package com.example.airportManager.mapper;

import com.example.airportManager.dto.passenger.PassengerProfileCreateDTO;
import com.example.airportManager.dto.passenger.PassengerProfileResponseDTO;
import com.example.airportManager.model.PassengerProfile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PassengerProfileMapper {
    PassengerProfile toEntity(PassengerProfileCreateDTO passengerProfileCreateDTO);
    PassengerProfileResponseDTO toResponse(PassengerProfile passengerProfile);
}
