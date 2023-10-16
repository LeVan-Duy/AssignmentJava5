package com.leduy.backend.service.impl;

import com.leduy.backend.dto.request.ShopOrderRequest;
import com.leduy.backend.dto.request.base.PaginationRequest;
import com.leduy.backend.dto.response.CustomerResponse;
import com.leduy.backend.dto.response.EmployeeResponse;
import com.leduy.backend.dto.response.ShopOrderResponse;
import com.leduy.backend.entity.ShopOrder;
import com.leduy.backend.infrastructure.constant.EntityProperties;
import com.leduy.backend.infrastructure.exception.ResourceNotFoundException;
import com.leduy.backend.repository.CustomerRepository;
import com.leduy.backend.repository.EmployeeRepository;
import com.leduy.backend.repository.ShopOrderRepository;
import com.leduy.backend.service.ShopOrderService;
import com.leduy.backend.utils.GetEntityById;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShopOrderServiceImpl implements ShopOrderService {
    private ShopOrderRepository shopOrderRepository;
    private EmployeeRepository employeeRepository;
    private CustomerRepository customerRepository;
    private ModelMapper mapper;
    private GetEntityById getEntityById;

    @Autowired
    public ShopOrderServiceImpl(ShopOrderRepository shopOrderRepository,
                                EmployeeRepository employeeRepository,
                                CustomerRepository customerRepository,
                                ModelMapper mapper,
                                GetEntityById getEntityById) {
        this.shopOrderRepository = shopOrderRepository;
        this.employeeRepository = employeeRepository;
        this.customerRepository = customerRepository;
        this.mapper = mapper;
        this.getEntityById = getEntityById;
    }


    @Override
    public ShopOrderResponse save(@Valid ShopOrderRequest shopOrderRequest) {
        ShopOrder shopOrder = formMapShopOrderRequestToEntity(shopOrderRequest);
        ShopOrder saveShopOrder = shopOrderRepository.save(shopOrder);
        return mapShopOrderToResponse(saveShopOrder);
    }

    @Override
    public ShopOrderResponse update(@Valid ShopOrderRequest shopOrderRequest) {
        Optional<ShopOrder> optionalShopOrder = shopOrderRepository.findById(shopOrderRequest.getId());
        if (optionalShopOrder.isPresent()) {
            ShopOrder model = optionalShopOrder.get();
            model.setAddress(shopOrderRequest.getAddress());
            model.setCompletionDate(shopOrderRequest.getCompletionDate());
            model.setDeliveryStartDate(shopOrderRequest.getDeliveryStartDate());
            model.setExpectedDeliveryDate(shopOrderRequest.getExpectedDeliveryDate());
            model.setPhoneNumber(shopOrderRequest.getPhoneNumber());
            model.setNote(shopOrderRequest.getNote());
            model.setReceivedDate(shopOrderRequest.getReceivedDate());
            model.setConfirmationDate(shopOrderRequest.getConfirmationDate());
            model.setStatusShopOrder(shopOrderRequest.getStatusShopOrder());
            model.setType(shopOrderRequest.getType());
            model.setTotalMoney(shopOrderRequest.getTotalMoney());
            model.setId(shopOrderRequest.getId());
            ShopOrder updateShopOrder = formMapShopOrderRequestToEntity(shopOrderRequest);
            BeanUtils.copyProperties(updateShopOrder, model);
            shopOrderRepository.save(model);
            return mapShopOrderToResponse(model);
        }
        throw new ResourceNotFoundException(EntityProperties.VALIDATE_NOT_FOUND);
    }

    @Override
    public ShopOrderResponse findById(String id) {
        Optional<ShopOrder> optionalShopOrderResponse = shopOrderRepository.findById(id);
        if (optionalShopOrderResponse.isPresent()) {
            return mapper.map(optionalShopOrderResponse.get(), ShopOrderResponse.class);
        }
        throw new ResourceNotFoundException(EntityProperties.VALIDATE_NOT_FOUND);
    }

    @Override
    public boolean delete(String id) {
        Optional<ShopOrder> optionalShopOrder = shopOrderRepository.findById(id);
        if (optionalShopOrder.isPresent()) {
            shopOrderRepository.deleteById(id);
            return true;
        } else if (optionalShopOrder.isEmpty()) {
            throw new ResourceNotFoundException(EntityProperties.VALIDATE_NOT_FOUND);
        }
        return false;
    }

    @Override
    public Map<String, Object> paginationShopOrder(PaginationRequest paginationRequest) {
        Pageable pageable = null;
        Map<String, Object> result = new HashMap<>();
        if (paginationRequest.getPage() > 0) {
            pageable = PageRequest.of(paginationRequest.getPage() - 1, paginationRequest.getSize());
        }
        if (paginationRequest.getOrder().equals("asc")) {
            pageable = PageRequest.of(paginationRequest.getPage() - 1, paginationRequest.getSize(), Sort.by(paginationRequest.getField()).ascending());
        }
        if (paginationRequest.getOrder().equals("desc")) {
            pageable = PageRequest.of(paginationRequest.getPage() - 1, paginationRequest.getSize(), Sort.by(paginationRequest.getField()).descending());

        }
        Page<ShopOrder> shopOrders = shopOrderRepository.paginationShopOrder(pageable, paginationRequest.getSearch());
        List<ShopOrderResponse> shopOrderResponseList = shopOrders
                .toList()
                .stream()
                .map(shopOrder ->
                        mapShopOrderToResponse(shopOrder)
                )
                .collect(Collectors.toList());
        result.put("listShopOrder", shopOrderResponseList);
        result.put("totalPage", shopOrders.getTotalPages());
        result.put("totalElement", shopOrders.getTotalElements());
        result.put("currentPage", paginationRequest.getPage());
        return result;
    }

    public ShopOrder formMapShopOrderRequestToEntity(ShopOrderRequest shopOrderRequest) {
        ShopOrder model = mapper.map(shopOrderRequest, ShopOrder.class);
        model.setCustomer(shopOrderRequest.getCustomerRequest() != null ? getEntityById.getCustomerById(shopOrderRequest.getCustomerRequest().getId()) : null);
        model.setEmployee(shopOrderRequest.getEmployeeRequest() != null ? getEntityById.getEmployeeById(shopOrderRequest.getEmployeeRequest().getId()) : null);
        return model;
    }

    public ShopOrderResponse mapShopOrderToResponse(ShopOrder shopOrder) {
        ShopOrderResponse response = mapper.map(shopOrder, ShopOrderResponse.class);
        if (shopOrder.getCustomer() != null) {
            response.setCustomerResponse(mapper.map(shopOrder.getCustomer(), CustomerResponse.class));
        }

        if (shopOrder.getEmployee() != null) {
            response.setEmployeeResponse(mapper.map(shopOrder.getEmployee(), EmployeeResponse.class));
        }

        return response;
    }
}
