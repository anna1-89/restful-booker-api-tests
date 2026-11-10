package models.booking;

public record BookingBodyModel(
        String firstname,
        String lastname,
        Integer totalprice,
        Boolean depositpaid,
        BookingDatesBodyModel bookingdates,
        String additionalneeds) {}
