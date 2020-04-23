import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UploadUnsupportedFormatImageTest {

    private PetEndpoint petEndpoint = new PetEndpoint();
    private long createdPetId;

    @Before
    public void createPet() {
        Pet pet = new Pet("0", "sammy", Status.AVAILABLE);
        ValidatableResponse response = petEndpoint.createPet(pet);
        createdPetId = response.extract().path("id");
    }

    @Test
    public void uploadPetImage() {petEndpoint.uploadPetImage(createdPetId, "Petstore_API_tests.txt");}

    @After
    public void deletePet() {
        petEndpoint.deletePet(createdPetId);
    }
}
