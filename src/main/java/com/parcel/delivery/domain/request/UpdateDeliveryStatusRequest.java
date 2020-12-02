package com.parcel.delivery.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;


public class UpdateDeliveryStatusRequest {

    @JsonProperty
    @NotNull(message= "ParcelId can not be null")
    String parcelId;

    @JsonProperty
    String deliveryStatus;

    public String getParcelId() {
        return parcelId;
    }

    public void setParcelId(String parcelId) {
        this.parcelId = parcelId;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
}
