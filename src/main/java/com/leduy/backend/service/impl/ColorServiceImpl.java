package com.leduy.backend.service.impl;

import com.leduy.backend.dto.request.ColorRequest;
import com.leduy.backend.dto.request.base.PaginationRequest;
import com.leduy.backend.dto.response.ColorResponse;
import com.leduy.backend.entity.Color;
import com.leduy.backend.infrastructure.constant.EntityProperties;
import com.leduy.backend.infrastructure.exception.ResourceNotFoundException;
import com.leduy.backend.repository.ColorRepository;
import com.leduy.backend.service.ColorService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
@Validated
@Service
public class ColorServiceImpl implements ColorService {
    @Autowired
    private ColorRepository colorRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public ColorResponse save(ColorRequest colorRequest) {
        Color model = mapper.map(colorRequest, Color.class);
        Color saveColor = colorRepository.save(model);
        ColorResponse colorResponse = mapper.map(saveColor, ColorResponse.class);
        return colorResponse;

    }

    @Override
    public ColorResponse update(@Valid ColorRequest colorRequest) {
        Optional<Color> optionalColor = colorRepository.findById(colorRequest.getId());
        if(optionalColor.isPresent()){
            Color model = optionalColor.get();
            model.setName(colorRequest.getName());
            model.setId(colorRequest.getId());
            colorRepository.save(model);
            return mapper.map(model,ColorResponse.class);
        }
        throw new ResourceNotFoundException(EntityProperties.VALIDATE_NOT_FOUND);
    }

    @Override
    public ColorResponse findById(String id) {
        Optional<Color> optionalColorResponse = colorRepository.findById(id);
        if (optionalColorResponse.isPresent()) {
            return mapper.map(optionalColorResponse.get(), ColorResponse.class);
        }
        throw new ResourceNotFoundException(EntityProperties.VALIDATE_NOT_FOUND);
    }

    @Override
    public boolean delete(String id) {
        Optional<Color> optionalColor = colorRepository.findById(id);
        if (optionalColor.isPresent()) {
            colorRepository.deleteById(id);
            return true;
        } else if (optionalColor.isEmpty()) {
            throw new ResourceNotFoundException(EntityProperties.VALIDATE_NOT_FOUND);
        }
        return false;
    }

    @Override
    public Map<String, Object> paginationColor(PaginationRequest paginationRequest) {
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
        Page<Color> colorPage = colorRepository.paginationColor(pageable, paginationRequest.getSearch());
        List<ColorResponse> colorResponseList = colorPage
                .toList()
                .stream()
                .map(colors -> mapper.map(colors, ColorResponse.class))
                .collect(Collectors.toList());
        result.put("listColor", colorResponseList);
        result.put("totalPage", colorPage.getTotalPages());
        result.put("totalElement", colorPage.getTotalElements());
        result.put("currentPage", paginationRequest.getPage());
        return result;
    }
}
