package com.leduy.backend.dto.request.base;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseRequest {
    @NotBlank(message = "Name _ Không được để trống !")
    private String name;

}
