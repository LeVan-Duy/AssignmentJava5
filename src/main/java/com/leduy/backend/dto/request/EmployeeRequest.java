package com.leduy.backend.dto.request;

import com.leduy.backend.infrastructure.constant.Role;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeRequest {
    private String id;

    private String email;

    private String fullName;

    private String address;

    private Boolean gender;

    private String passWord;

    private Role role;
}
