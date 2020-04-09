import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetPetByIdTest {

    long createdPetId;

    @Before
    public void before2() {
        RequestSpecBuilder spec = new RequestSpecBuilder();
        spec.setBaseUri("https://petstore.swagger.io/v2");
        spec.addHeader("Content-Type", "application/json");
        RestAssured.requestSpecification = spec.build();
    }
    @Before
    public void before1() {
        int id = 0;
        String body = "{\n" +
                "  \"id\": \""+ id +"\",\n" +
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
                "}";
        ValidatableResponse response = given()
                .log()
                .all()
                .body(body)
                .when()
                .post("/pet")
                .then()
                .log()
                .all()
                //.body( "id", is(id))
                .statusCode(200);

        createdPetId = response.extract().path("id");
        System.out.println(createdPetId);
    }
    @Test
    public void getPetById(){
        given()
                .log()
                .all()
                .when()
                .get("/pet/{id}", createdPetId)
                .then()
                .log()
                .all()
                .body("id", is(createdPetId))
                .statusCode(200);
    }
    @After
    public void after() {
        given()
                .log()
                .all()
                .when()
                .delete("/pet/{id}", createdPetId)
                .then()
                .log()
                .all()
                .body("message", is(String.valueOf(createdPetId)))
                .statusCode(200);
    }
}

