package com.leduy.backend.service;

import com.leduy.backend.dto.request.ColorRequest;
import com.leduy.backend.dto.request.base.PaginationRequest;
import com.leduy.backend.dto.response.ColorResponse;

import java.util.Map;

public interface ColorService extends BaseService<ColorResponse, String, ColorRequest> {
    Map<String, Object> paginationColor(PaginationRequest paginationRequest);
}
