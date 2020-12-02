package com.parcel.delivery.service;

import com.parcel.delivery.domain.Parcel;
import com.parcel.delivery.domain.request.ParcelRequest;
import com.parcel.delivery.domain.request.UpdateDeliveryStatusRequest;
import com.parcel.delivery.domain.response.MessageResponse;
import org.springframework.stereotype.Service;

import java.util.List;


public interface DeliveryService {

    List<Parcel> getListOfDeliveries(String agentId);

    MessageResponse updateDeliveryStatus(UpdateDeliveryStatusRequest updateDeliveryStatusRequest);

    MessageResponse createParcelEntry(ParcelRequest parcelRequest);
}
