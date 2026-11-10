package test_data;

import models.booking.BookingBodyModel;
import models.booking.BookingDatesBodyModel;
import models.booking.PartialUpdateBookingBodyModel;
import net.datafaker.Faker;

public class TestData {
    Faker faker = new Faker();

    public static final Integer NON_EXISTING_BOOKING_ID = 2548756;
    public static final String SERVER_ERROR = "Internal Server Error";
    public static final String BAD_REQUEST_ERROR = "Bad Request";
    public static final String NOT_FOUND_ERROR = "Not Found";
    public static final String FORBIDDEN_ERROR = "Forbidden";
    public static final String BAD_CREDENTIALS_ERROR = "Bad credentials";

    //AUTH
    public static final String AUTH_USERNAME = "admin";
    public static final String AUTH_PASSWORD = "password123";
    public static final String AUTH_BAD_CREDENTIALS = "Bad credentials";



    public BookingBodyModel bookingBodyModel() {
        return new BookingBodyModel(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.number().numberBetween(15000, 30000),
                faker.bool().bool(),
                new BookingDatesBodyModel("2026-11-15", "2026-11-20"),
                faker.options().option("Breakfast", "Early checkin")
        );
    }

    public BookingBodyModel requiredFieldsBookingBodyModel() {
        return new BookingBodyModel(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.number().numberBetween(15000, 30000),
                faker.bool().bool(),
                new BookingDatesBodyModel("2026-11-15", "2026-11-20"),
                null
        );
    }

    public BookingBodyModel notAllRequiredFieldsBookingBodyModel() {
        return new BookingBodyModel(
                null,
                faker.name().lastName(),
                faker.number().numberBetween(15000, 30000),
                faker.bool().bool(),
                new BookingDatesBodyModel("2026-11-15", "2026-11-20"),
                null
        );
    }

    public PartialUpdateBookingBodyModel partialBookingBodyModel() {
        return new PartialUpdateBookingBodyModel(
                faker.name().firstName(),
                faker.name().lastName()
        );
    }

}
