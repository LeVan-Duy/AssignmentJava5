package com.leduy.backend.service;

import com.leduy.backend.dto.request.BrandRequest;
import com.leduy.backend.dto.request.base.PaginationRequest;
import com.leduy.backend.dto.response.BrandResponse;

import java.util.Map;

public interface BrandService extends BaseService<BrandResponse, String, BrandRequest> {
    Map<String, Object> paginationBrand(PaginationRequest paginationRequest);
}
