package com.leduy.backend.dto.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailRequest {
    private String id;

    private Boolean gender;

    private String description;

    private Float price;

    private ProductRequest productRequest;

    private List<ImageRequest> imageRequestList;

    private ColorRequest colorRequest;

    private BrandRequest brandRequest;

    private SizeRequest sizeRequest;
}
