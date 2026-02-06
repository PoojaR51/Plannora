package com.plannora.package_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DestinationRequestDTO {

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "Place name is required")
    private String placeName;

    @NotBlank(message = "Description is required")
    private String description;
}
