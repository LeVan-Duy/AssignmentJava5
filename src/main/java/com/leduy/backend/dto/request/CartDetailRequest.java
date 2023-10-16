package com.leduy.backend.dto.request;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDetailRequest {
    private String id;

    private ProductDetailRequest productDetailRequest;

    private CartRequest cartRequest;
}
