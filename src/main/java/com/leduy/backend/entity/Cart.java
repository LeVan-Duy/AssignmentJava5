package com.leduy.backend.entity;

import com.leduy.backend.entity.base.PrimaryEntity;
import jakarta.persistence.*;
import lombok.*;

;


@Entity
@Getter
@Setter
@ToString
@Table(name = "cart")
@AllArgsConstructor
@NoArgsConstructor
public class Cart extends PrimaryEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
