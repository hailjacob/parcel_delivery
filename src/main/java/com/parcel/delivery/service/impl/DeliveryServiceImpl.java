package com.parcel.delivery.service.impl;

import com.parcel.delivery.domain.Parcel;
import com.parcel.delivery.domain.request.ParcelRequest;
import com.parcel.delivery.domain.request.UpdateDeliveryStatusRequest;
import com.parcel.delivery.domain.response.MessageResponse;
import com.parcel.delivery.repository.ParcelRepository;
import com.parcel.delivery.service.DeliveryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class DeliveryServiceImpl  implements DeliveryService {

    ParcelRepository parcelRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    public DeliveryServiceImpl(ParcelRepository parcelRepository) {
        this.parcelRepository = parcelRepository;
    }

    @Override
    public List<Parcel> getListOfDeliveries(String agentId) {
        return parcelRepository.findByAgentId(agentId);
    }


    @Override
    public MessageResponse updateDeliveryStatus(UpdateDeliveryStatusRequest updateDeliveryStatusRequest) {
        Optional<Parcel> parcel = parcelRepository.findById(updateDeliveryStatusRequest.getParcelId());

        MessageResponse messageResponse;
        if(parcel.isPresent()) {
            logger.info("Updating delivery Status for "+ updateDeliveryStatusRequest.getParcelId());
            parcel.get().setDeliveryStatus(updateDeliveryStatusRequest.getDeliveryStatus());
            parcel.get().setDate(Instant.now());
            parcelRepository.save(parcel.get());
            return new MessageResponse("Updated Delivery Status for " + updateDeliveryStatusRequest.getParcelId()+ " Successfully");
        }  else {
            return new MessageResponse(updateDeliveryStatusRequest.getParcelId() + " not found ");
        }
    }

    @Override
    public MessageResponse createParcelEntry(ParcelRequest parcelRequest) {

        Parcel newParcel = new Parcel();
        newParcel.setParcelId(parcelRequest.getParcelId());
        newParcel.setParcelItem(parcelRequest.getParcelItem());
        newParcel.setDeliveryAddress(parcelRequest.getDeliveryAddress());
        newParcel.setAgentId(parcelRequest.getAgentId());
        newParcel.setDeliveryStatus(parcelRequest.getDeliveryStatus());
        newParcel.setDate(Instant.now());
        parcelRepository.save(newParcel);
        logger.info("Created new Delivery entry  "+ parcelRequest.getParcelId());
        return new MessageResponse("Saved parcel details");
    }
}