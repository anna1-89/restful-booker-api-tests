package tests.booking;

import io.qameta.allure.Story;
import models.booking.BookingBodyModel;
import models.booking.SuccessfulBookingResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static test_data.TestData.SERVER_ERROR;

@Story("Функционал создания нового бронирования")
public class CreateBookingTests extends TestBase {

    @Test
    @DisplayName("[API] Создание нового бронирования с заполнением всех полей")
    public void createBookingTest() {
        BookingBodyModel bookingBody = testData.bookingBodyModel();
        SuccessfulBookingResponseModel bookingResponse = api.booking.createBooking(bookingBody);

        step("Проверка ответа (200, соответствие данных)", () -> {
            assertThat(bookingResponse.bookingid()).isGreaterThan(0);
            assertThat(bookingResponse.booking().firstname()).isEqualTo(bookingBody.firstname());
            assertThat(bookingResponse.booking().lastname()).isEqualTo(bookingBody.lastname());
            assertThat(bookingResponse.booking().totalprice()).isEqualTo(bookingBody.totalprice());
            assertThat(bookingResponse.booking().depositpaid()).isEqualTo(bookingBody.depositpaid());
            assertThat(bookingResponse.booking().bookingdates()).isEqualTo(bookingBody.bookingdates());
            assertThat(bookingResponse.booking().additionalneeds()).isEqualTo(bookingBody.additionalneeds());
        });
    }

    @Test
    @DisplayName("[API] Создание нового бронирования с заполнением только обязательных полей")
    public void createBookingWithRequiredDataTest() {
        BookingBodyModel bookingBody = testData.requiredFieldsBookingBodyModel();
        SuccessfulBookingResponseModel bookingResponse = api.booking.createBookingWithRequiredFields(bookingBody);

        step("Проверка ответа (200, соответствие данных)", () -> {
            assertThat(bookingResponse.bookingid()).isGreaterThan(0);
            assertThat(bookingResponse.booking().firstname()).isEqualTo(bookingBody.firstname());
            assertThat(bookingResponse.booking().lastname()).isEqualTo(bookingBody.lastname());
            assertThat(bookingResponse.booking().totalprice()).isEqualTo(bookingBody.totalprice());
            assertThat(bookingResponse.booking().depositpaid()).isEqualTo(bookingBody.depositpaid());
            assertThat(bookingResponse.booking().bookingdates()).isEqualTo(bookingBody.bookingdates());
            assertThat(bookingResponse.booking().additionalneeds()).isEqualTo(bookingBody.additionalneeds());
        });
    }

    @Test
    @DisplayName("[API] Попытка создание нового бронирования без заполнением обязательных полей")
    public void createBookingWithoutRequiredDataTest() {
        BookingBodyModel bookingBody = testData.notAllRequiredFieldsBookingBodyModel();
        String bookingResponse = api.booking.createBookingWithoutRequiredFields(bookingBody);

        step("Проверка ответа (500, текст с ошибкой)", () ->
            assertThat(bookingResponse).contains(SERVER_ERROR)
        );
    }
}
