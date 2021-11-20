package ru.voronasever.voronaStore.payload.request;

import lombok.Getter;

@Getter
public class CreateOrderRequest {
    private String phoneNumber;
    private String address;

}
