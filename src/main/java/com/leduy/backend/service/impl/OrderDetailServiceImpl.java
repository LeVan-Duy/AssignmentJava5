package com.leduy.backend.service.impl;

import com.leduy.backend.dto.request.OrderDetailRequest;
import com.leduy.backend.dto.request.base.PaginationRequest;
import com.leduy.backend.dto.response.OrderDetailResponse;
import com.leduy.backend.dto.response.ProductDetailResponse;
import com.leduy.backend.dto.response.ShopOrderResponse;
import com.leduy.backend.entity.OrderDetail;
import com.leduy.backend.infrastructure.constant.EntityProperties;
import com.leduy.backend.infrastructure.exception.ResourceNotFoundException;
import com.leduy.backend.repository.CustomerRepository;
import com.leduy.backend.repository.EmployeeRepository;
import com.leduy.backend.repository.OrderDetailRepository;
import com.leduy.backend.service.OrderDetailService;
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
public class OrderDetailServiceImpl implements OrderDetailService {
    private OrderDetailRepository orderDetailRepository;
    private EmployeeRepository employeeRepository;
    private CustomerRepository customerRepository;
    private ModelMapper mapper;
    private GetEntityById getEntityById;
    private ProductDetailServiceImpl productDetailService;
    private ShopOrderServiceImpl shopOrderService;

    @Autowired
    public OrderDetailServiceImpl(OrderDetailRepository OrderDetailRepository,
                                  EmployeeRepository employeeRepository,
                                  CustomerRepository customerRepository,
                                  ModelMapper mapper,
                                  GetEntityById getEntityById,
                                  ProductDetailServiceImpl productDetailService,
                                  ShopOrderServiceImpl shopOrderService) {
        this.orderDetailRepository = OrderDetailRepository;
        this.employeeRepository = employeeRepository;
        this.customerRepository = customerRepository;
        this.mapper = mapper;
        this.getEntityById = getEntityById;
        this.productDetailService = productDetailService;
        this.shopOrderService = shopOrderService;
    }


    @Override
    public OrderDetailResponse save(@Valid OrderDetailRequest orderDetailRequest) {
        OrderDetail orderDetail = formMapOrderDetailRequestToEntity(orderDetailRequest);
        OrderDetail saveOrderDetail = orderDetailRepository.save(orderDetail);
        return mapOrderDetailToResponse(saveOrderDetail);
    }

    @Override
    public OrderDetailResponse update(@Valid OrderDetailRequest orderDetailRequest) {
        Optional<OrderDetail> optionalOrderDetail = orderDetailRepository.findById(orderDetailRequest.getId());
        if (optionalOrderDetail.isPresent()) {
            OrderDetail model = optionalOrderDetail.get();
            model.setPrice(orderDetailRequest.getPrice());
            model.setQuantity(orderDetailRequest.getQuantity());
            model.setTotalPrice(orderDetailRequest.getTotalPrice());
            model.setId(orderDetailRequest.getId());
            OrderDetail updateOrderDetail = formMapOrderDetailRequestToEntity(orderDetailRequest);
            BeanUtils.copyProperties(updateOrderDetail, model);
            orderDetailRepository.save(model);
            return mapOrderDetailToResponse(model);
        }
        throw new ResourceNotFoundException(EntityProperties.VALIDATE_NOT_FOUND);
    }

    @Override
    public OrderDetailResponse findById(String id) {
        Optional<OrderDetail> optionalOrderDetailResponse = orderDetailRepository.findById(id);
        if (optionalOrderDetailResponse.isPresent()) {
            return mapper.map(optionalOrderDetailResponse.get(), OrderDetailResponse.class);
        }
        throw new ResourceNotFoundException(EntityProperties.VALIDATE_NOT_FOUND);
    }

    @Override
    public boolean delete(String id) {
        Optional<OrderDetail> optionalOrderDetail = orderDetailRepository.findById(id);
        if (optionalOrderDetail.isPresent()) {
            orderDetailRepository.deleteById(id);
            return true;
        } else if (optionalOrderDetail.isEmpty()) {
            throw new ResourceNotFoundException(EntityProperties.VALIDATE_NOT_FOUND);
        }
        return false;
    }

    @Override
    public Map<String, Object> paginationOrderDetail(PaginationRequest paginationRequest) {
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
        Page<OrderDetail> orderDetailPage = orderDetailRepository.paginationOrderDetail(pageable, paginationRequest.getSearch());
        List<OrderDetailResponse> orderDetailResponseList = orderDetailPage
                .toList()
                .stream()
                .map(x ->
                        mapOrderDetailToResponse(x)
                )
                .collect(Collectors.toList());
        result.put("listOrderDetail", orderDetailResponseList);
        result.put("totalPage", orderDetailPage.getTotalPages());
        result.put("totalElement", orderDetailPage.getTotalElements());
        result.put("currentPage", paginationRequest.getPage());
        return result;
    }

    private OrderDetail formMapOrderDetailRequestToEntity(OrderDetailRequest orderDetailRequest) {
        OrderDetail model = mapper.map(orderDetailRequest, OrderDetail.class);
        model.setProductDetail(orderDetailRequest.getProductDetailRequest() != null ? getEntityById.getProductDetailById(orderDetailRequest.getProductDetailRequest().getId()) : null);
        model.setShopOrder(orderDetailRequest.getShopOrderRequest() != null ? getEntityById.getShopOrderById(orderDetailRequest.getShopOrderRequest().getId()) : null);
        return model;
    }

    private OrderDetailResponse mapOrderDetailToResponse(OrderDetail orderDetail) {
        OrderDetailResponse response = mapper.map(orderDetail, OrderDetailResponse.class);
        if (orderDetail.getProductDetail() != null) {
            response.setProductDetailResponse(mapper.map(orderDetail.getProductDetail(), ProductDetailResponse.class));
            response.setProductDetailResponse(productDetailService.mapProductDetailToResponse(orderDetail.getProductDetail()));
        }

        if (orderDetail.getShopOrder() != null) {
            response.setShopOrderResponse(mapper.map(orderDetail.getShopOrder(), ShopOrderResponse.class));
            response.setShopOrderResponse(shopOrderService.mapShopOrderToResponse(orderDetail.getShopOrder()));
        }

        return response;
    }
}
