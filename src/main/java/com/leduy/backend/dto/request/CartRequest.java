package com.leduy.backend.dto.request;

import lombok.*;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CartRequest {
    private String id;

    private CustomerRequest customerRequest;
}
