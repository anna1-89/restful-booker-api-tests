package tests.booking;

import io.qameta.allure.Story;
import models.auth.AuthBodyModel;
import models.booking.BookingBodyModel;
import models.booking.PartialUpdateBookingBodyModel;
import models.booking.SuccessfulBookingResponseModel;
import models.booking.SuccessfulGetUpdateBookingResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static test_data.TestData.*;

@Story("Функционал обновления бронирования")
public class UpdateBookingTests extends TestBase {

    @Test
    @DisplayName("[API] Обновления всех полей бронирования")
    public void updatePutBookingTest() {
        BookingBodyModel bookingBody = testData.bookingBodyModel();
        SuccessfulBookingResponseModel bookingResponse = api.booking.createBooking(bookingBody);
        Integer bookingId = bookingResponse.bookingid();

        AuthBodyModel authData = new AuthBodyModel(AUTH_USERNAME, AUTH_PASSWORD);
        String authToken = api.auth.authAndGetToken(authData);

        BookingBodyModel updateBookingBody = testData.bookingBodyModel();
        SuccessfulGetUpdateBookingResponseModel updatedBookingResponse = api.booking.updateBooking(authToken, bookingId, updateBookingBody);

        step("Проверка ответа (200, соответствие данных)", () -> {
            assertThat(updatedBookingResponse.firstname()).isEqualTo(updateBookingBody.firstname());
            assertThat(updatedBookingResponse.lastname()).isEqualTo(updateBookingBody.lastname());
            assertThat(updatedBookingResponse.totalprice()).isEqualTo(updateBookingBody.totalprice());
            assertThat(updatedBookingResponse.depositpaid()).isEqualTo(updateBookingBody.depositpaid());
            assertThat(updatedBookingResponse.bookingdates()).isEqualTo(updateBookingBody.bookingdates());
            assertThat(updatedBookingResponse.additionalneeds()).isEqualTo(updateBookingBody.additionalneeds());
        });
    }

    @Test
    @DisplayName("[API] Попытка обновления бронирования неавторизированным пользователем")
    public void updateBookingWithoutAuthorizationTest() {
        BookingBodyModel bookingBody = testData.bookingBodyModel();
        SuccessfulBookingResponseModel bookingResponse = api.booking.createBooking(bookingBody);
        Integer bookingId = bookingResponse.bookingid();

        BookingBodyModel updateBookingBody = testData.bookingBodyModel();
        String updatedBookingResponse = api.booking.updateBookingWithoutAuthorization(bookingId, updateBookingBody);

        step("Проверка ответа (403, текст с ошибкой)", () ->
                assertThat(updatedBookingResponse).contains(FORBIDDEN_ERROR)
        );
    }

    @Test
    @DisplayName("[API] Попытка обновления бронирования без заполнением всех полей")
    public void createBookingWithoutRequiredDataTest() {
        BookingBodyModel bookingBody = testData.bookingBodyModel();
        SuccessfulBookingResponseModel bookingResponse = api.booking.createBooking(bookingBody);
        Integer bookingId = bookingResponse.bookingid();

        AuthBodyModel authData = new AuthBodyModel(AUTH_USERNAME, AUTH_PASSWORD);
        String authToken = api.auth.authAndGetToken(authData);

        BookingBodyModel updateBookingBody = testData.notAllRequiredFieldsBookingBodyModel();
        String updatedBookingResponse = api.booking.updateBookingWithoutAllFields(authToken, bookingId, updateBookingBody);

        step("Проверка ответа (400, текст с ошибкой)", () ->
            assertThat(updatedBookingResponse).contains(BAD_REQUEST_ERROR)
        );
    }

    @Test
    @DisplayName("[API] Частичное обновления полей бронирования")
    public void partialUpdatePutBookingTest() {
        BookingBodyModel bookingBody = testData.bookingBodyModel();
        SuccessfulBookingResponseModel bookingResponse = api.booking.createBooking(bookingBody);
        Integer bookingId = bookingResponse.bookingid();

        AuthBodyModel authData = new AuthBodyModel(AUTH_USERNAME, AUTH_PASSWORD);
        String authToken = api.auth.authAndGetToken(authData);

        PartialUpdateBookingBodyModel updateBookingBody = testData.partialBookingBodyModel();
        SuccessfulGetUpdateBookingResponseModel updatedBookingResponse = api.booking.partialUpdateBooking(authToken, bookingId, updateBookingBody);

        step("Проверка ответа (200, соответствие данных)", () -> {
            assertThat(updatedBookingResponse.firstname()).isEqualTo(updateBookingBody.firstname());
            assertThat(updatedBookingResponse.lastname()).isEqualTo(updateBookingBody.lastname());
            assertThat(updatedBookingResponse.totalprice()).isEqualTo(bookingResponse.booking().totalprice());
            assertThat(updatedBookingResponse.depositpaid()).isEqualTo(bookingResponse.booking().depositpaid());
            assertThat(updatedBookingResponse.bookingdates()).isEqualTo(bookingResponse.booking().bookingdates());
            assertThat(updatedBookingResponse.additionalneeds()).isEqualTo(bookingResponse.booking().additionalneeds());
        });
    }
}
