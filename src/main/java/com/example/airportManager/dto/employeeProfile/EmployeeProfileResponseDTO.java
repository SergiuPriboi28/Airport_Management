package com.example.airportManager.dto.employeeProfile;

import java.time.LocalDate;

public record EmployeeProfileResponseDTO(
        String position,
        String department,
        String grade,
        LocalDate hireDate
) {
}
