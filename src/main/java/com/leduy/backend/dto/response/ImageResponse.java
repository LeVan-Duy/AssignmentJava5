package com.leduy.backend.dto.response;

import com.leduy.backend.dto.response.base.BaseResponse;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ImageResponse extends BaseResponse {

    private Boolean isCoVer;

    private String url;

    private ProductDetailResponse productDetailResponse;
}
