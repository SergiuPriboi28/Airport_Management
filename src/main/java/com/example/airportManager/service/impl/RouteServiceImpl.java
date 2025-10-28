package com.example.airportManager.service.impl;

import com.example.airportManager.dto.RouteCreateDTO;
import com.example.airportManager.dto.RouteResponseDTO;
import com.example.airportManager.mapper.RouteMapper;
import com.example.airportManager.model.Route;
import com.example.airportManager.repository.RouteRepository;
import com.example.airportManager.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;
    private final RouteMapper routeMapper;

    @Override
    public Route getById(Long id) {
        Route route = routeRepository.findById(id).
                orElseThrow(()->new RuntimeException("No Route Found"));
        return route;
    }

    @Override
    public List<RouteResponseDTO> getAll(String sortBy, String dir) {
        Sort.Direction direction = dir.equalsIgnoreCase("desc")
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;
        return routeRepository.findAll(Sort.by(direction, sortBy)).
                stream().map(routeMapper::toResponse).toList();

    }

    @Override
    public RouteResponseDTO create(RouteCreateDTO routeCreateDTO) {
        Route route = routeMapper.toEntity(routeCreateDTO);
        Route routeSave = routeRepository.save(route);
        return routeMapper.toResponse(routeSave);
    }

    @Override
    public void delete(Long id) {
        routeRepository.deleteById(id);
    }
}
