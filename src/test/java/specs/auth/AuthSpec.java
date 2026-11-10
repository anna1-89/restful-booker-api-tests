package specs.auth;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.notNullValue;

public class AuthSpec {

    public static ResponseSpecification successfulAuthResponseSpec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(200)
            .expectBody(matchesJsonSchemaInClasspath("schemas/auth/successful_auth_response_schema.json"))
            .expectBody("token", notNullValue())
            .build();

    public static ResponseSpecification badCredentialsAuthResponseSpec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(200)
            .expectBody(matchesJsonSchemaInClasspath("schemas/auth/bad_credentials_auth_response_schema.json"))
            .expectBody("reason", notNullValue())
            .build();
}
