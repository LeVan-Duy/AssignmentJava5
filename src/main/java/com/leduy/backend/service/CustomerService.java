package com.leduy.backend.service;

import com.leduy.backend.dto.request.CustomerRequest;
import com.leduy.backend.dto.request.base.PaginationRequest;
import com.leduy.backend.dto.response.CustomerResponse;

import java.util.Map;

public interface CustomerService extends BaseService<CustomerResponse, String, CustomerRequest> {
    Map<String, Object> paginationCustomer(PaginationRequest paginationRequest);
}
