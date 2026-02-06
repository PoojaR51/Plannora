package com.plannora.package_service.dto.response;

import lombok.Data;

@Data
public class PackageResponseDTO {

    private Long id;
    private String packageName;
    private Integer durationDays;
    private Double price;
    private String details;

    // Destination summary
    private Long destinationId;
    private String destinationCity;
    private String destinationPlaceName;
}
