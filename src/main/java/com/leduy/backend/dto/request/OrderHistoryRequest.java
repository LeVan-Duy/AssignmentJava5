package com.leduy.backend.dto.request;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderHistoryRequest {
    private String id;

    private String actionDescription;

    private String note;

    private ShopOrderRequest shopOrderRequest;

}
