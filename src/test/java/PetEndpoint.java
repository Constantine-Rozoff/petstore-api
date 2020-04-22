import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import java.io.File;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

public class PetEndpoint {

    private final static String CREATE_PET = "/pet";
    private final static String GET_PET_BY_ID = "/pet/{id}";
    private final static String DELETE_PET_BY_ID = "/pet/{id}";
    private final static String GET_PET_BY_STATUS = "/pet/findByStatus";
    private final static String UPDATE_EXISTING_PET = "/pet";
    private final static String UPDATE_PET = "/pet/{id}";
    private final static String UPLOAD_PET_IMAGE = "/pet/{id}/uploadImage";

    static {
        RestAssured.filters(new RequestLoggingFilter(LogDetail.ALL));
        RestAssured.filters(new ResponseLoggingFilter(LogDetail.ALL));
    }

    private RequestSpecification given() {
        return RestAssured
                .given()
                .baseUri("https://petstore.swagger.io/v2")
                .contentType(ContentType.JSON);
    }
    public ValidatableResponse createPet(Pet pet) {
        return given()
                .body(pet)
                .when()
                .post(CREATE_PET)
                .then()
                .statusCode(SC_OK);
    }
    public ValidatableResponse getPetById(long petId) {
        return given()
                .when()
                .get(GET_PET_BY_ID, petId)
                .then()
                .body( "id", anyOf(is(petId), is(Status.AVAILABLE)))
                .statusCode(SC_OK);
    }
    public ValidatableResponse deletePet(long petId) {
        return given()
                .when()
                .delete(DELETE_PET_BY_ID, petId)
                .then()
                .body("message", is(String.valueOf(petId)))
                .statusCode(SC_OK);
    }
    public ValidatableResponse getPetByStatus(Status status) {
        return given()
                .param("status", status)
                .when()
                .get(GET_PET_BY_STATUS)
                .then()
                .body("status", everyItem(equalTo(status)))
                .statusCode(SC_OK);
    }
    public ValidatableResponse updateExistingPet(Pet petUpdated) {
        return given()
                .body(petUpdated)
                .when()
                .put(UPDATE_EXISTING_PET)
                .then()
                .body("name", is("annet"))
                .statusCode(SC_OK);
    }
    public ValidatableResponse updatePet(long petId) {
        return given()
                .contentType("application/x-www-form-urlencoded")
                .param("name", "goga")
                .param("status", Status.SOLD)
                .when()
                .post(UPDATE_PET, petId)
                .then()
                .body("message", is(String.valueOf(petId)))
                .statusCode(SC_OK);
    }
    public ValidatableResponse uploadPetImage(long petId) {
        return given()
                .contentType("multipart/form-data")
                .header("api-key", "special-key")
                .multiPart(new File("C:/Users/test/Desktop/HindMD/Cat.jpg"))
                .when()
                .post(UPLOAD_PET_IMAGE, petId)
                .then()
                .body("message", is("additionalMetadata: null\nFile uploaded to ./Cat.jpg, 216172 bytes"))
                .statusCode(SC_OK);

    }
}
