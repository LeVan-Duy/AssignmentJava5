package com.leduy.backend.service;

import com.leduy.backend.dto.request.ProductDetailRequest;
import com.leduy.backend.dto.request.base.PaginationRequest;
import com.leduy.backend.dto.response.ProductDetailResponse;

import java.util.Map;

public interface ProductDetailService extends BaseService<ProductDetailResponse, String, ProductDetailRequest> {
    Map<String, Object> paginationProductDetail(PaginationRequest paginationRequest);
}
