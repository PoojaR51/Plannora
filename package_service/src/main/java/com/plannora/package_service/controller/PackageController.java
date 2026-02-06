package com.plannora.package_service.controller;

import com.plannora.package_service.dto.request.PackageRequestDTO;
import com.plannora.package_service.service.PackageService;
import com.plannora.package_service.utils.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/packages")
@RequiredArgsConstructor

public class PackageController {

    private final PackageService packageService;

    /* ===================== CREATE PACKAGE ===================== */

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Create travel package (Admin only)")
    public ResponseEntity<BaseResponse> createPackage(
            @RequestBody @Valid PackageRequestDTO dto) {

        BaseResponse response = packageService.create(dto);
        return ResponseEntity
                .status(response.getStatus().getStatusCode())
                .body(response);
    }

    /* ===================== GET ALL PACKAGES ===================== */

    @GetMapping
    @Operation(summary = "Get all packages")
    public ResponseEntity<BaseResponse> getAllPackages() {

        BaseResponse response = packageService.getAll();
        return ResponseEntity
                .status(response.getStatus().getStatusCode())
                .body(response);
    }

    /* ===================== GET PACKAGE BY ID ===================== */

    @GetMapping("/{id}")
    @Operation(summary = "Get package by ID")
    public ResponseEntity<BaseResponse> getPackageById(
            @PathVariable Long id) {

        BaseResponse response = packageService.getById(id);
        return ResponseEntity
                .status(response.getStatus().getStatusCode())
                .body(response);
    }
}
