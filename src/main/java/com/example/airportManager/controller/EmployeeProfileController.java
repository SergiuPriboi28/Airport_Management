package com.example.airportManager.controller;

import com.example.airportManager.dto.employeeProfile.EmployeeProfileResponseDTO;
import com.example.airportManager.dto.employeeProfile.EmployeeProfileUpdateDTO;
import com.example.airportManager.model.EmployeeProfile;
import com.example.airportManager.service.EmployeeProfileService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employee-profiles")
@Validated
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeProfileController {

    private final EmployeeProfileService employeeProfileService;

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeProfile> findMyProfile(@PathVariable UUID id){
        return ResponseEntity.ok(employeeProfileService.getMyProfile(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeProfileResponseDTO> update(
            @PathVariable UUID id,
            @RequestBody EmployeeProfileUpdateDTO dto){
        EmployeeProfileResponseDTO updatedEmployee = employeeProfileService.updateMyProfile(id, dto);
        return ResponseEntity.ok(updatedEmployee);
    }

}
