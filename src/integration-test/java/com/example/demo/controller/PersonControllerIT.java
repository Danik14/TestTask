package com.example.demo.controller;

import com.example.demo.api.dto.PersonCreate;
import com.example.demo.api.dto.PersonResponse;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.mockMvc;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@AutoConfigureMockMvc
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonControllerIT {

    private static final String BASE_RELATIVE_PATH = "/api/v1/person";

    @Autowired
    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        mockMvc(mvc);
    }

    @Test
    @Order(1)
    void testGetAllPeople_success(){
        var actual = given()
                .log().all()
            .when()
                .get(BASE_RELATIVE_PATH)
            .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .body()
                .jsonPath().getList(".", PersonResponse.class);

        var expected = List.of(
                PersonResponse.builder()
                        .id(UUID.fromString("b1dc6a75-8d43-446e-984f-4af8a26d6003"))
                        .firstName("Zharas")
                        .lastName("ZharasLastName")
                        .build(),
                PersonResponse.builder()
                        .id(UUID.fromString("66881aa2-cdaa-46a3-bfc7-0967ad4dec59"))
                        .firstName("Daniyar")
                        .lastName("Danchik")
                        .build(),
                PersonResponse.builder()
                        .id(UUID.fromString("637e9a2d-892b-4c6f-8434-297512b49fe0"))
                        .firstName("Bogdan")
                        .lastName("Marzoev")
                        .build()
                );

        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    @Order(2)
    void testGetPersonById_success_200(){
        var actual = given()
                .log().all()
            .when()
                .get(BASE_RELATIVE_PATH + "/66881aa2-cdaa-46a3-bfc7-0967ad4dec59")
            .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .as(PersonResponse.class);

        var expected = PersonResponse.builder()
                .id(UUID.fromString("66881aa2-cdaa-46a3-bfc7-0967ad4dec59"))
                .firstName("Daniyar")
                .lastName("Danchik")
                .build();

        assertEquals(expected, actual);
    }

    @Test
    @Order(3)
    void testCreatePerson_success_201(){
        var requestBody = PersonCreate.builder()
                .firstName("NewUser")
                .lastName("NewUserLastName")
                .build();

        var actual = given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(requestBody)
            .when()
                .post(BASE_RELATIVE_PATH)
            .then()
                .log().all()
                .assertThat()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .extract()
                .as(PersonResponse.class);

        assertNotNull(actual);
        assertNotNull(actual.getId());
        var expected = PersonResponse.builder()
                .id(actual.getId())
                .firstName("NewUser")
                .lastName("NewUserLastName")
                .build();
        assertEquals(expected, actual);
    }

    @Test
    @Order(4)
    void testCreatePerson_emptyInput_400(){
        var requestBody = PersonCreate.builder()
                .firstName("NewUser")
                .lastName("")
                .build();

        var response = given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(requestBody)
            .when()
                .post(BASE_RELATIVE_PATH)
            .then()
                .log().all()
                .assertThat()
                .statusCode(400)
                .contentType(ContentType.JSON)
                .extract()
                .body()
                .jsonPath();

        var errors = response.getMap("");

        assertEquals(1, errors.size());
        assertEquals("last name must not be empty", errors.get("lastName"));
    }

    @Test
    @Order(5)
    void testCreatePerson_invalidInput_400(){
        var requestBody = PersonCreate.builder()
                .firstName("NewUser")
                .lastName("mkmldsmfldfdklmfmfkmlfsdsmfdslmfdmfsdmlfsdlfsdkmfkmsfdmmsfdlkmsd")
                .build();

        var response = given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(BASE_RELATIVE_PATH)
                .then()
                .log().all()
                .assertThat()
                .statusCode(400)
                .contentType(ContentType.JSON)
                .extract()
                .body()
                .jsonPath();

        var errors = response.getMap("");

        assertEquals(1, errors.size());
        assertEquals("last name's length should be between 1 and 20 letters", errors.get("lastName"));
    }
}
