package models.booking;

public record SuccessfulGetUpdateBookingResponseModel(
        String firstname,
        String lastname,
        Integer totalprice,
        Boolean depositpaid,
        BookingDatesBodyModel bookingdates,
        String additionalneeds) {}
