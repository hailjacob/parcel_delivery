package com.parcel.delivery.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.parcel.delivery.domain.enums.DeliveryStatus;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document(collection = "Parcel_Details")
public class Parcel {

    @JsonProperty
    @Id
    String parcelId;

    @JsonProperty
    String parcelItem;

    @JsonProperty
    DeliveryAddress deliveryAddress;

    @JsonProperty
    String agentId;

    @JsonProperty
    DeliveryStatus deliveryStatus;

    @JsonProperty
    Instant date;

    public Parcel(String parcelId, String parcelItem, DeliveryAddress deliveryAddress, String agentId, DeliveryStatus deliveryStatus) {
        this.parcelId = parcelId;
        this.parcelItem = parcelItem;
        this.deliveryAddress = deliveryAddress;
        this.deliveryStatus = deliveryStatus;
        this.agentId = agentId;
    }

    public Parcel() {

    }

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

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }
}
