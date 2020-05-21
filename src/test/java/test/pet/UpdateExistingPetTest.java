package test.pet;

import endPoint.PetEndpoint;
import io.restassured.response.ValidatableResponse;
import model.Category;
import model.Pet;
import model.Status;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(SerenityRunner.class)
public class UpdateExistingPetTest {

    @Steps
    private PetEndpoint petEndpoint;
    private Integer createdPetId;

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

    @After
    public void deletePet() {
        petEndpoint.deletePet(createdPetId);
    }

    @Test
    public void updateExistingPet() {
        Pet petUpdated = Pet.builder()
                .id(Long.toString(createdPetId))
                .name("annet")
                .status(Status.PENDING)
                .build();
        ValidatableResponse response = petEndpoint.updateExistingPet(petUpdated);
    }
}
