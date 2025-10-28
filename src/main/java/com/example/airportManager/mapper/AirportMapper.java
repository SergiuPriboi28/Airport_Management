package com.example.airportManager.mapper;

import com.example.airportManager.dto.AirportCreateDTO;
import com.example.airportManager.dto.AirportResponseDTO;
import com.example.airportManager.dto.AirportUpdateDTO;
import com.example.airportManager.model.Airport;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AirportMapper {
    Airport toEntity(AirportCreateDTO airportCreateDTO);
    AirportResponseDTO toResponse(Airport airport);
    void updateAirportFromDTO (AirportUpdateDTO airportUpdateDto, @MappingTarget Airport airport);
}
