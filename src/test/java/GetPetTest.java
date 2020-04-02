import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class GetPetTest {

    @Before
    public void before() {
        RequestSpecBuilder spec = new RequestSpecBuilder();
        spec.setBaseUri("https://petstore.swagger.io/v2");
        spec.addHeader("Content-Type", "application/json");
        RestAssured.requestSpecification = spec.build();
    }

    @Test
    public void getPetById(){
        int id = 5;
        given()
                .log()
                .all()
                .when()
                .get("/pet/{id}", id)
                .then()
                .log()
                .all()
                .body("id", is(id))
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
                .body("status[0]", is("sold"))
                .statusCode(200);
    }
    @Test
    public void createPet() {
        String name = "sammy";
        given()
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
        String petId;
        petId = "1845563262948980734";
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
                .body("message", is(petId))
                .statusCode(200);
    }
    @Test
    public void updateExistingPet() {
        String name = "annet";
        given()
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
                .put("/pet")
                .then()
                .log()
                .all()
                .body("name", is("annet"))
                .statusCode(200);
    }
    @Test
    public void deletePet() {
            String petId;
            petId = "1845563262948980750";
            given()
                    .header("api_key", "special-key")
                    .when()
                    .delete("/pet/{petId}", petId)
                    .then()
                    .log()
                    .all()
                    .body("message", is(petId))
                    .statusCode(200);
    }
}

