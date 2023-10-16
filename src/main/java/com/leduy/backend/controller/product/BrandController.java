package com.leduy.backend.controller.product;

import com.leduy.backend.dto.request.BrandRequest;
import com.leduy.backend.dto.request.base.PaginationRequest;
import com.leduy.backend.dto.response.BrandResponse;
import com.leduy.backend.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/brand")
@CrossOrigin(origins = {"*"})
public class BrandController {
    @Autowired
    private BrandService brandService;

    @PostMapping("")
    public ResponseEntity<Map<String, Object>> paginationColor(PaginationRequest paginationRequest) {
        ResponseEntity<Map<String, Object>> response = new ResponseEntity<>(
                brandService.paginationBrand(paginationRequest), HttpStatus.OK);
        return response;
    }

    @GetMapping("findById/{id}")
    public ResponseEntity<BrandResponse> getColorById(@PathVariable String id) {
        BrandResponse brandResponse = brandService.findById(id);
        return new ResponseEntity<>(brandResponse, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<BrandResponse> saveColor(@RequestBody BrandRequest brandRequest) {
        BrandResponse brandResponse = brandService.save(brandRequest);
        return new ResponseEntity<>(brandResponse, HttpStatus.CREATED);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<BrandResponse> updateColor(@PathVariable("id") String id,
                                                     @RequestBody BrandRequest brandRequest) {
        brandRequest.setId(id);
        BrandResponse colorResponse = brandService.update(brandRequest);
        return new ResponseEntity<>(colorResponse, HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteColor(@PathVariable String id) {
        boolean deleted = brandService.delete(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
