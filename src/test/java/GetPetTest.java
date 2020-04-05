import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetPetTest {

    @Before
    public void before() {
        RequestSpecBuilder spec = new RequestSpecBuilder();
        spec.setBaseUri("https://petstore.swagger.io/v2");
        spec.addHeader("Content-Type", "application/json");
        RestAssured.requestSpecification = spec.build();
        //#TO DO: add int variable int to before
    }

    @Test
    public void getPetById(){
        String name = "sammy";
        int petId = 999888;
        given()
                .body("{\n" +
                        "  \"id\": " + petId + ",\n" +
                        "  \"category\": {\n" +
                        "    \"id\": 0,\n" +
                        "    \"name\": \"string\"\n" +
                        "  },\n" +
                        "  \"name\": \"sammy\",\n" +
                        "  \"photoUrls\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"tags\": [\n" +
                        "    {\n" +
                        "      \"id\": 0,\n" +
                        "      \"name\": \"string\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"status\": \"available\"\n" +
                        "}")
                .when()
                .post("/pet")
                .then()
                .log()
                .all()
                .body("name", is("sammy"))
                .statusCode(200);

        given()
                .log()
                .all()
                .when()
                .get("/pet/{id}", petId)
                .then()
                .log()
                .all()
                .body("id", is(petId))
                .statusCode(200);
    }
    @Test
    public void getPetsByStatus(){
        String status = "sold";
        given()
                .param("status", status)
                .when()
                .get("/pet/findByStatus")
                .then()
                .log()
                .all()
                .body("status", everyItem(equalTo(status)))
                .statusCode(200);
    }
    @Test
    public void createPet() {
        String name = "sammy";
        int petId = 999888;
        given()
                .body("{\n" +
                        "  \"id\": " + petId + ",\n" +
                        "  \"category\": {\n" +
                        "    \"id\": 0,\n" +
                        "    \"name\": \"string\"\n" +
                        "  },\n" +
                        "  \"name\": \"sammy\",\n" +
                        "  \"photoUrls\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"tags\": [\n" +
                        "    {\n" +
                        "      \"id\": 0,\n" +
                        "      \"name\": \"string\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"status\": \"available\"\n" +
                        "}")
                .when()
                .post("/pet")
                .then()
                .log()
                .all()
                .body("name", is("sammy"))
                .statusCode(200);
    }
    @Test
    public void updatePet() {
        int petId = 999888;
        given()
                .body("{\n" +
                        "  \"id\": " + petId + ",\n" +
                        "  \"category\": {\n" +
                        "    \"id\": 0,\n" +
                        "    \"name\": \"string\"\n" +
                        "  },\n" +
                        "  \"name\": \"sammy\",\n" +
                        "  \"photoUrls\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"tags\": [\n" +
                        "    {\n" +
                        "      \"id\": 0,\n" +
                        "      \"name\": \"string\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"status\": \"available\"\n" +
                        "}")
                .when()
                .post("/pet")
                .then()
                .log()
                .all()
                .body("name", is("sammy"))
                .statusCode(200);

        given()
                .log()
                .all()
                .contentType("application/x-www-form-urlencoded")
                .param("name", "goga")
                .param("status", "unavailable")
                .when()
                .post("/pet/{petId}", petId)
                .then()
                .log()
                .all()
                .body("message", is(String.valueOf(petId)))
                .statusCode(200);
    }
    @Test
    public void updateExistingPet() {
        int petId = 999888;
        given()
                .body("{\n" +
                        "  \"id\": " + petId + ",\n" +
                        "  \"category\": {\n" +
                        "    \"id\": 0,\n" +
                        "    \"name\": \"string\"\n" +
                        "  },\n" +
                        "  \"name\": \"sammy\",\n" +
                        "  \"photoUrls\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"tags\": [\n" +
                        "    {\n" +
                        "      \"id\": 0,\n" +
                        "      \"name\": \"string\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"status\": \"available\"\n" +
                        "}")
                .when()
                .post("/pet")
                .then()
                .log()
                .all()
                .body("name", is("sammy"))
                .statusCode(200);

        String name = "annet";
        given()
                .body("{\n" +
                        "  \"id\": " + petId + " ,\n" +
                        "  \"category\": {\n" +
                        "    \"id\": 0,\n" +
                        "    \"name\": \"string\"\n" +
                        "  },\n" +
                        "  \"name\": \"annet\",\n" +
                        "  \"photoUrls\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"tags\": [\n" +
                        "    {\n" +
                        "      \"id\": 0,\n" +
                        "      \"name\": \"string\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"status\": \"available\"\n" +
                        "}")
                .when()
                .put("/pet")
                .then()
                .log()
                .all()
                .body("name", is("annet"))
                .statusCode(200);
    }
    @Test
    public void deletePet() {
        int petId = 999888;
        given()
        .body("{\n" +
                "  \"id\": " + petId + ",\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"string\"\n" +
                "  },\n" +
                "  \"name\": \"sammy\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}")
                .when()
                .post("/pet")
                .then()
                .log()
                .all()
                .body("name", is("sammy"))
                .statusCode(200);

        given()
                .header("api_key", "special-key")
                .when()
                .delete("/pet/{petId}", petId)
                .then()
                .log()
                .all()
                .body("message", is(String.valueOf(petId)))
                .statusCode(200);
    }
    @After
    public void after() {
        RestAssured.reset();
    }
}

