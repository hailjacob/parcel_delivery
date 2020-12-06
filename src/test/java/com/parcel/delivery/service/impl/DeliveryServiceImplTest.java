package com.parcel.delivery.service.impl;

import com.parcel.delivery.domain.Parcel;
import com.parcel.delivery.domain.response.MessageResponse;
import com.parcel.delivery.factory.DeliveryFactory;
import com.parcel.delivery.repository.ParcelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


class DeliveryServiceImplTest {

    ParcelRepository parcelRepository = mock(ParcelRepository.class);

   @InjectMocks
   private DeliveryServiceImpl DeliveryServiceImpl;

   @BeforeEach
   public void setup() {
        DeliveryServiceImpl = new DeliveryServiceImpl(parcelRepository);
    }

    @Test
    void getListOfDeliveries() {
        when(parcelRepository.findByAgentId(any())).thenReturn(DeliveryFactory.getParcels());
        List<Parcel> parcels = DeliveryServiceImpl.getListOfDeliveries(DeliveryFactory.MOCKED_AGENT_ID);
        assertNotNull(parcels);
        assertEquals(DeliveryFactory.MOCKED_AGENT_ID,parcels.get(0).getAgentId());
        assertEquals("X1234",parcels.get(0).getParcelId());
    }

    @Test
    void updateDeliveryStatus() {
        when(parcelRepository.findById(any())).thenReturn(DeliveryFactory.getNewParcel());
        MessageResponse messageResponse = DeliveryServiceImpl.updateDeliveryStatus(DeliveryFactory.getUpdateDeliveryStatusRequest());
        System.out.println(messageResponse);
        assertNotNull(messageResponse);
        assertEquals(messageResponse.getMessage(),"Updated Delivery Status for X1234 Successfully");

    }

    @Test
    void createParcelEntry() {
        MessageResponse messageResponse = DeliveryServiceImpl.createParcelEntry(DeliveryFactory.getParcelRequest());
        assertNotNull(messageResponse);
        assertEquals(messageResponse.getMessage(),"Saved parcel details");
    }
}