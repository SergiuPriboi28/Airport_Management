package com.example.airportManager.dto.airport;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AirportCreateDTO(
        @NotBlank(message = "IATA code is required.")
        @Size(min = 3, max = 3, message = "IATA code must be 3 characters long.")
        String iata,
        @NotBlank(message = "ICAO code is required")
        @Size(min = 4, max = 4, message = "ICAO code must be 4 characters long.")
        String icao,
        @NotBlank(message = "Aiport name is required.")
        @Size(max = 25)
        String name,
        @NotBlank(message = "City is required.") String city,
        @NotBlank(message = "Country is required.") String country,
        @NotBlank(message = "Timezone is required.") String timezone
) {
}
