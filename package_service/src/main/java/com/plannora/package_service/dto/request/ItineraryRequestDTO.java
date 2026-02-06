package com.plannora.package_service.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ItineraryRequestDTO {

    @NotNull(message = "Day number is required")
    @Min(value = 1, message = "Day number must be at least 1")
    private Integer dayNo;

    @NotBlank(message = "Activity is required")
    private String activity;

    @NotBlank(message = "Description is required")
    private String description;
}
