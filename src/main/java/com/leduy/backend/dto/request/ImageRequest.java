package com.leduy.backend.dto.request;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ImageRequest {

    private String id;

    private Boolean isCoVer;

    private String url;

    private ProductDetailRequest productDetailRequest;
}
