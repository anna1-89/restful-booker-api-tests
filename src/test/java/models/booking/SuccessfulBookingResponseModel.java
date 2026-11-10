package models.booking;

public record SuccessfulBookingResponseModel(
        Integer bookingid,
        BookingBodyModel booking) {}
