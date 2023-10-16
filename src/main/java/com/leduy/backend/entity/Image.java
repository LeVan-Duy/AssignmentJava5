package com.leduy.backend.entity;

import com.leduy.backend.entity.base.PrimaryEntity;
import com.leduy.backend.infrastructure.constant.EntityProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "image")
@AllArgsConstructor
@NoArgsConstructor
public class Image extends PrimaryEntity {
    @Column(name = "is_cover")
    private Boolean isCoVer;

    @Column(name = "url", length = EntityProperties.LENGTH_DESCRIPTION)
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_detail_id")
    private ProductDetail productDetail;
}
