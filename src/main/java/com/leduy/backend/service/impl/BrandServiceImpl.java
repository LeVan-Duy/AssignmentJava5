package com.leduy.backend.service.impl;

import com.leduy.backend.dto.request.BrandRequest;
import com.leduy.backend.dto.request.base.PaginationRequest;
import com.leduy.backend.dto.response.BrandResponse;
import com.leduy.backend.entity.Brand;
import com.leduy.backend.infrastructure.constant.EntityProperties;
import com.leduy.backend.infrastructure.exception.ResourceNotFoundException;
import com.leduy.backend.repository.BrandRepository;
import com.leduy.backend.service.BrandService;
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
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public BrandResponse save(@Valid BrandRequest brandRequest) {
        Brand model = mapper.map(brandRequest, Brand.class);
        Brand saveBrand = brandRepository.save(model);
        BrandResponse brandResponse = mapper.map(saveBrand, BrandResponse.class);
        return brandResponse;
    }

    @Override
    public BrandResponse update(@Valid BrandRequest brandRequest) {
        Optional<Brand> optionalBrand = brandRepository.findById(brandRequest.getId());
        if (optionalBrand.isPresent()) {
            Brand model = optionalBrand.get();
            model.setName(brandRequest.getName());
            model.setId(brandRequest.getId());
            brandRepository.save(model);
            return mapper.map(model, BrandResponse.class);
        }
        throw new ResourceNotFoundException(EntityProperties.VALIDATE_NOT_FOUND);
    }

    @Override
    public BrandResponse findById(String id) {
        Optional<Brand> optionalBrandResponse = brandRepository.findById(id);
        if (optionalBrandResponse.isPresent()) {
            return mapper.map(optionalBrandResponse.get(), BrandResponse.class);
        }
        throw new ResourceNotFoundException(EntityProperties.VALIDATE_NOT_FOUND);
    }

    @Override
    public boolean delete(String id) {
        Optional<Brand> optionalBrand = brandRepository.findById(id);
        if (optionalBrand.isPresent()) {
            brandRepository.deleteById(id);

        } else if (optionalBrand.isEmpty()) {
            throw new ResourceNotFoundException(EntityProperties.VALIDATE_NOT_FOUND);
        }
        return false;
    }

    @Override
    public Map<String, Object> paginationBrand(PaginationRequest paginationRequest) {
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
        Page<Brand> brandPage = brandRepository.paginationBrand(pageable, paginationRequest.getSearch());
        List<BrandResponse> brandResponseList = brandPage
                .toList()
                .stream()
                .map(Brands -> mapper.map(Brands, BrandResponse.class))
                .collect(Collectors.toList());
        result.put("listBrand", brandResponseList);
        result.put("totalPage", brandPage.getTotalPages());
        result.put("totalElement", brandPage.getTotalElements());
        result.put("currentPage", paginationRequest.getPage());
        return result;
    }

//    public Pageable pageable(PaginationRequest paginationRequest) {
//        Pageable pageable = null;
//        if (paginationRequest.getPage() > 0) {
//            pageable = PageRequest.of(paginationRequest.getPage() - 1, paginationRequest.getSize());
//        }
//        if (paginationRequest.getOrder().equals("asc")) {
//            pageable = PageRequest.of(paginationRequest.getPage() - 1, paginationRequest.getSize(), Sort.by(paginationRequest.getField()).ascending());
//        }
//        if (paginationRequest.getOrder().equals("desc")) {
//            pageable = PageRequest.of(paginationRequest.getPage() - 1, paginationRequest.getSize(), Sort.by(paginationRequest.getField()).descending());
//
//        }
//        return pageable;
//
//    }
}
