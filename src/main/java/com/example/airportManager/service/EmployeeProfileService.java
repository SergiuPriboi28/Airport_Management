package com.example.airportManager.service;

import com.example.airportManager.dto.employeeProfile.EmployeeProfileResponseDTO;
import com.example.airportManager.dto.employeeProfile.EmployeeProfileUpdateDTO;
import com.example.airportManager.model.EmployeeProfile;

import java.util.UUID;

public interface EmployeeProfileService {
    EmployeeProfile getMyProfile(UUID id);
    EmployeeProfileResponseDTO updateMyProfile(UUID id, EmployeeProfileUpdateDTO employeeProfileUpdateDTO);
}
