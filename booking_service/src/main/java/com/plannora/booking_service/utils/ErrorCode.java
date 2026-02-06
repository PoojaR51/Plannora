package com.plannora.booking_service.utils;

public final class ErrorCode {

    /* ===================== COMMON ===================== */

    public static final String NO_DATA_FOUND =
            "No data found for the given request.";

    public static final String ALREADY_EXISTS =
            "Data already exists.";

    public static final String INVALID_REQUEST =
            "Invalid request data.";

    public static final String INTERNAL_SERVER_ERROR =
            "Internal server error occurred.";

    /* ===================== DESTINATION ===================== */

    public static final String DESTINATION_NOT_FOUND =
            "Destination not found.";

    public static final String DESTINATION_ALREADY_EXISTS =
            "Destination already exists.";

    /* ===================== PACKAGE ===================== */

    public static final String PACKAGE_NOT_FOUND =
            "Package not found.";

    public static final String PACKAGE_ALREADY_EXISTS =
            "Package already exists.";

    /* ===================== ITINERARY ===================== */

    public static final String ITINERARY_NOT_FOUND =
            "Itinerary not found.";

    public static final String ITINERARY_ALREADY_EXISTS =
            "Itinerary for this day already exists.";

    /* ===================== SECURITY ===================== */

    public static final String UNAUTHORIZED =
            "Unauthorized access.";

    public static final String FORBIDDEN =
            "Access denied.";

    /* ===================== CONSTRUCTOR ===================== */

    private ErrorCode() {
        // prevent instantiation
    }
}
