package com.leduy.backend.service.impl;

import com.leduy.backend.dto.request.ProductRequest;
import com.leduy.backend.dto.request.base.PaginationRequest;
import com.leduy.backend.dto.response.ProductResponse;
import com.leduy.backend.entity.Product;
import com.leduy.backend.infrastructure.constant.EntityProperties;
import com.leduy.backend.infrastructure.exception.ResourceNotFoundException;
import com.leduy.backend.repository.ProductRepository;
import com.leduy.backend.service.ProductService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
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
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public ProductResponse save(@Valid ProductRequest productRequest) {
        Product model = mapper.map(productRequest, Product.class);
        Product saveProduct = productRepository.save(model);
        ProductResponse productResponse = mapper.map(saveProduct, ProductResponse.class);
        return productResponse;

    }

    @Override
    public ProductResponse update(@Valid ProductRequest productRequest) {
        Optional<Product> optionalProduct = productRepository.findById(productRequest.getId());
        if(optionalProduct.isPresent()){
            Product model = optionalProduct.get();
            model.setName(productRequest.getName());
            model.setId(productRequest.getId());
            productRepository.save(model);
            return mapper.map(model,ProductResponse.class);
        }
        throw new ResourceNotFoundException(EntityProperties.VALIDATE_NOT_FOUND);
    }

    @Override
    public ProductResponse findById(String id) {
        Optional<Product> optionalProductResponse = productRepository.findById(id);
        if (optionalProductResponse.isPresent()) {
            return mapper.map(optionalProductResponse.get(), ProductResponse.class);
        }
        throw new ResourceNotFoundException(EntityProperties.VALIDATE_NOT_FOUND);
    }

    @Override
    public boolean delete(String id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            productRepository.deleteById(id);
            return true;
        } else if (optionalProduct.isEmpty()) {
            throw new ResourceNotFoundException(EntityProperties.VALIDATE_NOT_FOUND);
        }
        return false;
    }

    @Override
    public Map<String, Object> paginationProduct(PaginationRequest paginationRequest) {
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
        Page<Product> ProductPage = productRepository.paginationProduct(pageable, paginationRequest.getSearch());
        List<ProductResponse> ProductResponseList = ProductPage
                .toList()
                .stream()
                .map(Products -> mapper.map(Products, ProductResponse.class))
                .collect(Collectors.toList());
        result.put("listProduct", ProductResponseList);
        result.put("totalPage", ProductPage.getTotalPages());
        result.put("totalElement", ProductPage.getTotalElements());
        result.put("currentPage", paginationRequest.getPage());
        return result;
    }
}
