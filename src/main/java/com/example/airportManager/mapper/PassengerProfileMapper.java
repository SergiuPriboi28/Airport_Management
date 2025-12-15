package com.example.airportManager.mapper;

import com.example.airportManager.dto.passengerProfile.PassengerProfileCreateDTO;
import com.example.airportManager.dto.passengerProfile.PassengerProfileResponseDTO;
import com.example.airportManager.dto.passengerProfile.PassengerProfileUpdateDTO;
import com.example.airportManager.model.PassengerProfile;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PassengerProfileMapper {
    PassengerProfile toEntity(PassengerProfileCreateDTO passengerProfileCreateDTO);
    PassengerProfileResponseDTO toResponse(PassengerProfile passengerProfile);
    void updatePassengerProfileFromDTO(PassengerProfileUpdateDTO passengerProfileUpdateDTO, @MappingTarget PassengerProfile passengerProfile);
}
