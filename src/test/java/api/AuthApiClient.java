package api;

import io.qameta.allure.Step;
import models.auth.AuthBodyModel;
import models.auth.BadCredentialsAuthResponseModel;
import models.auth.SuccessfulAuthResponseModel;

import static io.restassured.RestAssured.given;
import static specs.BaseSpec.baseRequestSpec;
import static specs.auth.AuthSpec.badCredentialsAuthResponseSpec;
import static specs.auth.AuthSpec.successfulAuthResponseSpec;

public class AuthApiClient {

    private static final String AUTH_PATH = "/auth";

    @Step("[API][POST] Отправка запроса на авторизацию /auth с валидными данными")
    public SuccessfulAuthResponseModel auth(AuthBodyModel authBody) {
        return given(baseRequestSpec)
                .body(authBody)
                .when()
                .post(AUTH_PATH)
                .then()
                .spec(successfulAuthResponseSpec)
                .extract()
                .as(SuccessfulAuthResponseModel.class);
    }

    @Step("[API][POST] Отправка запроса на авторизацию /auth и получение token")
    public String authAndGetToken(AuthBodyModel authBody) {
        return given(baseRequestSpec)
                .body(authBody)
                .when()
                .post(AUTH_PATH)
                .then()
                .spec(successfulAuthResponseSpec)
                .extract()
                .path("token");
    }

    @Step("[API][POST] Отправка запроса на авторизацию /auth с невалидными данными")
    public BadCredentialsAuthResponseModel authBadCredentials(AuthBodyModel loginBody) {
        return given(baseRequestSpec)
                .body(loginBody)
                .when()
                .post(AUTH_PATH)
                .then()
                .spec(badCredentialsAuthResponseSpec)
                .extract()
                .as(BadCredentialsAuthResponseModel.class);
    }

}
