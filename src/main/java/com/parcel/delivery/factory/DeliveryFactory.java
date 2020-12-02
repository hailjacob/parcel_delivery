package com.parcel.delivery.factory;

import com.parcel.delivery.domain.DeliveryAddress;
import com.parcel.delivery.domain.Parcel;
import com.parcel.delivery.domain.enums.DeliveryStatus;
import com.parcel.delivery.domain.request.ParcelRequest;
import com.parcel.delivery.domain.request.UpdateDeliveryStatusRequest;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeliveryFactory {

    public static final String MOCKED_AGENT_ID = "Agent1" ;
    public static List<Parcel> getParcels() {
        Parcel parcel = new Parcel();
        DeliveryAddress  deliveryAddress = new DeliveryAddress();
        parcel.setAgentId(MOCKED_AGENT_ID);
        parcel.setDeliveryAddress(deliveryAddress);
        parcel.setParcelItem("Book");
        parcel.setParcelId("X1234");
        parcel.setDate(Instant.now());
        List<Parcel> parcelList = new ArrayList<>();
        parcelList.add(parcel);
        return parcelList;

    }

    public static UpdateDeliveryStatusRequest getUpdateDeliveryStatusRequest() {
        UpdateDeliveryStatusRequest updateDeliveryStatusRequest = new UpdateDeliveryStatusRequest();
        updateDeliveryStatusRequest.setDeliveryStatus("DELIVERY_FAILED");
        updateDeliveryStatusRequest.setParcelId("X1234");
        return updateDeliveryStatusRequest;
    }

    public static ParcelRequest getParcelRequest() {
        return new ParcelRequest();
    }

    public static Optional<Parcel> getNewParcel() {
        Parcel parcel = new Parcel();
        DeliveryAddress  deliveryAddress = new DeliveryAddress();
        parcel.setDeliveryStatus(DeliveryStatus.DELIVERED);
        parcel.setAgentId(MOCKED_AGENT_ID);
        parcel.setDeliveryAddress(deliveryAddress);
        parcel.setParcelItem("Book");
        parcel.setParcelId("X1234");
        parcel.setDate(Instant.now());
        return Optional.of(parcel);
    }
}
