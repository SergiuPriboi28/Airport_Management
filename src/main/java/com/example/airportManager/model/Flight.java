package com.example.airportManager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.cglib.core.Local;

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
    @NotBlank(message = "Flight code is required.")
    @Size(min = 4, max = 8, message = "Flight code must be between 4 and 8 characters.")
    private String code;

    @Column
//    @NotBl(message = "Departure scheduled time is required.")
    private LocalDateTime departureScheduled;

    @Column
//    @NotNull(message = "Arrival scheduled time is required.")
    private LocalDateTime arrivalScheduled;

    @Column
    private String gate;

    @Enumerated(EnumType.STRING)
    private FlightStatus status;

//    @NotBlank(message = "Aircraft ID is required.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "aircraft_id"
    )
    private Aircraft aircraft;

    @NotNull(message = "Route ID is required.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "route_id"
    )
    private Route route;

    @OneToMany(mappedBy = "flight", fetch = FetchType.LAZY)
    private Set<Booking> bookings;

}
