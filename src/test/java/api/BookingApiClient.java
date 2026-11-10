package api;

import io.qameta.allure.Step;
import models.booking.BookingBodyModel;
import models.booking.PartialUpdateBookingBodyModel;
import models.booking.SuccessfulBookingResponseModel;
import models.booking.SuccessfulGetUpdateBookingResponseModel;

import static io.restassured.RestAssured.given;
import static specs.BaseSpec.baseRequestSpec;
import static specs.booking.BookingSpec.*;

public class BookingApiClient {

    //CREATE

    @Step("[API] Отправка запроса на создание POST /booking со всеми валидными данными")
    public SuccessfulBookingResponseModel createBooking(BookingBodyModel bookingBody) {
        return given(baseRequestSpec)
                .body(bookingBody)
                .when()
                .post("/booking")
                .then()
                .spec(successfulBookingResponseSpec("schemas/booking/successful_booking_response_schema.json"))
                .extract()
                .as(SuccessfulBookingResponseModel.class);
    }

    @Step("[API] Отправка запроса на создание POST /booking с валидными обязательными данными")
    public SuccessfulBookingResponseModel createBookingWithRequiredFields(BookingBodyModel bookingBody) {
        return given(baseRequestSpec)
                .body(bookingBody)
                .when()
                .post("/booking")
                .then()
                .spec(successfulBookingResponseSpec("schemas/booking/required_fields_successful_booking_response_schema.json"))
                .extract()
                .as(SuccessfulBookingResponseModel.class);
    }

    @Step("[API] Отправка запроса на создание POST /booking без обязательных данных")
    public String createBookingWithoutRequiredFields(BookingBodyModel bookingBody) {
        return given(baseRequestSpec)
                .body(bookingBody)
                .when()
                .post("/booking")
                .then()
                .spec(responseSpec(500))
                .extract()
                .asString();
    }

    @Step("[API] Отправка запроса на создание POST /booking с получение id созданного бронирования")
    public Integer createBookingAndGetBookingId(BookingBodyModel bookingBody) {
        return given(baseRequestSpec)
                .body(bookingBody)
                .when()
                .post("/booking")
                .then()
                .spec(successfulBookingResponseSpec("schemas/booking/successful_booking_response_schema.json"))
                .extract()
                .path("bookingid");
    }

    //GET

    @Step("[API] Отправка запроса на получение информации по бронированию по id GET /booking/{id}/")
    public SuccessfulGetUpdateBookingResponseModel getBooking(Integer bookingId) {
        return given(baseRequestSpec)
                .pathParam("id", bookingId)
                .when()
                .get("/booking/{id}/")
                .then()
                .spec(successfulGetUpdateBookingResponseSpec)
                .extract()
                .as(SuccessfulGetUpdateBookingResponseModel.class);
    }

    @Step("[API] Отправка запроса на получение информации по несуществующему бронированию по id GET /booking/{id}/")
    public String getNonExistingBooking(Integer bookingId) {
        return given(baseRequestSpec)
                .pathParam("id", bookingId)
                .when()
                .get("/booking/{id}/")
                .then()
                .spec(responseSpec(404))
                .extract()
                .asString();
    }

    //PUT
    @Step("[API] Отправка запроса на обновление PUT /booking/{id} с валидными данными")
    public SuccessfulGetUpdateBookingResponseModel updateBooking(String authToken, Integer bookingId, BookingBodyModel bookingBody) {
        return given(baseRequestSpec)
                .cookie("token", authToken)
                .pathParam("id", bookingId)
                .body(bookingBody)
                .when()
                .put("/booking/{id}/")
                .then()
                .spec(successfulGetUpdateBookingResponseSpec)
                .extract()
                .as(SuccessfulGetUpdateBookingResponseModel.class);
    }

    @Step("[API] Отправка запроса на обновление PUT /booking/{id} неавторизованным пользователем")
    public String updateBookingWithoutAuthorization(Integer bookingId, BookingBodyModel bookingBody) {
        return given(baseRequestSpec)
                .pathParam("id", bookingId)
                .body(bookingBody)
                .when()
                .put("/booking/{id}/")
                .then()
                .spec(responseSpec(403))
                .extract()
                .asString();
    }

    @Step("[API] Отправка запроса на обновление PUT /booking/{id} без заполнения всех полей")
    public String updateBookingWithoutAllFields(String authToken, Integer bookingId, BookingBodyModel bookingBody) {
        return given(baseRequestSpec)
                .cookie("token", authToken)
                .pathParam("id", bookingId)
                .body(bookingBody)
                .when()
                .put("/booking/{id}/")
                .then()
                .spec(responseSpec(400))
                .extract()
                .asString();
    }

    //PATCH
    @Step("[API] Отправка запроса на частичное обновление PATCH /booking/{id} с валидными данными")
    public SuccessfulGetUpdateBookingResponseModel partialUpdateBooking(String authToken, Integer bookingId, PartialUpdateBookingBodyModel bookingBody) {
        return given(baseRequestSpec)
                .cookie("token", authToken)
                .pathParam("id", bookingId)
                .body(bookingBody)
                .when()
                .patch("/booking/{id}/")
                .then()
                .spec(successfulGetUpdateBookingResponseSpec)
                .extract()
                .as(SuccessfulGetUpdateBookingResponseModel.class);
    }

    //DELETE
    @Step("[API] Отправка запроса на удаление существующего бронирования DELETE /booking/{id}")
    public void deleteBooking(String authToken, Integer bookingId) {
        given(baseRequestSpec)
                .cookie("token", authToken)
                .pathParam("id", bookingId)
                .when()
                .delete("/booking/{id}/")
                .then()
                .spec(responseSpec(201));
    }

    @Step("Отправка запроса на удаление существующего бронирования DELETE /booking/{id} неавторизованным пользователем")
    public String deleteBookingWithoutAuthorization(Integer bookingId) {
        return given(baseRequestSpec)
                .pathParam("id", bookingId)
                .when()
                .delete("/booking/{id}/")
                .then()
                .spec(responseSpec(403))
                .extract()
                .asString();
    }

    @Step("Отправка запроса на удаление несуществующего бронирования DELETE /booking/{id}")
    public String deleteNonExistingBooking(String authToken, Integer bookingId) {
        return given(baseRequestSpec)
                .cookie("token", authToken)
                .pathParam("id", bookingId)
                .when()
                .delete("/booking/{id}/")
                .then()
                .spec(responseSpec(405))
                .extract()
                .asString();
    }




}
