package com.example.airportManager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "airport", indexes = {
        @Index(name = "idx_airport_iata", columnList = "iata"),
        @Index(name = "idx_airport_icao", columnList = "icao")
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"originRoutes", "destRoutes"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
//Airport(id, iata, icao, name, city, country, timezone)
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column
    @NotBlank(message = "IATA code is required.")
    @Size(min = 3, max = 3, message = "IATA code must be 3 characters long.")
    private String iata;

    @Column
    @NotBlank(message = "ICAO code is required")
    @Size(min = 4, max = 4, message = "ICAO code must be 4 characters long.")
    private String icao;

    @Column
    @NotBlank(message = "Airport name is required.")
    @Size(max = 25)
    private String name;

    @Column
    @NotBlank(message = "City is required.")
    private String city;

    @Column
    @NotBlank(message = "Country is required.")
    private String country;

    @Column
    @NotBlank(message = "Timezone is required.")
    private String timezone;

    @OneToMany(mappedBy = "originAirport", fetch = FetchType.LAZY)
    private Set<Route> originRoutes;

    @OneToMany(mappedBy = "destAirport", fetch = FetchType.LAZY)
    private Set<Route> destRoutes;


}
