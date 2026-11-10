package tests.booking;

import io.qameta.allure.Story;
import models.auth.AuthBodyModel;
import models.booking.BookingBodyModel;
import models.booking.SuccessfulGetUpdateBookingResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static test_data.TestData.*;
import static test_data.TestData.AUTH_PASSWORD;

@Story("Функционал удаления бронирования")
public class DeleteBookingTests extends TestBase {

    @Test
    @DisplayName("[API] Удаление существующего бронирования бронировании")
    public void deleteBookingTest() {
        BookingBodyModel bookingBody = testData.bookingBodyModel();
        Integer bookingId = api.booking.createBookingAndGetBookindId(bookingBody);

        AuthBodyModel authData = new AuthBodyModel(AUTH_USERNAME, AUTH_PASSWORD);
        String authToken = api.auth.authAndGetToken(authData);

        api.booking.deleteBooking(authToken, bookingId);

        String bookingResponse = api.booking.getNonExistingBooking(bookingId);

        step("Проверка, что бронирование удаление и не находится запросом GET)", () ->
                assertThat(bookingResponse).contains(NOT_FOUND_ERROR)
        );
    }

    @Test
    @DisplayName("[API] Удаление несуществующего бронировании")
    public void deleteNonExistingBookingTest() {
        AuthBodyModel authData = new AuthBodyModel(AUTH_USERNAME, AUTH_PASSWORD);
        String authToken = api.auth.authAndGetToken(authData);

        String bookingResponse = api.booking.deleteNonExistingBooking(authToken, NON_EXISTING_BOOKING_ID);

        step("Проверка ответа (405, текст с ошибкой)", () ->
            assertThat(bookingResponse).contains(METHOD_NOT_ALLOWED_ERROR)
            );
    }

    @Test
    @DisplayName("[API] Удаление бронировании неавторизованным пользователем")
    public void deleteBookingWithoutAuthorizationTest() {
        BookingBodyModel bookingBody = testData.bookingBodyModel();
        Integer bookingId = api.booking.createBookingAndGetBookindId(bookingBody);

        String bookingResponse = api.booking.deleteBookingWithoutAuthorization(bookingId);

        step("Проверка ответа (403, текст с ошибкой)", () ->
                assertThat(bookingResponse).contains(FORBIDDEN_ERROR)
        );
    }


}
