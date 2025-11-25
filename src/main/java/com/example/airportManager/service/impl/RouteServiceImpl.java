package com.example.airportManager.service.impl;

import com.example.airportManager.dto.route.RouteCreateDTO;
import com.example.airportManager.dto.route.RouteResponseDTO;
import com.example.airportManager.exception.ConflictException;
import com.example.airportManager.mapper.RouteMapper;
import com.example.airportManager.model.Airport;
import com.example.airportManager.model.Route;
import com.example.airportManager.repository.AirportRepository;
import com.example.airportManager.repository.RouteRepository;
import com.example.airportManager.service.RouteService;
import com.example.airportManager.spec.RouteSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {

    private final RouteRepository routeRepository;
    private final RouteMapper routeMapper;
    private final AirportRepository airportRepository;

    @Override
    public Route getById(Long id) {
        Route route = routeRepository.findById(id).
                orElseThrow(()->new RuntimeException("No Route Found"));
        return route;
    }

    @Override
    public Page<RouteResponseDTO> getAll(Pageable pageable,
                                         Optional<Long> originId,
                                         Optional<Long> destId)
    {
        Specification<Route> spec = Specification.where(null);
        if (originId.isPresent()) {
            spec = spec.and(RouteSpecifications.hasOriginAirportId(originId.get()));
        }
        if (destId.isPresent()) {
            spec = spec.and(RouteSpecifications.hasDestAirportId(destId.get()));
        }
        return routeRepository.findAll(spec, pageable)
                .map(routeMapper::toResponse);
    }

    @Override
    public RouteResponseDTO create(RouteCreateDTO routeCreateDTO) {

        if (routeRepository.existsByOriginAirportIdAndDestAirportId(
                routeCreateDTO.originAirportId(),
                routeCreateDTO.destAirportId())
        ){
            throw new ConflictException("Route already exists for this origin-destination pair");
        }

        Airport originAirport = airportRepository.findById(routeCreateDTO.originAirportId())
                .orElseThrow(()-> new RuntimeException("Origin airport with id: "
                        + routeCreateDTO.originAirportId()
                        + " does not exist"));

        Airport destAirport = airportRepository.findById(routeCreateDTO.destAirportId())
                .orElseThrow(() -> new RuntimeException("Dest airport with id: " +
                         routeCreateDTO.destAirportId()
                        + " does not exist"));


        Route route = routeMapper.toEntity(routeCreateDTO);

        route.setDestAirport(destAirport);
        route.setOriginAirport(originAirport);

        Route routeSave = routeRepository.save(route);
        return routeMapper.toResponse(routeSave);
    }

    @Override
    public void delete(Long id) {
        routeRepository.deleteById(id);
    }
}
