package com.plannora.booking_service.dto.response;

import lombok.Data;
import java.time.LocalDate;

@Data
public class BookingResponseDTO {

    private Long bookingId;

    // ✅ ADD field (setter already used in service)
    private Long userId;

    private Long packageId;

    private Integer travelersCount;

    // ✅ ADD field (required by service & frontend)
    private LocalDate travelDate;

    private String status;   // PENDING, CONFIRMED, REJECTED

    private LocalDate bookingDate;

    // ✅ COMPLETE existing method (DO NOT REMOVE)
    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
