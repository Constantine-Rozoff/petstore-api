import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UpdateExistingPetTest {

    private PetEndpoint petEndpoint = new PetEndpoint();
    private long createdPetId;

    @Before
    public void createPet() {
        Pet pet = new Pet("0", "sammy", Status.AVAILABLE);
        ValidatableResponse response = petEndpoint.createPet(pet);
        createdPetId = response.extract().path("id");
    }
    @After
    public void deletePet() {
        petEndpoint.deletePet(createdPetId);
    }
    @Test
    public void updateExistingPet() {
        Pet petUpdated = new Pet(Long.toString(createdPetId), "annet", Status.PENDING);
        ValidatableResponse response = petEndpoint.updateExistingPet(petUpdated);
    }
}
