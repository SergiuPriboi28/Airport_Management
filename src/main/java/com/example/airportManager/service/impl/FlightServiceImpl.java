package com.example.airportManager.service.impl;

import com.example.airportManager.dto.flight.FlightCreateDTO;
import com.example.airportManager.dto.flight.FlightResponseDTO;
import com.example.airportManager.dto.flight.FlightUpdateDTO;
import com.example.airportManager.exception.ConflictException;
import com.example.airportManager.mapper.FlightMapper;
import com.example.airportManager.model.Aircraft;
import com.example.airportManager.model.Flight;
import com.example.airportManager.model.FlightStatus;
import com.example.airportManager.model.Route;
import com.example.airportManager.repository.AircraftRepository;
import com.example.airportManager.repository.FlightRepository;
import com.example.airportManager.repository.RouteRepository;
import com.example.airportManager.service.FlightService;
import com.example.airportManager.spec.FlightSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final RouteRepository routeRepository;
    private final FlightMapper flightMapper;
    private final AircraftRepository aircraftRepository;

    @Override
    public Flight getById(Long id) {
        Flight flight = flightRepository.findById(id).
                orElseThrow(()->new RuntimeException("No Flight Found"));
        return  flight;
    }

    @Override
    public Page<FlightResponseDTO> getAll(Pageable pageable,
                                          Optional<LocalDateTime> dateFrom,
                                          Optional<LocalDateTime> dateTo,
                                          Optional<Long> routeId,
                                          Optional<FlightStatus> status)
    {
        Specification<Flight> spec = Specification.where(null);
        if (dateFrom.isPresent() || dateTo.isPresent()) {
            spec = spec.and(FlightSpecifications.departureBetween(dateFrom.orElse(null), dateTo.orElse(null)));
        }
        if (routeId.isPresent()) {
            spec = spec.and(FlightSpecifications.hasRouteId(routeId.get()));
        }
        if (status.isPresent()) {
            spec = spec.and(FlightSpecifications.hasStatus(status.get()));
        }

        return flightRepository.findAll(spec, pageable)
                .map(flightMapper::toResponse);
    }

    @Override
    public FlightResponseDTO create(FlightCreateDTO flightCreateDTO) {

        if (flightRepository.findByCode(flightCreateDTO.code()).isPresent()) {
            throw new com.example.airportManager.exception.ConflictException("Flight with code " + flightCreateDTO.code() + " already exists");
        }

        Route route = routeRepository.findById(flightCreateDTO.routeId())
                .orElseThrow(() -> new RuntimeException("Passenger with id: "
                        + flightCreateDTO.routeId()
                        + " does not exist"));

        Flight flight = flightMapper.toEntity(flightCreateDTO);
        flight.setRoute(route);
        flight.setAircraft(aircraftRepository.findById(1L).orElseThrow(()-> new RuntimeException("No aircraft with id 1: exists!")));
        Flight flightSave = flightRepository.save(flight);
        return flightMapper.toResponse(flightSave);
    }

    @Override
    public FlightResponseDTO update(Long id, FlightUpdateDTO flightUpdateDTO) {

        Optional<Flight> existingWithCode = flightRepository.findByCode(flightUpdateDTO.code());

        if (existingWithCode.isPresent() && !existingWithCode.get().getId().equals(id))
        {
            throw new ConflictException("Flight with code " + flightUpdateDTO.code() + " already exists");
        }

        Route route = routeRepository.findById(flightUpdateDTO.routeId())
                .orElseThrow(()-> new RuntimeException("No route with id: "
                        + flightUpdateDTO.routeId()
                        + " exists!"));

        Aircraft aircraft = aircraftRepository.findById(flightUpdateDTO.aircraftId())
                .orElseThrow(()-> new RuntimeException("No aircraft with id : "
                        + flightUpdateDTO.aircraftId()
                        + " exists!"));

        Flight oldFlight = flightRepository.findById(id).
                orElseThrow(()->new RuntimeException("No Flight Found"));

        oldFlight.setCode(flightUpdateDTO.code());
        oldFlight.setRoute(route);
        oldFlight.setDepartureScheduled(flightUpdateDTO.departureScheduled());
        oldFlight.setArrivalScheduled(flightUpdateDTO.arrivalScheduled());
        oldFlight.setGate(flightUpdateDTO.gate());
        oldFlight.setAircraft(aircraft);
        oldFlight.setStatus(flightUpdateDTO.flightStatus());

        flightMapper.updateFlightFromDTO(flightUpdateDTO, oldFlight);
        Flight updatedFlight = flightRepository.save(oldFlight);
        return flightMapper.toResponse(updatedFlight);
    }

    @Override
    public void delete(Long id) {
        flightRepository.deleteById(id);
    }
}
