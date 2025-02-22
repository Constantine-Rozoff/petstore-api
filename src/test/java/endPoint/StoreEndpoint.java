package endPoint;

import io.restassured.response.ValidatableResponse;
import model.Order;
import model.OrderStatus;
import model.Status;
import net.thucydides.core.annotations.Step;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

public class StoreEndpoint extends EndPoint {

    @Step
    public ValidatableResponse createOrder(Order order) {
        return given()
                .body(order)
                .when()
                .post(CREATE_ORDER)
                .then()
                //.body("id", is(orderId)
                .body("petId", is(order.getPetId()))
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
    public ValidatableResponse getInventoryByStatus(OrderStatus status) {
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
