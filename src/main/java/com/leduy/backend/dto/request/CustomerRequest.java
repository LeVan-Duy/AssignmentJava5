package com.leduy.backend.dto.request;

import lombok.*;


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRequest {
    private String id;

    private Long dateOfBirth;

    private String email;

    private String fullName;

    private String  passWord;

    private Boolean gender;
}
