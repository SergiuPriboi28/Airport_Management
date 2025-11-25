package com.example.airportManager.service;

import com.example.airportManager.dto.route.RouteCreateDTO;
import com.example.airportManager.dto.route.RouteResponseDTO;
import com.example.airportManager.model.Route;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface RouteService {
    Route getById(Long id);
    Page<RouteResponseDTO> getAll(Pageable pageable,
                                  Optional<Long> originId,
                                  Optional<Long> destId);
    RouteResponseDTO create(RouteCreateDTO routeCreateDTO);
    void delete(Long id);
}
