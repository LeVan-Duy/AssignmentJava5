package com.leduy.backend.dto.response;

import com.leduy.backend.dto.response.base.BaseResponse;
import com.leduy.backend.infrastructure.constant.Role;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeResponse extends BaseResponse {


    private String email;

    private String fullName;

    private String address;

    private Boolean gender;

    private String passWord;

    private Role role;
}
