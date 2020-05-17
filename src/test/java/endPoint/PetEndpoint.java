package endPoint;

import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import model.Order;
import model.Pet;
import model.Status;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.hamcrest.Matchers;

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

    private final static String CREATE_ORDER = "/store/order";
    private final static String GET_STORE_ORDER_BY_ID = "/store/order/{orderId}";
    private final static String GET_INVENTORY_BY_STATUS = "/store/inventory";
    private final static String DELETE_ORDER_BY_ID = "/store/order/{orderId}";




    static {
        SerenityRest.filters(new RequestLoggingFilter(LogDetail.ALL));
        SerenityRest.filters(new ResponseLoggingFilter(LogDetail.ALL));
    }

    private RequestSpecification given() {
        return SerenityRest
                .given()
                .baseUri("https://petstore.swagger.io/v2")
                .contentType(ContentType.JSON);
    }

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

    @Step
    public ValidatableResponse createOrder(Order order) {
        return given()
                .body(order)
                .when()
                .post(CREATE_ORDER)
                .then()
                .body("petId", is(4))
                .statusCode(SC_OK);
    }

    @Step
    public ValidatableResponse getOrderById(Integer orderId) {
        return given()
                .when()
                .get(GET_STORE_ORDER_BY_ID, orderId)
                .then()
                .body("id", is(orderId))
                .statusCode(SC_OK);
    }

    @Step
    public ValidatableResponse getInventoryByStatus(Status status) {
        return given()
                .param("status", status)
                .when()
                .get(GET_INVENTORY_BY_STATUS)
                .then()
                .body("sold", instanceOf(Integer.class),
                "string", instanceOf(Integer.class),
                        "available", instanceOf(Integer.class))
                .statusCode(SC_OK);
    }

    @Step
    public ValidatableResponse deleteOrder(Integer orderId) {
        return given()
                .when()
                .delete(DELETE_ORDER_BY_ID, orderId)
                .then()
                .body("message", is(String.valueOf(orderId)))
                .statusCode(SC_OK);
    }
}
