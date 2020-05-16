package test.store;

import endPoint.PetEndpoint;
import io.restassured.response.ValidatableResponse;
import model.Order;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GetStoreOrderByIdTest {

    @Steps
    private PetEndpoint petEndpoint;
    private Order order;
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

    @After
    public void deleteOrder() {
        petEndpoint.deleteOrder(orderId);
    }

    @Test
    public void getOrderById() { petEndpoint.getOrderById(orderId); }
}
