package com.leduy.backend.dto.response;

import com.leduy.backend.dto.response.base.BaseResponse;
import lombok.*;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CustomerResponse extends BaseResponse {

    private Long dateOfBirth;

    private String email;

    private String fullName;

    private String  passWord;

    private Boolean gender;
}
