package com.parcel.delivery.cotroller;

import com.parcel.delivery.domain.request.ParcelRequest;
import com.parcel.delivery.domain.request.UpdateDeliveryStatusRequest;
import com.parcel.delivery.domain.response.MessageResponse;
import com.parcel.delivery.factory.DeliveryFactory;
import com.parcel.delivery.service.DeliveryService;
import com.parcel.delivery.service.impl.DeliveryServiceImpl;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.response.MockMvcResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.parcel.delivery.factory.DeliveryFactory.MOCKED_AGENT_ID;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;




@ExtendWith(MockitoExtension.class)
class DeliveryControllerTest {


    DeliveryService deliveryService = mock(DeliveryServiceImpl.class);

    @Mock
    FilterChainProxy springSecurityFilterChain;


    @BeforeEach
    public void setup() {
        final MockMvcBuilder mockMvcBuilder = MockMvcBuilders
                .standaloneSetup(new DeliveryController(deliveryService))
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setControllerAdvice(new ExceptionController())
                .apply(SecurityMockMvcConfigurers.springSecurity(springSecurityFilterChain));
       RestAssuredMockMvc.standaloneSetup(mockMvcBuilder);
    }

    @Test
    void getAllParcelDetails() {
    when(deliveryService.getListOfDeliveries(MOCKED_AGENT_ID)).thenReturn(DeliveryFactory.getParcels());
    given()
            .accept(MediaType.APPLICATION_JSON_VALUE)
            .when()
            .get("/delivery/parcel/Agent1")
            .then()
            .statusCode(200);
    }

    @Test
    void updateDeliveryStatus() {
        UpdateDeliveryStatusRequest updateDeliveryStatusRequest = DeliveryFactory.getUpdateDeliveryStatusRequest();
        when(deliveryService.updateDeliveryStatus(updateDeliveryStatusRequest)).thenReturn(new MessageResponse("Updated Delivery Status"));
        MockMvcResponse response = RestAssuredMockMvc.given()
                .body(updateDeliveryStatusRequest)
                .contentType(ContentType.JSON)
                .put("/delivery/update");
          assertEquals(200, response.getStatusCode());
    }

    @Test
    void createParcelEntry() {
        ParcelRequest parcelRequest = DeliveryFactory.getParcelRequest();
        when(deliveryService.createParcelEntry(parcelRequest)).thenReturn(new MessageResponse("Saved parcel details"));
        MockMvcResponse response = RestAssuredMockMvc.given()
                .body(parcelRequest)
                .contentType(ContentType.JSON)
                .post("/delivery/admin");
        assertEquals(200, response.getStatusCode());
    }
}