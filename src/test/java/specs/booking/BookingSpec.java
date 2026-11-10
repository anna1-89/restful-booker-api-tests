package specs.booking;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.notNullValue;

public class BookingSpec {

    public static ResponseSpecification successfulBookingResponseSpec(String schemaPath) {
        return new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectBody(matchesJsonSchemaInClasspath(schemaPath))
                .expectBody("bookingid", notNullValue())
                .expectBody("booking", notNullValue())
                .build();
    }

    public static ResponseSpecification successfulGetUpdateBookingResponseSpec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(200)
            .expectBody(matchesJsonSchemaInClasspath(
                    "schemas/booking/successful_get_update_booking_response_schema.json"))
            .build();

    public static ResponseSpecification responseSpec(int expectedStatusCode) {
        return new ResponseSpecBuilder()
                .expectStatusCode(expectedStatusCode)
                .build();
    }

}
