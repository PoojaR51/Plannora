package com.plannora.package_service.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PackageRequestDTO {

    @NotBlank(message = "Package name is required")
    private String packageName;

    @NotNull(message = "Duration is required")
    @Min(value = 1, message = "Duration must be at least 1 day")
    private Integer durationDays;

    @NotNull(message = "Price is required")
    @Min(value = 1, message = "Price must be greater than zero")
    private Double price;

    @NotBlank(message = "Package details are required")
    private String details;

    @NotNull(message = "Destination ID is required")
    private Long destinationId;
}
