package com.plannora.booking_service.repository;

import com.plannora.booking_service.entity.Booking;
import com.plannora.booking_service.entity.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepo extends JpaRepository<Booking, Long> {

    // CUSTOMER: view own bookings
    List<Booking> findByUserIdOrderByBookingDateDesc(Long userId);

    // ADMIN: filter bookings by status
    List<Booking> findByStatus(BookingStatus status);

    // ADMIN: filter bookings for a package
    List<Booking> findByPackageId(Long packageId);
}
