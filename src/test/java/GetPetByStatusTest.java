import io.restassured.response.ValidatableResponse;
import org.junit.Test;

public class GetPetByStatusTest {

    private PetEndpoint petEndpoint = new PetEndpoint();

        @Test
        public void getPetsByStatus() {
            Status status = Status.SOLD;
            ValidatableResponse response = petEndpoint.getPetByStatus(status);
        }
    }