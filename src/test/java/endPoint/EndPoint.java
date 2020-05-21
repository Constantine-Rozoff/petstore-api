package endPoint;

import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;

public class EndPoint {

    protected final static String CREATE_PET = "/pet";
    protected final static String GET_PET_BY_ID = "/pet/{id}";
    protected final static String DELETE_PET_BY_ID = "/pet/{id}";
    protected final static String GET_PET_BY_STATUS = "/pet/findByStatus";
    protected final static String UPDATE_EXISTING_PET = "/pet";
    protected final static String UPDATE_PET = "/pet/{id}";
    protected final static String UPLOAD_PET_IMAGE = "/pet/{id}/uploadImage";

    protected final static String CREATE_ORDER = "/store/order";
    protected final static String GET_STORE_ORDER_BY_ID = "/store/order/{orderId}";
    protected final static String GET_INVENTORY_BY_STATUS = "/store/inventory";
    protected final static String DELETE_ORDER_BY_ID = "/store/order/{orderId}";

    static {
        SerenityRest.filters(new RequestLoggingFilter(LogDetail.ALL));
        SerenityRest.filters(new ResponseLoggingFilter(LogDetail.ALL));
    }

    protected RequestSpecification given() {
        return SerenityRest
                .given()
                .baseUri("https://petstore.swagger.io/v2")
                .contentType(ContentType.JSON);
    }
}
