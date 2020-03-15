import org.junit.Test;
import static io.restassured.RestAssured.given;

public class GetPetTest {
    @Test
    public void getPetById(){
        int id = 1;
        given()
                .log()
                .all()
                .baseUri("https://petstore.swagger.io")
                .when()
                .get("/v2/pet/{id}", id)
                .then()
                .log()
                .all()
                .statusCode(200);
    }
    @Test
    public void getPetsByStatus(){
        String status = "sold";
        given()
                .accept("application/json")
                .baseUri("https://petstore.swagger.io")
                .param("status", status)
                .when()
                .get("/v2/pet/findByStatus")
                .then()
                .log()
                .all()
                .statusCode(200);
    }
    @Test
    public void createPet() {
        given()
                .contentType("application/json")
                .accept("application/json")
                .body("{\n" +
                        "  \"id\": 0,\n" +
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
                .baseUri("https://petstore.swagger.io")
                .when()
                .post("/v2/pet")
                .then()
                .log()
                .all()
                .statusCode(200)
                .contentType("application/json");
        //#TO DO: get ID of created pet and save it in variable
        // Systen.out.println(createdPetId);
    }
    @Test
    public void updatePet() {
        String petId;
        petId = "15435006002686";
        given()
                .param("name", "goga")
                .param("status", "unavailable")
                .baseUri("https://petstore.swagger.io")
                .when()
                .post("/v2/pet/{petId}", petId)
                .then()
                .log()
                .all()
                .statusCode(200);
    }
    @Test
    public void updateExistingPet() {
        String petId;
        petId = "15435006002686";
        given()
                .contentType("application/json")
                .accept("application/json")
                .baseUri("https://petstore.swagger.io")
                .body("{\n" +
                        "  \"id\": 15435006002686,\n" +
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
                .put("/v2/pet/{petId}", petId)
                .then()
                .log()
                .all()
                .statusCode(200);
    }
    @Test
    public void deletePet() {
        String petId;
        petId = "15435006003413";
        given()
                .accept("application/json")
                .header("api_key", "special-key")
                .baseUri("https://petstore.swagger.io")
                .when()
                .post("/v2/pet/{petId}", petId)
                .then()
                .log()
                .all()
                .statusCode(200);
    }
}

