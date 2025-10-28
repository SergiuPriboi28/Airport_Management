package com.example.airportManager.service;

import com.example.airportManager.dto.RouteCreateDTO;
import com.example.airportManager.dto.RouteResponseDTO;
import com.example.airportManager.model.Route;

import java.util.List;

public interface RouteService {
    Route getById(Long id);
    List<RouteResponseDTO> getAll(String sortBy, String dir);
    RouteResponseDTO create(RouteCreateDTO routeCreateDTO);
    void delete(Long id);
}
