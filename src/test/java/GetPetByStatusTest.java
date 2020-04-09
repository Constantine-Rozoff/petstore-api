import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetPetByStatusTest {

    @Before
    public void before() {
        RequestSpecBuilder spec = new RequestSpecBuilder();
        spec.setBaseUri("https://petstore.swagger.io/v2");
        spec.addHeader("Content-Type", "application/json");
        RestAssured.requestSpecification = spec.build();
    }
        @Test
        public void getPetsByStatus() {
            String status = "sold";
            given()
                    .param("status", status)
                    .when()
                    .get("/pet/findByStatus")
                    .then()
                    .log()
                    .all()
                    .body("status", everyItem(equalTo(status)))
                    .statusCode(200);
        }
    }