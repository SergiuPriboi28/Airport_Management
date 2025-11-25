package com.example.airportManager.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "aircraft", indexes = {
        @Index(name = "idx_aircraft_tail", columnList = "tailNumber")
})
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"flights"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
//Aircraft(id, tailNumber, model, capacity, seatMapRef, status)
public class Aircraft {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column
    @NotBlank(message = "Tail number is required.")
    private String tailNumber;

    @Column
    @NotBlank(message = "Model is required.")
    private String model;

    @Column
    @NotNull(message = "Capacity must be provided.")
    @Min(value = 0, message = "Capacity has to be a positive number") // We can have ferry flights where only the crew is present
    @Max(value = 860, message = "Capacity cannot exceed 860")
    private int capacity;

    @Column
    @NotBlank(message = "Seat map reference is required.")
    private String seatMapRef;

    @Column
    @NotBlank(message = "Status is required.")
    private String status;

    @OneToMany(mappedBy = "aircraft")
    private Set<Flight> flights;


}
