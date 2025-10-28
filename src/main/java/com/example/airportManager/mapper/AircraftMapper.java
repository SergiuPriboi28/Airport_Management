package com.example.airportManager.mapper;

import com.example.airportManager.dto.AircraftCreateDTO;
import com.example.airportManager.dto.AircraftResponseDTO;
import com.example.airportManager.dto.AircraftUpdateDTO;
import com.example.airportManager.dto.AirportUpdateDTO;
import com.example.airportManager.model.Aircraft;
import com.example.airportManager.model.Airport;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AircraftMapper {
    Aircraft toEntity(AircraftCreateDTO aircraftCreateDTO);
    AircraftResponseDTO toResponse(Aircraft aircraft);
    void updateAircraftFromDTO (AircraftUpdateDTO aircraftUpdateDTO, @MappingTarget Aircraft aircraft);
}
