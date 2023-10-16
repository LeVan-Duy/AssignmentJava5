package com.leduy.backend.dto.request;

import com.leduy.backend.infrastructure.constant.StatusShopOrder;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ShopOrderRequest  {
    private String id;

    private String fullName;

    private Long completionDate;

    private Long confirmationDate;

    private Long deliveryStartDate;

    private Long expectedDeliveryDate;

    private String note;

    private String phoneNumber;

    private String address;

    private Long receivedDate;

    private Float shippingMoney;

    private Float totalMoney;

    private StatusShopOrder statusShopOrder;

    private String type;

    private EmployeeRequest employeeRequest;

    private CustomerRequest customerRequest;

}
