package tests;

import api.ApiClient;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import test_data.TestData;

public class TestBase {

    protected static final ApiClient api = new ApiClient();
    protected static final TestData testData = new TestData();

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    }
}
