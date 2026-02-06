package com.plannora.booking_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
        name = "bookings",
        indexes = {
                @Index(name = "idx_booking_user", columnList = "user_id"),
                @Index(name = "idx_booking_package", columnList = "package_id"),
                @Index(name = "idx_booking_status", columnList = "status")
        }
)
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* ===================== RELATIONS (IDS ONLY) ===================== */

    @Column(name = "user_id", nullable = false)
    private Long userId;     // from JWT (CUSTOMER)

    @Column(name = "package_id", nullable = false)
    private Long packageId;  // from Package Service

    /* ===================== BOOKING DETAILS ===================== */

    @Column(name = "travelers_count", nullable = false)
    private Integer travelersCount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20, nullable = false)
    private BookingStatus status;

    @CreationTimestamp
    @Column(name = "booking_date", nullable = false, updatable = false)
    private LocalDate bookingDate;

    @Column(name = "travel_date", nullable = false)
    private LocalDate travelDate;


    /* ===================== CONSTRUCTOR ===================== */

    public Booking(
            Long userId,
            Long packageId,
            Integer travelersCount,
            LocalDate travelDate,
            BookingStatus status
    ) {
        this.userId = userId;
        this.packageId = packageId;
        this.travelersCount = travelersCount;
        this.travelDate = travelDate;
        this.status = status;
    }
    }