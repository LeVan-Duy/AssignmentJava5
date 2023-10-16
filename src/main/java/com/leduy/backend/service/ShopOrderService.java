package com.leduy.backend.service;

import com.leduy.backend.dto.request.ShopOrderRequest;
import com.leduy.backend.dto.request.base.PaginationRequest;
import com.leduy.backend.dto.response.ShopOrderResponse;

import java.util.Map;

public interface ShopOrderService extends BaseService<ShopOrderResponse, String, ShopOrderRequest> {
    Map<String, Object> paginationShopOrder(PaginationRequest paginationRequest);
}
