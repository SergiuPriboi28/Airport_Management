package com.example.airportManager.service.impl;

import com.example.airportManager.dto.employeeProfile.EmployeeProfileResponseDTO;
import com.example.airportManager.dto.employeeProfile.EmployeeProfileUpdateDTO;
import com.example.airportManager.mapper.EmployeeProfileMapper;
import com.example.airportManager.model.EmployeeProfile;
import com.example.airportManager.repository.EmployeeProfileRepository;
import com.example.airportManager.service.EmployeeProfileService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeProfileServiceImpl implements EmployeeProfileService {

    private final EmployeeProfileRepository employeeProfileRepository;
    private final EmployeeProfileMapper employeeProfileMapper;

    @Override
    @Transactional
    public EmployeeProfile getMyProfile(UUID id) {
        EmployeeProfile employeeProfile = employeeProfileRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("No Employee Profile found!"));
        return employeeProfile;
    }

    @Override
    public EmployeeProfileResponseDTO updateMyProfile(UUID id, EmployeeProfileUpdateDTO employeeProfileUpdateDTO) {
        EmployeeProfile oldEmployeeProfile = employeeProfileRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("No Employee Profile found!"));
        employeeProfileMapper.updateEmployeeProfileFromDTO(employeeProfileUpdateDTO, oldEmployeeProfile);
        EmployeeProfile updatedEmployee = employeeProfileRepository.save(oldEmployeeProfile);
        return employeeProfileMapper.toResponse(updatedEmployee);
    }
}
