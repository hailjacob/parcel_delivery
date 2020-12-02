package com.parcel.delivery.domain.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.parcel.delivery.domain.DeliveryAddress;

import javax.validation.constraints.NotNull;

public class ParcelRequest {

    @JsonProperty
    @NotNull(message= "ParcelId can not be null")
    String parcelId;

    @JsonProperty
    String parcelItem;

    @JsonProperty
    @NotNull(message= "deliveryAddress can not be null")
    DeliveryAddress deliveryAddress;

    @JsonProperty
    @NotNull(message= "agentId can not be null")
    String agentId;

    @JsonProperty
    String deliveryStatus;

    public String getParcelId() {
        return parcelId;
    }

    public void setParcelId(String parcelId) {
        this.parcelId = parcelId;
    }

    public String getParcelItem() {
        return parcelItem;
    }

    public void setParcelItem(String parcelItem) {
        this.parcelItem = parcelItem;
    }

    public DeliveryAddress getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(DeliveryAddress deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }
}
