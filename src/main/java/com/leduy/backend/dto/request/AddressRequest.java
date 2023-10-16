package com.leduy.backend.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressRequest {

    private String id;

    private String city;

    private String country;

    private Boolean isDefault;

    private String line;

    private String phoneNumber;

    private String province;

    private CustomerRequest customerRequest;
}
