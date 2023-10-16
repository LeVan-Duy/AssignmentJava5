package com.leduy.backend.dto.response;

import com.leduy.backend.dto.response.base.BaseResponse;
import lombok.*;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailResponse extends BaseResponse {

    private Float price;

    private int quantity;

    private Float totalPrice;

    private ShopOrderResponse shopOrderResponse;

    private ProductDetailResponse productDetailResponse;

}
