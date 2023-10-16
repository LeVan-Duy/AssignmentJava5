package com.leduy.backend.dto.response;

import com.leduy.backend.dto.response.base.BaseResponse;
import com.leduy.backend.infrastructure.constant.StatusShopOrder;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ShopOrderResponse extends BaseResponse {

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

    private EmployeeResponse employeeResponse;

    private CustomerResponse customerResponse;

}
