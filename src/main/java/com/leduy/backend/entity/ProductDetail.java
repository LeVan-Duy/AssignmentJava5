package com.leduy.backend.entity;

import com.leduy.backend.entity.base.PrimaryEntity;
import com.leduy.backend.infrastructure.constant.EntityProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "product_detail")
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetail extends PrimaryEntity {
    @Column(name = "gender")
    private Boolean gender;

    @Column(name = "description", length = EntityProperties.LENGTH_DESCRIPTION)
    private String description;

    @Column(name = "price")
    private Float price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(mappedBy = "productDetail", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Image> imageList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "color_id")
    private Color color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "size_id")
    private Size size;
}
