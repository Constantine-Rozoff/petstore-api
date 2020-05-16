package test.store;

import endPoint.PetEndpoint;
import io.restassured.response.ValidatableResponse;
import model.Order;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class DeleteOrderByIDTest {

    @Steps
    private PetEndpoint petEndpoint;
    private Integer orderId;

    @Before
    public void createOrder() {
        Order order = Order.builder()
                .id("0")
                .petId(4)
                .quantity(1)
                .shipDate(System.currentTimeMillis())
                //.status("placed")
                .complete(true)
                .build();
        ValidatableResponse response = petEndpoint.createOrder(order);
        orderId = response.extract().path("id");
    }

    @Test
    public void deleteOrder() {
        petEndpoint.deleteOrder(orderId);
    }
}
