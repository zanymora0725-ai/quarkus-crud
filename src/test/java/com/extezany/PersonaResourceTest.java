package com.extezany;

import com.extezany.dto.PersonaRequestDTO;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class PersonaResourceTest {

    @Test
    public void testListarPersonasEndpoint() {

        given()
                .when().get("/personas")
                .then()
                .statusCode(200);
    }

    @Test
    public void testCrearPersonaEndpoint() {

        PersonaRequestDTO dto = new PersonaRequestDTO();

        dto.nombre = "Juan";
        dto.apellido = "Pérez";
        dto.edad = 30;

        given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when().post("/personas")
                .then()
                .statusCode(201)
                .body("nombre", is("Juan"))
                .body("apellido", is("Pérez"))
                .body("edad", is(30));
    }

    @Test
    public void testBuscarPersonaNoExistente() {

        given()
                .when().get("/personas/9999")
                .then()
                .statusCode(404);
    }

    @Test
    public void testActualizarPersonaEndpoint() {

        PersonaRequestDTO dto = new PersonaRequestDTO();

        dto.nombre = "Ana";
        dto.apellido = "López";
        dto.edad = 25;

        Integer id = given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when().post("/personas")
                .then()
                .statusCode(201)
                .extract().path("id");

        PersonaRequestDTO updateDto = new PersonaRequestDTO();

        updateDto.nombre = "Ana María";
        updateDto.apellido = "López";
        updateDto.edad = 26;

        given()
                .contentType(ContentType.JSON)
                .body(updateDto)
                .when().put("/personas/" + id)
                .then()
                .statusCode(200)
                .body("nombre", is("Ana María"))
                .body("edad", is(26));
    }

    @Test
    public void testEliminarPersonaEndpoint() {

        PersonaRequestDTO dto = new PersonaRequestDTO();

        dto.nombre = "Carlos";
        dto.apellido = "Martínez";
        dto.edad = 40;

        Integer id = given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when().post("/personas")
                .then()
                .statusCode(201)
                .extract().path("id");

        given()
                .when().delete("/personas/" + id)
                .then()
                .statusCode(204);

        given()
                .when().get("/personas/" + id)
                .then()
                .statusCode(404);
    }
}