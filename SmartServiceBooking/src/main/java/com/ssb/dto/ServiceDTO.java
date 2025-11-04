package com.ssb.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceDTO {
    private Long id;

    @NotBlank
    private String serviceName;

    private String description;

    @NotNull
    private Double price;
}
