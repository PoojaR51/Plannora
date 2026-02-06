package com.plannora.booking_service.service.impl;

import com.plannora.booking_service.dto.request.BookingRequestDTO;
import com.plannora.booking_service.dto.response.BookingResponseDTO;
import com.plannora.booking_service.entity.Booking;
import com.plannora.booking_service.entity.BookingStatus;
import com.plannora.booking_service.repository.BookingRepo;
import com.plannora.booking_service.security.JwtUtils;
import com.plannora.booking_service.security.UserPrincipal;
import com.plannora.booking_service.service.BookingService;
import com.plannora.booking_service.utils.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.plannora.booking_service.utils.GsUtils.error;

@Service
@RequiredArgsConstructor
@Transactional
public class BookingServiceImpl implements BookingService {

    private final BookingRepo bookingRepo;
    private final JwtUtils jwtUtils;

    /* ===================== CREATE BOOKING ===================== */

    @Override
    public BaseResponse createBooking(BookingRequestDTO dto) {

        UserPrincipal principal = jwtUtils.getCurrentPrincipal();

        if (principal == null) {
            return error(
                    HttpStatus.UNAUTHORIZED,
                    ErrorCode.UNAUTHORIZED,
                    Errors.ERROR_TYPE.SECURITY
            );
        }

        Booking booking = new Booking(
                principal.getUserId(),
                dto.getPackageId(),
                dto.getTravelersCount(),
                dto.getTravelDate(),     // ✅ ADDED
                BookingStatus.PENDING
        );

        bookingRepo.save(booking);

        return GsUtils.getBaseResponse(
                HttpStatus.CREATED,
                mapToResponseDTO(booking)
        );
    }

    /* ===================== GET MY BOOKINGS ===================== */

    @Override
    public BaseResponse getMyBookings() {

        UserPrincipal principal = jwtUtils.getCurrentPrincipal();

        if (principal == null) {
            return error(
                    HttpStatus.UNAUTHORIZED,
                    ErrorCode.UNAUTHORIZED,
                    Errors.ERROR_TYPE.SECURITY
            );
        }

        List<BookingResponseDTO> bookings =
                bookingRepo
                        .findByUserIdOrderByBookingDateDesc(principal.getUserId())
                        .stream()
                        .map(this::mapToResponseDTO)
                        .toList();

        return GsUtils.getBaseResponse(HttpStatus.OK, bookings);
    }

    /* ===================== GET ALL BOOKINGS (ADMIN) ===================== */

    @Override
    public BaseResponse getAllBookings() {

        List<BookingResponseDTO> bookings =
                bookingRepo.findAll()
                        .stream()
                        .map(this::mapToResponseDTO)
                        .toList();

        return GsUtils.getBaseResponse(HttpStatus.OK, bookings);
    }

    /* ===================== CONFIRM BOOKING ===================== */

    @Override
    public BaseResponse confirmBooking(Long bookingId) {

        Optional<Booking> optionalBooking =
                bookingRepo.findById(bookingId);

        if (optionalBooking.isEmpty()) {
            return error(
                    HttpStatus.NOT_FOUND,
                    ErrorCode.NO_DATA_FOUND,
                    Errors.ERROR_TYPE.BOOKING
            );
        }

        Booking booking = optionalBooking.get();

        if (booking.getStatus() == BookingStatus.CONFIRMED) {
            return error(
                    HttpStatus.CONFLICT,
                    "Booking already confirmed",
                    Errors.ERROR_TYPE.BOOKING
            );
        }

        booking.setStatus(BookingStatus.CONFIRMED);

        return GsUtils.getBaseResponse(
                HttpStatus.OK,
                mapToResponseDTO(booking)
        );
    }

    /* ===================== REJECT BOOKING ===================== */

    @Override
    public BaseResponse rejectBooking(Long bookingId) {

        Optional<Booking> optionalBooking = bookingRepo.findById(bookingId);

        if (optionalBooking.isEmpty()) {
            return error(
                    HttpStatus.NOT_FOUND,
                    ErrorCode.NO_DATA_FOUND,
                    Errors.ERROR_TYPE.BOOKING
            );
        }

        Booking booking = optionalBooking.get();

        if (booking.getStatus() == BookingStatus.CONFIRMED) {
            return error(
                    HttpStatus.CONFLICT,
                    "Confirmed booking cannot be rejected",
                    Errors.ERROR_TYPE.BOOKING
            );
        }

        booking.setStatus(BookingStatus.REJECTED);

        bookingRepo.save(booking);

        return GsUtils.getBaseResponse(
                HttpStatus.OK,
                mapToResponseDTO(booking)
        );
    }

    /* ===================== MAPPER ===================== */

    private BookingResponseDTO mapToResponseDTO(Booking booking) {

        BookingResponseDTO dto = new BookingResponseDTO();

        dto.setBookingId(booking.getId());
        dto.setUserId(booking.getUserId());
        dto.setPackageId(booking.getPackageId());
        dto.setTravelersCount(booking.getTravelersCount());
        dto.setTravelDate(booking.getTravelDate());   // ✅ ADDED
        dto.setStatus(booking.getStatus().name());
        dto.setBookingDate(booking.getBookingDate());

        return dto;
    }
}
