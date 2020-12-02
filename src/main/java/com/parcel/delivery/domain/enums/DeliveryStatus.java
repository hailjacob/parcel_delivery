package com.parcel.delivery.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.HashMap;
import java.util.Map;

public enum DeliveryStatus {

    ORDER_PLACED("order placed"),
    DELIVERED("delivered"),
    PICKED("picked"),
    DELIVERY_FAILED("failed delivery"),
    REATTEMPT("retry delivery");

    private String value;

    private static final Map<String, DeliveryStatus> CONSTANTS = new HashMap<>();

    static{
        for(DeliveryStatus c: values()){
            CONSTANTS.put(c.value, c);
        }
    }

    DeliveryStatus(String value) {
        this.value = value;
    }



    @JsonCreator
    public static DeliveryStatus fromValue(final String value){
        final DeliveryStatus  constant = CONSTANTS.get(value.toLowerCase());
        if(constant == null )
        {
            throw new IllegalArgumentException(value);
        } else return constant;
    }
}
