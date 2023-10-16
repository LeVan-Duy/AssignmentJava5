package com.leduy.backend.entity;

import com.leduy.backend.entity.base.PrimaryEntity;
import com.leduy.backend.infrastructure.constant.EntityProperties;
import com.leduy.backend.infrastructure.constant.StatusShopOrder;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@Table(name = "shop_order")
@AllArgsConstructor
@NoArgsConstructor
public class ShopOrder extends PrimaryEntity {

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "completion_date")
    private Long completionDate;

    @Column(name = "confirmation_ate")
    private Long confirmationDate;

    @Column(name = "delivery_start_date")
    private Long deliveryStartDate;

    @Column(name = "expected_delivery_date")
    private Long expectedDeliveryDate;

    @Column(name = "note", length = EntityProperties.LENGTH_DESCRIPTION)
    private String note;

    @Column(name = "phone_number", length = EntityProperties.LENGTH_PHONE)
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "received_date")
    private Long receivedDate;

    @Column(name = "shipping_money")
    private Float shippingMoney;

    @Column(name = "total_money")
    private Float totalMoney;

    @Column(name = "status")
    private StatusShopOrder statusShopOrder;

    @Column(name = "type")
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

//    @OneToMany(mappedBy = "shopOrder", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<OrderDetail> orderDetailList;

}
