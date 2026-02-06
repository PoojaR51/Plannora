package com.plannora.booking_service.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingRequestDTO {

    @NotNull(message = "Package ID is required")
    private Long packageId;

    @NotNull(message = "Number of travelers is required")
    @Min(value = 1, message = "At least one traveler is required")
    private Integer travelersCount;

    // ✅ ADDED: user-selected travel date
    @NotNull(message = "Travel date is required")
    private LocalDate travelDate;
}
