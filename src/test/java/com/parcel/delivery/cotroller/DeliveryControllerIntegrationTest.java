package com.parcel.delivery.cotroller;

import com.parcel.delivery.domain.request.ParcelRequest;
import com.parcel.delivery.domain.request.UpdateDeliveryStatusRequest;
import com.parcel.delivery.factory.DeliveryFactory;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.given;

@ExtendWith(SpringExtension.class)
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class DeliveryControllerIntegrationTest {

    @LocalServerPort
    private int port;


    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    @Test
    @DisplayName("Test get parcel details with invalid credentials throws  401")
    public void testGetAllParcelDetailsWithWrongCredentials() {
        given()
                .auth()
                .basic("hello", "hello")
                .when()
                .get(createURLWithPort("/delivery/v1/parcel/Agent13"))
                .then()
                .statusCode(401);
    }

    @Test
    @DisplayName("Test retrieve parcel details by agent ")
    public void testGetAllParcelDetails() {
        given()
                .auth()
                .basic("agent", "agent")
                .when().get(createURLWithPort("/delivery/v1/parcel/Agent13"))
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("Test Adding new  parcel by Admin ")
    public void testAddNewParcelByAdmin() throws Exception {
        ParcelRequest parcelRequest = DeliveryFactory.getParcelRequest();
        given()
                .auth()
                .basic("admin", "admin")
                .body(parcelRequest)
                .contentType(ContentType.JSON)
                .when().post(createURLWithPort("/delivery/v1/parcel"))
                .then()
                .statusCode(201);

    }

    @Test
    @DisplayName("Test Adding new  parcel by Agent  throws unauthorized")
    public void testAddNewParcelByAgent() throws Exception {
        ParcelRequest parcelRequest = DeliveryFactory.getParcelRequest();
        given()
                .auth()
                .basic("agent", "agent")
                .body(parcelRequest)
                .contentType(ContentType.JSON)
                .when().post(createURLWithPort("/delivery/v1/parcel"))
                .then()
                .statusCode(403);

    }

    @Test
    @DisplayName("Test update delivery Status by agent ")
    public void testUpdateDeliveryStatus() throws Exception {
        UpdateDeliveryStatusRequest updateDeliveryStatusRequest = DeliveryFactory.getUpdateDeliveryStatusRequest();
        given()
                .auth()
                .basic("admin", "admin")
                .body(updateDeliveryStatusRequest)
                .contentType(ContentType.JSON)
                .when().put(createURLWithPort("/delivery/v1/parcel"))
                .then()
                .statusCode(200);

    }

}

