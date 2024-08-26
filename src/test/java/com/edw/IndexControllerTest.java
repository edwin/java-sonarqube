package com.edw;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasToString;

/**
 * <pre>
 *  com.edw.IndexControllerTest
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 25 Aug 2024 21:08
 */
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DisplayName("01. Testing Index API")
public class IndexControllerTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = this.port;
    }

    @Test
    @DisplayName("01. Test Get Index Page should give 200")
    public void test_getIndexPageShouldGive_200() {
        given()
                .log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
            .when()
                .get("/")
            .then()
                .statusCode(200)
                .body("hello", hasToString("world"))
                .log().all();
    }

}
