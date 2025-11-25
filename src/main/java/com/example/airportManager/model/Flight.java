package com.example.airportManager.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "flight", indexes = {
        @Index(name = "idx_flight_code", columnList = "code")
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"bookings"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column
    private String code;

    @Column
    private String departureScheduled;

    @Column
    private String arrivalScheduled;

    @Column
    private String gate;

    @Enumerated(EnumType.STRING)
    private FlightStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "aircraft_id"
    )
    private Aircraft aircraft;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "route_id"
    )
    private Route route;

    @OneToMany(mappedBy = "flight", fetch = FetchType.LAZY)
    private Set<Booking> bookings;

}
