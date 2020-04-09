import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UpdatePetTest {

    @Before
    public void before() {
        RequestSpecBuilder spec = new RequestSpecBuilder();
        spec.setBaseUri("https://petstore.swagger.io/v2");
        spec.addHeader("Content-Type", "application/json");
        RestAssured.requestSpecification = spec.build();
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
    @After
    public void after() {
        RestAssured.reset();
    }
   }
