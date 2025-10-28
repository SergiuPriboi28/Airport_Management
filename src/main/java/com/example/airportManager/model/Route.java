package com.example.airportManager.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "route",
        uniqueConstraints = {
        @UniqueConstraint(name = "route_airports",
                columnNames = {"origin_airport_id", "dest_airport_id"})
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"flights"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
//Route(id, origin_airport_id, dest_airport_id, distanceNm, stdDurationMin)
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long ID;

    @Column
    private int distanceNm;

    @Column
    private int stdDurationMin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "origin_airport_id")
    private Airport originAirport;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dest_airport_id")
    private Airport destAirport;

    @OneToMany(mappedBy = "route", fetch = FetchType.LAZY)
    private Set<Flight> flights;
}
