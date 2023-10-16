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
@Table(name = "brand")
@AllArgsConstructor
@NoArgsConstructor
public class Brand extends PrimaryEntity {
    @Column(name = "name",length = EntityProperties.LENGTH_NAME)
    private String name;
}
