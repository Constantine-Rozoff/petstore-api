import io.restassured.response.ValidatableResponse;
import org.junit.Test;

public class GetPetByStatusTest {

    PetEndpoint petEndpoint = new PetEndpoint();

        @Test
        public void getPetsByStatus() {
            String status = "sold";
            ValidatableResponse response = petEndpoint.getPetByStatus(status);
        }
    }