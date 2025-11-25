package com.example.airportManager.mapper;


import com.example.airportManager.dto.flight.FlightCreateDTO;
import com.example.airportManager.dto.flight.FlightResponseDTO;
import com.example.airportManager.dto.flight.FlightUpdateDTO;
import com.example.airportManager.model.Flight;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface FlightMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "aircraft", ignore = true)
    @Mapping(target = "bookings", ignore = true)
    @Mapping(target = "route", ignore = true)
    @Mapping(target = "departureScheduled", ignore = true)
    @Mapping(target = "arrivalScheduled", ignore = true)
    @Mapping(target = "gate", ignore = true)
    Flight toEntity(FlightCreateDTO flightCreateDTO);
    @Mapping(target = "aircraftId", source = "aircraft.id")
    @Mapping(target = "routeId", source = "route.id")
    FlightResponseDTO toResponse(Flight flight);
    @Mapping(target = "aircraft", ignore = true)
    @Mapping(target = "route", ignore = true)
    void updateFlightFromDTO(FlightUpdateDTO flightUpdateDTO, @MappingTarget Flight flight);
}
