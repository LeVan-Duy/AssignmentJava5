package com.leduy.backend.service;

import com.leduy.backend.dto.request.ProductRequest;
import com.leduy.backend.dto.request.base.PaginationRequest;
import com.leduy.backend.dto.response.ProductResponse;

import java.util.Map;

public interface ProductService extends BaseService<ProductResponse, String, ProductRequest> {
    Map<String, Object> paginationProduct(PaginationRequest paginationRequest);
}
