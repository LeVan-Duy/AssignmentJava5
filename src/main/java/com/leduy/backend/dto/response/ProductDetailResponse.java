package com.leduy.backend.dto.response;

import com.leduy.backend.dto.response.base.BaseResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailResponse extends BaseResponse {
    private Boolean gender;

    private String description;

    private Float price;

    private ProductResponse productResponse;

    private List<ImageResponse> imageResponseList;

    private ColorResponse colorResponse;

    private BrandResponse brandResponse;

    private SizeResponse sizeResponse;
}
