package test;

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
public class GetPetByStatusTest {

    @Steps
    private PetEndpoint petEndpoint;
    private long createdPetId;
    private final Status status;

    public GetPetByStatusTest(Status status) {
        this.status = status;
    }

    @TestData
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {Status.AVAILABLE},
                {Status.PENDING},
                {Status.SOLD},
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

    @After
    public void deletePet() {
        petEndpoint.deletePet(createdPetId);
    }

    @Test
    public void getPetsByStatus() {
        ValidatableResponse response = petEndpoint.getPetByStatus(status);
    }
}