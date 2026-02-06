package com.plannora.package_service.dto.response;

import lombok.Data;

@Data
public class ItineraryResponseDTO {

    private Long id;
    private Integer dayNo;
    private String activity;
    private String description;
}
