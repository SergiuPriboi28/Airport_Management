package com.example.airportManager.service.impl;

import com.example.airportManager.dto.flight.*;
import com.example.airportManager.exception.ConflictException;
import com.example.airportManager.mapper.FlightMapper;
import com.example.airportManager.model.*;
import com.example.airportManager.repository.AircraftRepository;
import com.example.airportManager.repository.FlightRepository;
import com.example.airportManager.repository.RouteRepository;
import com.example.airportManager.service.FlightService;
import com.example.airportManager.spec.FlightSpecifications;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.time.LocalTime;

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

    @Override
    public FlightSearchResponseDTO searchFlights(LocalDate departureDate,
                                                 LocalDate returnDate,
                                                 FlightType flightType,
                                                 Optional<Long> routeId,
                                                 Optional<FlightStatus> status) {

        LocalDateTime departureStart = departureDate.atStartOfDay();
//        LocalDateTime departureEnd = returnDate.atTime(LocalTime.MAX);
        LocalDateTime departureEnd = departureDate.atTime(LocalTime.MAX);


        Specification<Flight> flightBetweenDeparture = FlightSpecifications.departureBetween(departureStart, departureEnd);

        Specification<Flight> departSpec = flightBetweenDeparture;

        if (routeId.isPresent()) {
            departSpec = departSpec.and(FlightSpecifications.hasRouteId(routeId.get()));
        }

        if (status.isPresent()) {
            departSpec = departSpec.and(FlightSpecifications.hasStatus(status.get()));
        }

        List<FlightResponseDTO> departureFlights = flightRepository.findAll(departSpec)
                .stream().map(flightMapper::toResponse).toList();

        if (flightType == FlightType.RETURN){
            if (routeId.isEmpty()){
                throw new RuntimeException("A valid routeId has to be provided for return flights!");
            }

            Route route = routeRepository.findById(routeId.get())
                    .orElseThrow(() ->
                            new RuntimeException("No route found based on provided route ID"));

            Airport ReturnOriginAirport = route.getDestAirport();
            Airport ReturnDestAirport = route.getOriginAirport();

            LocalDateTime returnStart = returnDate.atStartOfDay();
            LocalDateTime returnEnd = returnDate.atTime(LocalTime.MAX);

            Specification<Flight> flightBetweenReturn = FlightSpecifications.departureBetween(returnStart, returnEnd);

            Specification<Flight> returnSpec = flightBetweenReturn;

            returnSpec = returnSpec.
                    and(FlightSpecifications.hasOrigin(ReturnOriginAirport)).
                    and(FlightSpecifications.hasDest(ReturnDestAirport));

            if (status.isPresent()) {
                returnSpec = returnSpec.and(FlightSpecifications.hasStatus(status.get()));
            }

            List<FlightResponseDTO> returnFlights = flightRepository.findAll(returnSpec)
                    .stream().map(flightMapper::toResponse).toList();

            if (returnFlights.isEmpty()){
                throw new EntityNotFoundException("There are no return flights for the specified date/route!");
            }

            return new FlightSearchResponseDTO(departureFlights, returnFlights);

        }

//       1. Fetch me all departure flights and map to DTOs
//       2. Check FlightType and populate return flights if flight type is "return"
//       3. If return flight has a route specified, locate its reversed (instead of origin-dest -> dest-origin)
//       4. If there is no return flight available, show error msg ("No ret flight available...")
//       5. If there is a flight available, show the result
        return null;
    }

}
