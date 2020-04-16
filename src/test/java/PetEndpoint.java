import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

public class PetEndpoint {

    private final static String CREATE_PET = "/pet";
    private final static String GET_PET_BY_ID = "/pet/{id}";
    private final static String DELETE_PET_BY_ID = "/pet/{id}";
    private final static String GET_PET_BY_STATUS = "/pet/findByStatus";
    private final static String UPDATE_EXISTING_PET = "/pet";
    private final static String UPDATE_PET = "/pet/{id}";

    private RequestSpecification given() {
        return RestAssured
                .given()
                .baseUri("https://petstore.swagger.io/v2")
                .contentType(ContentType.JSON)
                .log()
                .all();
    }
    public ValidatableResponse createPet(Pet pet) {
        return given()
                .body(pet)
                .when()
                .post(CREATE_PET)
                .then()
                .log()
                .all()
                .statusCode(200);
    }
    public ValidatableResponse getPetById(long petId) {
        return given()
                .when()
                .get(GET_PET_BY_ID, petId)
                .then()
                .log()
                .all()
                .body( "id", anyOf(is(petId), is("available")))
                .statusCode(200);
    }
    public ValidatableResponse deletePet(long petId) {
        return given()
                .when()
                .delete(DELETE_PET_BY_ID, petId)
                .then()
                .log()
                .all()
                .body("message", is(String.valueOf(petId)))
                .statusCode(200);
    }
    public ValidatableResponse getPetByStatus(String status) {
        return given()
                .param("status", status)
                .when()
                .get(GET_PET_BY_STATUS)
                .then()
                .log()
                .all()
                .body("status", everyItem(equalTo(status)))
                .statusCode(200);
    }
    public ValidatableResponse updateExistingPet(Pet petUpdated) {
        return given()
                .body(petUpdated)
                .when()
                .put(UPDATE_EXISTING_PET)
                .then()
                .log()
                .all()
                .body("name", is("annet"))
                .statusCode(200);
    }
    public ValidatableResponse updatePet(long petId) {
        return given()
                .log()
                .all()
                .contentType("application/x-www-form-urlencoded")
                .param("name", "goga")
                .param("status", "unavailable")
                .when()
                .post(UPDATE_PET, petId)
                .then()
                .log()
                .all()
                .body("message", is(String.valueOf(petId)))
                .statusCode(200);
    }
}
