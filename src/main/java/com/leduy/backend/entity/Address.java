package com.leduy.backend.entity;

import com.leduy.backend.entity.base.PrimaryEntity;
import com.leduy.backend.infrastructure.constant.EntityProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "address")
@AllArgsConstructor
@NoArgsConstructor
public class Address extends PrimaryEntity {

    @Column(name = "city", length = EntityProperties.LENGTH_EMAIL)
    private String city;

    @Column(name = "country", length = EntityProperties.LENGTH_NAME)
    private String country;

    @Column(name = "is_default")
    private Boolean isDefault;

    @Column(name = "line")
    private String line;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "province")
    private String province;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
