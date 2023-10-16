package com.leduy.backend.entity;

import com.leduy.backend.entity.base.PrimaryEntity;
import com.leduy.backend.infrastructure.constant.EntityProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "order_history")
@AllArgsConstructor
@NoArgsConstructor
public class OrderHistory extends PrimaryEntity {

    @Column(name = "action_description")
    private String actionDescription;

    @Column(name = "note",length = EntityProperties.LENGTH_DESCRIPTION)
    private String note;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_order_id")
    private ShopOrder shopOrder;

}
