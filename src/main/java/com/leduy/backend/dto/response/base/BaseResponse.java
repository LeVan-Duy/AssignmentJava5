package com.leduy.backend.dto.response.base;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseResponse {
    private String id;

    private Long createdAt;

    private Long updatedAt;

    private String createBy;

    private String updateBy;

    private Boolean deleted;
}
