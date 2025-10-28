package com.example.airportManager.mapper;


import com.example.airportManager.dto.FlightCreateDTO;
import com.example.airportManager.dto.FlightResponseDTO;
import com.example.airportManager.dto.FlightUpdateDTO;
import com.example.airportManager.model.Flight;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface FlightMapper {
    Flight toEntity(FlightCreateDTO flightCreateDTO);
    FlightResponseDTO toResponse(Flight flight);
    void updateFlightFromDTO(FlightUpdateDTO flightUpdateDTO, @MappingTarget Flight flight);
}
