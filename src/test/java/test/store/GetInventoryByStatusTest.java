package test.store;

import endPoint.PetEndpoint;
import io.restassured.response.ValidatableResponse;
import model.Order;
import model.Status;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class GetInventoryByStatusTest {

    @Steps
    private PetEndpoint petEndpoint;
    private Order order;
    private Status status;

        @Test
        public void getInventoryByStatus() {
            ValidatableResponse response = petEndpoint.getInventoryByStatus(status);
        }
    }

