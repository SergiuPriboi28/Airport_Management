package com.example.airportManager.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
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
    private Long ID;

    @Column
    private String iata;

    @Column
    private String icao;

    @Column
    private String name;

    @Column
    private String city;

    @Column
    private String country;

    @Column
    private String timezone;

    @OneToMany(mappedBy = "originAirport", fetch = FetchType.LAZY)
    private Set<Route> originRoutes;

    @OneToMany(mappedBy = "destAirport", fetch = FetchType.LAZY)
    private Set<Route> destRoutes;


}
