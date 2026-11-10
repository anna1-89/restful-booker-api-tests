package tests.auth;

import io.qameta.allure.Story;
import models.auth.AuthBodyModel;
import models.auth.BadCredentialsAuthResponseModel;
import models.auth.SuccessfulAuthResponseModel;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;
import static test_data.TestData.*;

@Story("Функционал авторизации")
public class AuthTests extends TestBase {

    String username;
    String password;

    @BeforeEach
    public void prepareTestData() {
        Faker faker = new Faker();
        username = faker.name().firstName() + System.currentTimeMillis();
        password = faker.name().firstName();
    }

    @Test
    @DisplayName("Успешный вход в систему")
    public void successfulAuthTest() {
        AuthBodyModel authData = new AuthBodyModel(AUTH_USERNAME, AUTH_PASSWORD);
        SuccessfulAuthResponseModel authResponse = api.auth.auth(authData);

        step("Проверка ответа (200, токен получен)", () -> {
            String Token = authResponse.token();
            assertThat(Token).isNotNull();
        });
    }

    @Test
    @DisplayName("Ошибка при попытке входа в систему с невалидным password")
    public void badCredentialsPasswordAuthTest() {
        AuthBodyModel authData = new AuthBodyModel(AUTH_USERNAME, password);

        BadCredentialsAuthResponseModel authResponse = api.auth.authBadCredentials(authData);

        step("Проверка ответа (200, текст с ошибкой)", () -> {
            String expectedReasonError = AUTH_BAD_CREDENTIALS;
            String actualReasonError = authResponse.reason();
            assertThat(actualReasonError).isEqualTo(expectedReasonError);
        });
    }

    @Test
    @DisplayName("Ошибка при попытке входа в систему с невалидным username")
    public void badCredentialsUsernameAuthTest() {
        AuthBodyModel authData = new AuthBodyModel(username, AUTH_PASSWORD);

        BadCredentialsAuthResponseModel authResponse = api.auth.authBadCredentials(authData);

        step("Проверка ответа (200, текст с ошибкой)", () -> {
            String expectedReasonError = AUTH_BAD_CREDENTIALS;
            String actualReasonError = authResponse.reason();
            assertThat(actualReasonError).isEqualTo(expectedReasonError);
        });
    }


    @Test
    @DisplayName("Ошибка при попытке входа в систему с незаполненным password")
    public void blankPasswordAuthTest() {
        AuthBodyModel authData = new AuthBodyModel(AUTH_USERNAME, "");

        BadCredentialsAuthResponseModel authResponse = api.auth.authBadCredentials(authData);

        step("Проверка ответа (200, текст с ошибкой)", () -> {
            String expectedReasonError = AUTH_BAD_CREDENTIALS;
            String actualReasonError = authResponse.reason();
            assertThat(actualReasonError).isEqualTo(expectedReasonError);
        });
    }

    @Test
    @DisplayName("Ошибка при попытке входа в систему с незаполненным username")
    public void blankUsernameAuthTest() {
        AuthBodyModel authData = new AuthBodyModel("", AUTH_PASSWORD);

        BadCredentialsAuthResponseModel authResponse = api.auth.authBadCredentials(authData);

        step("Проверка ответа (200, текст с ошибкой)", () -> {
            String expectedReasonError = AUTH_BAD_CREDENTIALS;
            String actualReasonError = authResponse.reason();
            assertThat(actualReasonError).isEqualTo(expectedReasonError);
        });
    }

    @Test
    @DisplayName("Ошибка при попытке входа в систему с незаполненным username и password")
    public void blankUsernameAndPasswordAuthTest() {
        AuthBodyModel authData = new AuthBodyModel("", AUTH_PASSWORD);

        BadCredentialsAuthResponseModel authResponse = api.auth.authBadCredentials(authData);

        step("Проверка ответа (200, текст с ошибкой)", () -> {
            String expectedReasonError = AUTH_BAD_CREDENTIALS;
            String actualReasonError = authResponse.reason();
            assertThat(actualReasonError).isEqualTo(expectedReasonError);
        });
    }

}
