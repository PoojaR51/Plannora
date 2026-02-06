package com.plannora.package_service.controller;

import com.plannora.package_service.dto.request.DestinationRequestDTO;
import com.plannora.package_service.service.DestinationService;
import com.plannora.package_service.utils.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/destinations")
@RequiredArgsConstructor

public class DestinationController {

    private final DestinationService destinationService;

    /* ===================== CREATE DESTINATION ===================== */

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Create destination (Admin only)")
    public ResponseEntity<BaseResponse> createDestination(
            @RequestBody @Valid DestinationRequestDTO dto) {

        BaseResponse response = destinationService.create(dto);
        return ResponseEntity
                .status(response.getStatus().getStatusCode())
                .body(response);
    }

    /* ===================== GET ALL DESTINATIONS ===================== */

    @GetMapping
    @Operation(summary = "Get all destinations")
    public ResponseEntity<BaseResponse> getAllDestinations() {

        BaseResponse response = destinationService.getAll();
        return ResponseEntity
                .status(response.getStatus().getStatusCode())
                .body(response);
    }
}
