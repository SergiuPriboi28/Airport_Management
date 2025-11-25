package com.example.airportManager.controller;

import com.example.airportManager.dto.airport.AirportResponseDTO;
import com.example.airportManager.dto.route.RouteCreateDTO;
import com.example.airportManager.dto.route.RouteResponseDTO;
import com.example.airportManager.model.Route;
import com.example.airportManager.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/routes")
@Validated
public class RouteController {

    private final RouteService routeService;

    @GetMapping("/{id}")
    public ResponseEntity<Route> findRouteById(@PathVariable Long id){
        return ResponseEntity.ok(routeService.getById(id));
    }

    @GetMapping
    public Page<RouteResponseDTO> getAll(
            @ParameterObject @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable,
            @RequestParam Optional<Long> originId,
            @RequestParam Optional<Long> destId
    ){
        return routeService.getAll(pageable, originId, destId);
    }

    @PostMapping
    public ResponseEntity<RouteResponseDTO> create (
            @RequestBody RouteCreateDTO routeCreateDTO){
        return ResponseEntity.status(HttpStatus.CREATED).
                body(routeService.create(routeCreateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        routeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}