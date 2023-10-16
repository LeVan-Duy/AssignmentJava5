package com.leduy.backend.service.impl;

import com.leduy.backend.dto.request.SizeRequest;
import com.leduy.backend.dto.request.base.PaginationRequest;
import com.leduy.backend.dto.response.SizeResponse;
import com.leduy.backend.entity.Size;
import com.leduy.backend.infrastructure.constant.EntityProperties;
import com.leduy.backend.infrastructure.exception.ResourceNotFoundException;
import com.leduy.backend.repository.SizeRepository;
import com.leduy.backend.service.SizeService;
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
public class SizeServiceImpl implements SizeService {
    @Autowired
    private SizeRepository sizeRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public SizeResponse save(@Valid SizeRequest sizeRequest) {
        Size model = mapper.map(sizeRequest, Size.class);
        Size saveSize = sizeRepository.save(model);
        SizeResponse sizeResponse = mapper.map(saveSize, SizeResponse.class);
        return sizeResponse;

    }

    @Override
    public SizeResponse update(@Valid SizeRequest sizeRequest) {
        Optional<Size> optionalSize = sizeRepository.findById(sizeRequest.getId());
        if (optionalSize.isPresent()) {
            Size model = optionalSize.get();
            model.setName(sizeRequest.getName());
            model.setId(sizeRequest.getId());
            sizeRepository.save(model);

            return mapper.map(model, SizeResponse.class);
        }
        throw new ResourceNotFoundException(EntityProperties.VALIDATE_NOT_FOUND);
    }

    @Override
    public SizeResponse findById(String id) {
        Optional<Size> optionalSizeResponse = sizeRepository.findById(id);
        if (optionalSizeResponse.isPresent()) {
            return mapper.map(optionalSizeResponse.get(), SizeResponse.class);
        }
        throw new ResourceNotFoundException(EntityProperties.VALIDATE_NOT_FOUND);
    }

    @Override
    public boolean delete(String id) {
        Optional<Size> optionalSize = sizeRepository.findById(id);
        if (optionalSize.isPresent()) {
            sizeRepository.deleteById(id);
            return true;
        } else if (optionalSize.isEmpty()) {
            throw new ResourceNotFoundException(EntityProperties.VALIDATE_NOT_FOUND);
        }
        return false;
    }

    @Override
    public Map<String, Object> paginationSize(PaginationRequest paginationRequest) {
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
        Page<Size> sizePage = sizeRepository.paginationSize(pageable, paginationRequest.getSearch());
        List<SizeResponse> SizeResponseList = sizePage
                .toList()
                .stream()
                .map(Sizes -> mapper.map(Sizes, SizeResponse.class))
                .collect(Collectors.toList());
        result.put("listSize", SizeResponseList);
        result.put("totalPage", sizePage.getTotalPages());
        result.put("totalElement", sizePage.getTotalElements());
        result.put("currentPage", paginationRequest.getPage());
        return result;
    }
}
