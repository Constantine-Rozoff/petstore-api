import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GetPetByIdTest {

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
    @After
    public void deletePet() {
        petEndpoint.deletePet(createdPetId);
    }
    @Test
    public void getPetById(){
        petEndpoint.getPetById(createdPetId);
    }
}

