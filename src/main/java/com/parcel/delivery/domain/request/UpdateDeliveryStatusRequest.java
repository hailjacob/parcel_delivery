package com.parcel.delivery.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;


public class UpdateDeliveryStatusRequest {

    @JsonProperty
    @NotNull(message= "ParcelId can not be null")
    String parcelId;

    @JsonProperty
    @Pattern(regexp = "ORDER_PLACED|DELIVERED|PICKED|DELIVERY_FAILED|REATTEMPT" , flags = Pattern.Flag.CASE_INSENSITIVE)
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
