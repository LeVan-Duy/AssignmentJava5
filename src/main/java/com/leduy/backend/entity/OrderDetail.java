package com.leduy.backend.entity;

import com.leduy.backend.entity.base.PrimaryEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "order_detail")
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail extends PrimaryEntity {
    @Column(name = "price")
    private Float price;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "total_price")
    private Float totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_order_id")
    private ShopOrder shopOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_detail_id")
    private ProductDetail productDetail;

}
