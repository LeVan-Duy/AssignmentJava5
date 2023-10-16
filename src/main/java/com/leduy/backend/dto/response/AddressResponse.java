package com.leduy.backend.dto.response;

import com.leduy.backend.dto.response.base.BaseResponse;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AddressResponse extends BaseResponse {

    private String city;

    private String country;

    private Boolean isDefault;

    private String line;

    private String phoneNumber;

    private String province;

    private CustomerResponse customerResponse;
}
