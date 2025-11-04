package com.ssb.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingDTO {
    private Long id;

    @NotNull
    private Long userId;

    @NotNull
    private Long serviceId;

    @NotNull
    private LocalDate bookingDate;

    private String status;
    
    private String location;
}
