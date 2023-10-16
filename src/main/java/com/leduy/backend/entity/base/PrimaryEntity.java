package com.leduy.backend.entity.base;

import com.leduy.backend.infrastructure.constant.EntityProperties;
import com.leduy.backend.infrastructure.listener.CreatePrimaryEntityListener;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@MappedSuperclass
@EntityListeners(CreatePrimaryEntityListener.class)
public abstract class PrimaryEntity extends AuditEntity {

    @Id
    @Column(name = "id",length = EntityProperties.LENGTH_ID, updatable = false)
    private String id;

}
