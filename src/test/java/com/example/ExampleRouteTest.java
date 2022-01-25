package com.example;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
class ExampleRouteTest {

    @Test
    void testRoute() {
        given()
                .body("{\"field\": \"\"}")
                .when()
                .post("/example")
                .then()
                .statusCode(200);
    }
}
