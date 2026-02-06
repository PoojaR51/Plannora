package com.plannora.booking_service.controller;

import com.plannora.booking_service.dto.request.BookingRequestDTO;
import com.plannora.booking_service.service.BookingService;
import com.plannora.booking_service.utils.BaseResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
@Validated
@Slf4j
@CrossOrigin(origins = "http://localhost:5173") // ✅ frontend port
public class BookingController {

    private final BookingService bookingService;

    /* ===================== CREATE BOOKING ===================== */
    // CUSTOMER creates booking → status = PENDING

    @PostMapping
    @PreAuthorize("hasAuthority('CUSTOMER')")
    @Operation(summary = "Create booking (Customer)")
    public ResponseEntity<BaseResponse> createBooking(
            @RequestBody @Valid BookingRequestDTO dto) {

        log.info(
                "Creating booking for packageId={}, travelersCount={}, travelDate={}",
                dto.getPackageId(),
                dto.getTravelersCount(),
                dto.getTravelDate()
        );

        BaseResponse response = bookingService.createBooking(dto);

        return ResponseEntity
                .status(response.getStatus().getStatusCode())
                .body(response);
    }

    /* ===================== GET MY BOOKINGS ===================== */
    // CUSTOMER views own bookings
    // 🔥 FRONTEND CALLS /bookings/my

    @GetMapping("/my")
    @PreAuthorize("hasAuthority('CUSTOMER')")
    @Operation(summary = "Get my bookings (Customer)")
    public ResponseEntity<BaseResponse> getMyBookings() {

        log.info("Fetching bookings for logged-in customer");

        BaseResponse response = bookingService.getMyBookings();

        return ResponseEntity
                .status(response.getStatus().getStatusCode())
                .body(response);
    }

    /* ===================== GET ALL BOOKINGS ===================== */
    // ADMIN views all bookings

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Get all bookings (Admin)")
    public ResponseEntity<BaseResponse> getAllBookings() {

        log.info("Fetching all bookings (admin)");

        BaseResponse response = bookingService.getAllBookings();

        return ResponseEntity
                .status(response.getStatus().getStatusCode())
                .body(response);
    }

    /* ===================== CONFIRM BOOKING ===================== */
    // ADMIN confirms booking after payment

    @PutMapping("/{bookingId}/confirm")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Confirm booking (Admin)")
    public ResponseEntity<BaseResponse> confirmBooking(
            @PathVariable Long bookingId) {

        log.info("Confirming bookingId={}", bookingId);

        BaseResponse response =
                bookingService.confirmBooking(bookingId);

        return ResponseEntity
                .status(response.getStatus().getStatusCode())
                .body(response);
    }

    /* ===================== REJECT BOOKING ===================== */
// ADMIN rejects booking

    @PutMapping("/{bookingId}/reject")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "Reject booking (Admin)")
    public ResponseEntity<BaseResponse> rejectBooking(
            @PathVariable Long bookingId) {

        log.info("Rejecting bookingId={}", bookingId);

        BaseResponse response =
                bookingService.rejectBooking(bookingId);

        return ResponseEntity
                .status(response.getStatus().getStatusCode())
                .body(response);
    }



}
