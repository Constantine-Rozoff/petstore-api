package endPoint;

import io.restassured.response.ValidatableResponse;
import model.Pet;
import model.Status;
import net.thucydides.core.annotations.Step;
import org.hamcrest.Matchers;

import java.io.File;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.*;

public class PetEndpoint extends EndPoint {

    @Step
    public ValidatableResponse createPet(Pet pet) {
        return given()
                .body(pet)
                .when()
                .post(CREATE_PET)
                .then()
                .body("name", is("sammy"))
                .statusCode(SC_OK);
    }

    @Step
    public ValidatableResponse getPetById(long petId) {
        return given()
                .when()
                .get(GET_PET_BY_ID, petId)
                .then()
                .body("id", is(petId))
                .statusCode(SC_OK);
    }

    @Step
    public ValidatableResponse deletePet(long petId) {
        return given()
                .when()
                .delete(DELETE_PET_BY_ID, petId)
                .then()
                .body("message", is(String.valueOf(petId)))
                .statusCode(SC_OK);
    }

    @Step
    public ValidatableResponse getPetByStatus(Status status) {
        return given()
                .param("status", status)
                .when()
                .get(GET_PET_BY_STATUS)
                .then()
                .body("status", everyItem(equalTo(status.toString())))
                .statusCode(SC_OK);
    }

    @Step
    public ValidatableResponse updateExistingPet(Pet petUpdated) {
        return given()
                .body(petUpdated)
                .when()
                .put(UPDATE_EXISTING_PET)
                .then()
                .body("name", Matchers.is(petUpdated.getName()))
                .statusCode(SC_OK);
    }

    @Step
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

    @Step
    public ValidatableResponse uploadPetImage(long petId, String fileName) {

        File file = new File(getClass().getClassLoader().getResource(fileName).getFile());

        return given()
                .contentType("multipart/form-data")
                .multiPart(file)
                .when()
                .post(UPLOAD_PET_IMAGE, petId)
                .then()
                .body("message", allOf(containsString("File uploaded"), containsString(file.getName())))
                .statusCode(SC_OK);
    }
}
