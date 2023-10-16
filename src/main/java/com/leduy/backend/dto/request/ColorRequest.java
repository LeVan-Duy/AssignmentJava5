package com.leduy.backend.dto.request;

import com.leduy.backend.dto.request.base.BaseRequest;
import lombok.*;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ColorRequest extends BaseRequest {
    private String id;
}
