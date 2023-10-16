package com.leduy.backend.entity;

import com.leduy.backend.entity.base.PrimaryEntity;
import com.leduy.backend.infrastructure.constant.EntityProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;


@Entity
@Getter
@Setter
@ToString
@Table(name = "customer")
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends PrimaryEntity {
    @Column(name = "date_of_birth")
    private Long dateOfBirth;

    @Column(name = "email",length = EntityProperties.LENGTH_EMAIL)
    private String email;

    @Column(name = "full_name",length = EntityProperties.LENGTH_NAME)
    private String fullName;

    @Column(name = "gender")
    private Boolean gender;

    @Column(name = "password")
    private String  passWord;
}
