package com.parcel.delivery.cotroller;

import com.parcel.delivery.DeliveryApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.given;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DeliveryApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"security.basic.enabled = false"})
public class DeliveryControllerIntegrationTest {

    @LocalServerPort
    private int port;


    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    @Test
    public void testGetAllParcelDetailsWithWrongCreds() {
        given().auth().basic("agent", "admin").when().get(createURLWithPort("/delivery/parcel/Agent13")).then().statusCode(401);
    }

    @Test
    public void testGetAllParcelDetails() {
        given().auth().basic("agent", "agent").when().get(createURLWithPort("/delivery/parcel/Agent13")).then().statusCode(200);
    }

}

