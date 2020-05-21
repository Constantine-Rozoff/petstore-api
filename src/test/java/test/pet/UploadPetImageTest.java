package test.pet;

import endPoint.PetEndpoint;
import io.restassured.response.ValidatableResponse;
import model.Category;
import model.Pet;
import model.Status;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.junit.annotations.TestData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import java.util.Arrays;
import java.util.Collection;

@RunWith(SerenityParameterizedRunner.class)
public class UploadPetImageTest {

    @Steps
    private PetEndpoint petEndpoint;
    private Integer createdPetId;
    private final String fileName;

    public UploadPetImageTest(String fileName) {
        this.fileName = fileName;
    }

    @TestData
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
            {"Cat.jpg"},
            {"Dog.png"},
            {"Airbus.tif"},
            {"Petstore_API_tests.txt"}
        });
    }

    @Before
    public void createPet() {
        Pet pet = Pet.builder()
                .id("0")
                .name("sammy")
                .status(Status.AVAILABLE)
                .category(Category.builder().id("0").name("animal").build())
                .build();
        ValidatableResponse response = petEndpoint.createPet(pet);
        createdPetId = response.extract().path("id");
    }

    @Test
    public void uploadPetImage() {petEndpoint.uploadPetImage(createdPetId, fileName);}

    @After
    public void deletePet() {
        petEndpoint.deletePet(createdPetId);
    }
}
