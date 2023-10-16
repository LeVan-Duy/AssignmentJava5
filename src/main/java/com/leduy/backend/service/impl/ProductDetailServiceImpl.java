package com.leduy.backend.service.impl;

import com.leduy.backend.dto.request.ProductDetailRequest;
import com.leduy.backend.dto.request.base.PaginationRequest;
import com.leduy.backend.dto.response.*;
import com.leduy.backend.entity.ProductDetail;
import com.leduy.backend.infrastructure.constant.EntityProperties;
import com.leduy.backend.infrastructure.exception.ResourceNotFoundException;
import com.leduy.backend.repository.*;
import com.leduy.backend.service.ProductDetailService;
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
public class ProductDetailServiceImpl implements ProductDetailService {

    private ProductDetailRepository productDetailRepository;
    private BrandRepository brandRepository;
    private ColorRepository colorRepository;
    private SizeRepository sizeRepository;
    private ProductRepository productRepository;
    private ModelMapper mapper;
    private GetEntityById getEntityById;

    @Autowired
    public ProductDetailServiceImpl(ProductDetailRepository productDetailRepository,
                                    BrandRepository brandRepository,
                                    ColorRepository colorRepository,
                                    SizeRepository sizeRepository,
                                    ProductRepository productRepository,
                                    ModelMapper mapper,
                                    GetEntityById getEntityById) {
        this.productDetailRepository = productDetailRepository;
        this.brandRepository = brandRepository;
        this.colorRepository = colorRepository;
        this.sizeRepository = sizeRepository;
        this.productRepository = productRepository;
        this.mapper = mapper;
        this.getEntityById = getEntityById;
    }


    @Override
    public ProductDetailResponse save(@Valid ProductDetailRequest productDetailRequest) {
        ProductDetail productDetail = formMapProductDetailRequestToEntity(productDetailRequest);
        ProductDetail saveProductDetail = productDetailRepository.save(productDetail);
        return mapProductDetailToResponse(saveProductDetail);
    }

    @Override
    public ProductDetailResponse update(@Valid ProductDetailRequest productDetailRequest) {
        Optional<ProductDetail> optionalProductDetail = productDetailRepository.findById(productDetailRequest.getId());
        if (optionalProductDetail.isPresent()) {
            ProductDetail model = optionalProductDetail.get();
            model.setId(productDetailRequest.getId());
            model.setGender(productDetailRequest.getGender());
            model.setDescription(productDetailRequest.getDescription());
            model.setPrice(productDetailRequest.getPrice());
            ProductDetail updatedProductDetail = formMapProductDetailRequestToEntity(productDetailRequest);
            BeanUtils.copyProperties(updatedProductDetail, model);
            productDetailRepository.save(model);
            return mapProductDetailToResponse(model);
        }
        throw new ResourceNotFoundException(EntityProperties.VALIDATE_NOT_FOUND);
    }

    @Override
    public ProductDetailResponse findById(String id) {
        Optional<ProductDetail> optionalProductDetailResponse = productDetailRepository.findById(id);
        if (optionalProductDetailResponse.isPresent()) {
            return mapper.map(optionalProductDetailResponse.get(), ProductDetailResponse.class);
        }
        throw new ResourceNotFoundException(EntityProperties.VALIDATE_NOT_FOUND);
    }

    @Override
    public boolean delete(String id) {
        Optional<ProductDetail> optionalProductDetail = productDetailRepository.findById(id);
        if (optionalProductDetail.isPresent()) {
            productDetailRepository.deleteById(id);
            return true;
        } else if (optionalProductDetail.isEmpty()) {
            throw new ResourceNotFoundException(EntityProperties.VALIDATE_NOT_FOUND);
        }
        return false;
    }

    @Override
    public Map<String, Object> paginationProductDetail(PaginationRequest paginationRequest) {
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
        Page<ProductDetail> ProductDetailPage = productDetailRepository.paginationProductDetail(pageable, paginationRequest.getSearch());
        List<ProductDetailResponse> productDetailResponses = ProductDetailPage
                .toList()
                .stream()
                .map(productDetail -> {
                    return mapProductDetailToResponse(productDetail);
                })
                .collect(Collectors.toList());
        result.put("listProductDetail", productDetailResponses);
        result.put("totalPage", ProductDetailPage.getTotalPages());
        result.put("totalElement", ProductDetailPage.getTotalElements());
        result.put("currentPage", paginationRequest.getPage());
        return result;
    }

    public ProductDetail formMapProductDetailRequestToEntity(ProductDetailRequest productDetailRequest) {
        ProductDetail model = mapper.map(productDetailRequest, ProductDetail.class);
        model.setProduct(getEntityById.getProductById(productDetailRequest.getProductRequest().getId()));
        model.setColor(getEntityById.getColorById(productDetailRequest.getColorRequest().getId()));
        model.setBrand(getEntityById.getBrandById(productDetailRequest.getBrandRequest().getId()));
        model.setSize(getEntityById.getSizeById(productDetailRequest.getSizeRequest().getId()));
        return model;
    }

    public ProductDetailResponse mapProductDetailToResponse(ProductDetail productDetail) {
        ProductDetailResponse response = mapper.map(productDetail, ProductDetailResponse.class);
        response.setColorResponse(mapper.map(productDetail.getColor(), ColorResponse.class));
        response.setBrandResponse(mapper.map(productDetail.getBrand(), BrandResponse.class));
        response.setProductResponse(mapper.map(productDetail.getProduct(), ProductResponse.class));
        response.setSizeResponse(mapper.map(productDetail.getSize(), SizeResponse.class));
        return response;
    }


}
