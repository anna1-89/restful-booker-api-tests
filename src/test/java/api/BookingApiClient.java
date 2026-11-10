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
                .spec(successfulBookingResponseSpec)
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
                .spec(successfulBookingWithRequiredFieldsResponseSpec)
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
                .spec(bookingInternalServerErrorResponseSpec)
                .extract()
                .asString();
    }

    @Step("[API] Отправка запроса на создание POST /booking с получение id созданного бронирования")
    public Integer createBookingAndGetBookindId(BookingBodyModel bookingBody) {
        return given(baseRequestSpec)
                .body(bookingBody)
                .when()
                .post("/booking")
                .then()
                .spec(successfulBookingResponseSpec)
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
                .spec(bookingNotFoundResponseSpec)
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
                .spec(bookingForbiddenResponseSpec)
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
                .spec(bookingBadRequestResponseSpec)
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




}
