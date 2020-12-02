package com.parcel.delivery.cotroller;


import com.parcel.delivery.domain.Parcel;
import com.parcel.delivery.domain.request.ParcelRequest;
import com.parcel.delivery.domain.request.UpdateDeliveryStatusRequest;
import com.parcel.delivery.domain.response.MessageResponse;
import com.parcel.delivery.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value= "/delivery", produces = {"application/json"})
public class DeliveryController {


    private final DeliveryService deliveryService;

    public DeliveryController(final DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @GetMapping(value= "/parcel/{agentId}")
    public ResponseEntity<List<Parcel>> getAllParcelDetails(@PathVariable final String agentId){
        return new ResponseEntity<>(deliveryService.getListOfDeliveries(agentId), HttpStatus.OK);

    }

    @PutMapping(value= "/parcel/update", consumes = {"application/json"})
    public ResponseEntity<MessageResponse> updateDeliveryStatus(@Valid @RequestBody final UpdateDeliveryStatusRequest updateDeliveryStatusRequest){
        return new ResponseEntity<>(deliveryService.updateDeliveryStatus(updateDeliveryStatusRequest), HttpStatus.OK);

    }

    @PostMapping(value= "/parcel/admin", consumes = {"application/json"})
    public ResponseEntity<MessageResponse> createParcelEntry(@Valid @RequestBody final ParcelRequest parcelRequest){
        return new ResponseEntity<>(deliveryService.createParcelEntry(parcelRequest), HttpStatus.CREATED);

    }

}
