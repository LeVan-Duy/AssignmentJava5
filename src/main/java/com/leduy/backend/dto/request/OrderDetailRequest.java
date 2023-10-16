package com.leduy.backend.dto.request;

import lombok.*;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailRequest {
    private String id;

    private Float price;

    private int quantity;

    private Float totalPrice;

    private ShopOrderRequest shopOrderRequest;

    private ProductDetailRequest productDetailRequest;

}
