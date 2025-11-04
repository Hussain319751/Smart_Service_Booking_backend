package com.ssb.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "service_id")
    @JsonIgnore // ✅ Prevent recursive serialization
    private ServiceEntity service;

    @NotNull
    private LocalDate bookingDate;

    private String status; // PENDING, CONFIRMED, COMPLETED
    
    private String location;
}
