package com.leduy.backend.service;

import com.leduy.backend.dto.request.SizeRequest;
import com.leduy.backend.dto.request.base.PaginationRequest;
import com.leduy.backend.dto.response.SizeResponse;

import java.util.Map;

public interface SizeService extends BaseService<SizeResponse, String, SizeRequest> {
    Map<String, Object> paginationSize(PaginationRequest paginationRequest);
}
