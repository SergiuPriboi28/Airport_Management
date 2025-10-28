package com.example.airportManager.mapper;

import com.example.airportManager.dto.RouteCreateDTO;
import com.example.airportManager.dto.RouteResponseDTO;
import com.example.airportManager.model.Route;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface RouteMapper {
    Route toEntity(RouteCreateDTO routeCreateDTO);
    RouteResponseDTO toResponse(Route route);
}
