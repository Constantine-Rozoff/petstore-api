package test.store;

import endPoint.PetEndpoint;
import endPoint.StoreEndpoint;
import io.restassured.response.ValidatableResponse;
import model.Order;
import model.OrderStatus;
import model.Pet;
import model.Status;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class DeleteOrderByIDTest {

    @Steps
    private PetEndpoint petEndpoint;
    private long createdPetId;

    @Steps
    private StoreEndpoint storeEndpoint;
    private Integer orderId;

    @Before
    public void createOrder() {
        Pet pet = Pet.builder()
                .id("0")
                .name("sammy")
                .status(Status.AVAILABLE)
                .build();
        ValidatableResponse response = petEndpoint.createPet(pet);
        createdPetId = response.extract().path("id");

        Order order = Order.builder()
                .id("0")
                .petId(createdPetId)
                .quantity(1)
                .shipDate(System.currentTimeMillis())
                .status(OrderStatus.PLACED)
                .complete(true)
                .build();
        ValidatableResponse responseOrder = storeEndpoint.createOrder(order);
        orderId = responseOrder.extract().path("id");
    }

    @After
    public void deletePet() {
        petEndpoint.deletePet(createdPetId);
    }

    @Test
    public void deleteOrder() {
        storeEndpoint.deleteOrder(orderId);
    }
}
