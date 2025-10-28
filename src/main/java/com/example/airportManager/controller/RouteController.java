package com.example.airportManager.controller;

import com.example.airportManager.dto.RouteCreateDTO;
import com.example.airportManager.dto.RouteResponseDTO;
import com.example.airportManager.model.Route;
import com.example.airportManager.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/routes")
public class RouteController {

    private final RouteService routeService;

    @GetMapping("/{id}")
    public ResponseEntity<Route> findRouteById(@PathVariable Long id){
        return ResponseEntity.ok(routeService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<RouteResponseDTO>> getAll(
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String dir){
        List<RouteResponseDTO> routeList = routeService.getAll(sortBy, dir);
        return ResponseEntity.ok(routeList);
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