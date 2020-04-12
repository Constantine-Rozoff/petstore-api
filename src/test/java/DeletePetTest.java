import io.restassured.response.ValidatableResponse;
import org.junit.Before;
import org.junit.Test;

public class DeletePetTest {

    PetEndpoint petEndpoint = new PetEndpoint();
    long createdPetId;

    @Before
    public void createPet() {
        int id = 0;
        String body = "{\n" +
                "  \"id\": \""+ id +"\",\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"string\"\n" +
                "  },\n" +
                "  \"name\": \"sammy\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}";
                ValidatableResponse response = petEndpoint.createPet(body);
                createdPetId = response.extract().path("id");
    }
    @Test
    public void deletePet() {
        petEndpoint.deletePet(createdPetId);
    }
}
