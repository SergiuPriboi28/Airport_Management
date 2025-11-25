package com.example.airportManager.mapper;

import com.example.airportManager.dto.aircraft.AircraftCreateDTO;
import com.example.airportManager.dto.aircraft.AircraftResponseDTO;
import com.example.airportManager.dto.aircraft.AircraftUpdateDTO;
import com.example.airportManager.model.Aircraft;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AircraftMapper {
    Aircraft toEntity(AircraftCreateDTO aircraftCreateDTO);
    AircraftResponseDTO toResponse(Aircraft aircraft);
    void updateAircraftFromDTO (AircraftUpdateDTO aircraftUpdateDTO, @MappingTarget Aircraft aircraft);
}
