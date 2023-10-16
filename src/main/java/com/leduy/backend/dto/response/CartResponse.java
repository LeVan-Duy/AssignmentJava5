package com.leduy.backend.dto.response;

import com.leduy.backend.dto.response.base.BaseResponse;
import lombok.*;




@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CartResponse extends BaseResponse {

    private CustomerResponse customerResponse;
}
