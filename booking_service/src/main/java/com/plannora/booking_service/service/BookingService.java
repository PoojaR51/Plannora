package com.plannora.booking_service.service;

import com.plannora.booking_service.dto.request.BookingRequestDTO;
import com.plannora.booking_service.utils.BaseResponse;

public interface BookingService {

    // CUSTOMER → create booking (PENDING)
    BaseResponse createBooking(BookingRequestDTO dto);

    // CUSTOMER → view own bookings
    BaseResponse getMyBookings();

    // ADMIN → view all bookings
    BaseResponse getAllBookings();

    // INTERNAL / ADMIN → confirm booking after payment
    BaseResponse confirmBooking(Long bookingId);

    BaseResponse rejectBooking(Long bookingId);
}
