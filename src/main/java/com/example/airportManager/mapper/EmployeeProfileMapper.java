package com.example.airportManager.mapper;

import com.example.airportManager.dto.employeeProfile.EmployeeProfileDTO;
import com.example.airportManager.dto.employeeProfile.EmployeeProfileResponseDTO;
import com.example.airportManager.dto.employeeProfile.EmployeeProfileUpdateDTO;
import com.example.airportManager.model.EmployeeProfile;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EmployeeProfileMapper {
    EmployeeProfile toEntity(EmployeeProfileDTO employeeProfileDTO);
    EmployeeProfileResponseDTO toResponse(EmployeeProfile employeeProfile);
    void updateEmployeeProfileFromDTO(EmployeeProfileUpdateDTO employeeProfileUpdateDTO, @MappingTarget EmployeeProfile employeeProfile);
}
