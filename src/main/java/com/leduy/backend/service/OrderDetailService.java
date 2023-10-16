package com.leduy.backend.service;

import com.leduy.backend.dto.request.OrderDetailRequest;
import com.leduy.backend.dto.request.base.PaginationRequest;
import com.leduy.backend.dto.response.OrderDetailResponse;

import java.util.Map;

public interface OrderDetailService extends BaseService<OrderDetailResponse, String, OrderDetailRequest> {
    Map<String, Object> paginationOrderDetail(PaginationRequest paginationRequest);
}
