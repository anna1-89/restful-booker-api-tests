package tests.booking;

import io.qameta.allure.Story;
import models.booking.BookingBodyModel;
import models.booking.SuccessfulGetUpdateBookingResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static test_data.TestData.NON_EXISTING_BOOKING_ID;
import static test_data.TestData.NOT_FOUND_ERROR;

@Story("Функционал получения информации по бронированию")
public class GetBookingTests extends TestBase {

    @Test
    @DisplayName("[API] Получение информации о существующем бронировании")
    public void getBookingTest() {
        BookingBodyModel bookingBody = testData.bookingBodyModel();
        Integer bookingId = api.booking.createBookingAndGetBookindId(bookingBody);

        SuccessfulGetUpdateBookingResponseModel bookingResponse = api.booking.getBooking(bookingId);

        step("Проверка ответа (200, соответствие данных)", () -> {
            assertThat(bookingResponse.firstname()).isEqualTo(bookingBody.firstname());
            assertThat(bookingResponse.lastname()).isEqualTo(bookingBody.lastname());
            assertThat(bookingResponse.totalprice()).isEqualTo(bookingBody.totalprice());
            assertThat(bookingResponse.depositpaid()).isEqualTo(bookingBody.depositpaid());
            assertThat(bookingResponse.bookingdates()).isEqualTo(bookingBody.bookingdates());
            assertThat(bookingResponse.additionalneeds()).isEqualTo(bookingBody.additionalneeds());
        });
    }

    @Test
    @DisplayName("[API] Получение информации о несуществующем бронировании")
    public void getNonExistingBooking() {
        String bookingResponse = api.booking.getNonExistingBooking(NON_EXISTING_BOOKING_ID);

        step("Проверка ответа (404, текст с ошибкой)", () ->
            assertThat(bookingResponse).contains(NOT_FOUND_ERROR)
            );
    }


}
