package com.leduy.backend.dto.response;

import com.leduy.backend.dto.response.base.BaseResponse;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderHistoryResponse extends BaseResponse {

    private String actionDescription;

    private String note;

    private ShopOrderResponse shopOrderResponse;

}
