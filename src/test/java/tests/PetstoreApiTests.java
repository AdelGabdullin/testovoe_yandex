package tests;

import io.restassured.response.Response;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PetstoreApiTests {

    private static final String BASE_URL = "https://petstore.swagger.io/v2/pet";

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void testCreatePet() {
        String petJson = "{ \"id\": 12345, \"name\": \"Dog\", \"status\": \"available\" }";

        given()
                .contentType(ContentType.JSON)
                .body(petJson)
                .when()
                .post()
                .then()
                .statusCode(200)
                .body("name", equalTo("Dog"))
                .body("status", equalTo("available"));
    }

    @Test
    public void testGetPetById() {
        int petId = 12345;

        given()
                .when()
                .get("/" + petId)
                .then()
                .statusCode(200)
                .body("id", equalTo(petId));
    }

    @Test
    public void testUpdatePet() {
        String updatedPetJson = "{ \"id\": 12345, \"name\": \"Dog Updated\", \"status\": \"available\" }";

        given()
                .contentType(ContentType.JSON)
                .body(updatedPetJson)
                .when()
                .put()
                .then()
                .statusCode(200)
                .body("name", equalTo("Dog Updated"));
    }

    @Test
    public void testDeletePet() {
        int petId = 12345;

        given()
                .when()
                .delete("/" + petId)
                .then()
                .statusCode(200);
    }
}
