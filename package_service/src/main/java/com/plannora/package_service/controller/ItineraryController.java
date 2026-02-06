package com.plannora.package_service.controller;

import com.plannora.package_service.dto.request.ItineraryRequestDTO;
import com.plannora.package_service.service.ItineraryService;
import com.plannora.package_service.utils.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/itineraries")
@RequiredArgsConstructor

public class ItineraryController {

    private final ItineraryService itineraryService;

    /* ===================== ADD ITINERARY ===================== */

    @PostMapping("/{packageId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Add itinerary to package (Admin only)")
    public ResponseEntity<BaseResponse> addItinerary(
            @PathVariable Long packageId,
            @RequestBody @Valid ItineraryRequestDTO dto) {

        BaseResponse response =
                itineraryService.addItinerary(packageId, dto);

        return ResponseEntity
                .status(response.getStatus().getStatusCode())
                .body(response);
    }

    /* ===================== GET ITINERARY ===================== */

    @GetMapping("/{packageId}")
    @Operation(summary = "Get itinerary by package ID")
    public ResponseEntity<BaseResponse> getItineraryByPackageId(
            @PathVariable Long packageId) {

        BaseResponse response =
                itineraryService.getByPackageId(packageId);

        return ResponseEntity
                .status(response.getStatus().getStatusCode())
                .body(response);
    }
}
